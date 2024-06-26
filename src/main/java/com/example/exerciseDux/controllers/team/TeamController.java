package com.example.exerciseDux.controllers.team;

import com.example.exerciseDux.model.team.Team;
import com.example.exerciseDux.services.team.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Obtiene todos los equipos", description = "Retorna una lista de todos los equipos.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getTeams() {
        return this.teamService.getTeam();
    }


    @Operation(summary = "Obtener un equipo por su id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTeamById(@PathVariable Long id) {
        Optional<Team> teamOptional = teamService.getTeamById(id);

        if (teamOptional.isPresent()) {
            return ResponseEntity.ok(teamOptional.get());
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Equipo no encontrado");
            errorResponse.put("codigo", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @Operation(summary = "Eliminar un equipo por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeamById(@PathVariable Long id) {
        Optional<Team> teamOptional = teamService.getTeamById(id);

        if (teamOptional.isPresent()) {
            teamService.deleteTeam(id);
            return ResponseEntity.noContent().build();
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Equipo no encontrado");
            errorResponse.put("codigo", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    @Operation(summary = "Agregar un nuevo equipo")
    @PostMapping
    public ResponseEntity<Object> addTeam(@Valid @RequestBody Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            errorResponse.put("mensaje", "La solicitud es invalida");
            errorResponse.put("codigo", 400);
            errorResponse.put("errores", errors);  // Para incluir los detalles de los errores si es necesario
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return teamService.addTeam(team);
    }

    @Operation(summary = "Actualizar un equipo por su id")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTeam(@PathVariable Long id, @Valid @RequestBody Team updatedTeam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            errorResponse.put("mensaje", "La solicitud es invalida");
            errorResponse.put("codigo", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("errores", errors);
            return ResponseEntity.badRequest().body(errorResponse);
        }

        return teamService.updateTeam(id, updatedTeam);
    }
    }
