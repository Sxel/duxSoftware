package com.example.exerciseDux.controllers.auth;


import com.example.exerciseDux.model.auth.AuthenticationReq;
import com.example.exerciseDux.model.auth.TokenInfo;
import com.example.exerciseDux.services.auth.JwtUtilService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Operation(summary = "Autenticar", description = "Autentica a un usuario y nos devuelve un token si las credenciales son validas")
    @PostMapping("/auth/login")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
        logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                            authenticationReq.getClave()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationReq.getUsuario());

        final String jwt = jwtUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new TokenInfo(jwt));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + ex.getMessage());
    }
}
