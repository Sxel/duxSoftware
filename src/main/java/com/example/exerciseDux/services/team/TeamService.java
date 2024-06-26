package com.example.exerciseDux.services.team;


import com.example.exerciseDux.model.team.Team;
import com.example.exerciseDux.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamService {

    @Autowired
    private final TeamRepository teamRepository;

    public List<Team> getTeam (){
        return teamRepository.findAll();
    }


    public Optional <Team> getTeamById(Long id){
        return teamRepository.findById(id);
    }

    public ResponseEntity<Object> addTeam (Team team){
        HashMap<String, Object> data = new HashMap<>();
        teamRepository.save(team);
        data.put("data", team);
        data.put("message","Successfully saved");
        return new ResponseEntity<>(
                data,
                HttpStatus.CREATED
        );
    }

    public void deleteTeam (Long id){
        teamRepository.deleteById(id);
    }

    public ResponseEntity<Object> updateTeam(Long id, Team updatedTeam) {
        Optional<Team> existingTeamOptional = teamRepository.findById(id);

        if (existingTeamOptional.isPresent()) {
            Team existingTeam = existingTeamOptional.get();

            existingTeam.setName(updatedTeam.getName());
            existingTeam.setLeague(updatedTeam.getLeague());
            existingTeam.setCountry(updatedTeam.getCountry());

            teamRepository.save(existingTeam);

            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("id", existingTeam.getId());
            responseBody.put("country", existingTeam.getCountry());
            responseBody.put("league", existingTeam.getLeague());
            responseBody.put("name", existingTeam.getName());

            return ResponseEntity.ok(responseBody);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Equipo no encontrado");
            errorResponse.put("codigo", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
