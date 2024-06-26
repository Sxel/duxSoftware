package com.example.exerciseDux.services;

import com.example.exerciseDux.model.team.Team;
import com.example.exerciseDux.repositories.TeamRepository;
import com.example.exerciseDux.services.team.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeamServiceTest {

    @MockBean
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTeam(){
        Team team = new Team();
        team.setName("Ajax");
        team.setLeague("Eredivisie");
        team.setCountry("Holanda");

        List<Team> teams = Collections.singletonList(team);

        when(teamRepository.findAll()).thenReturn(teams);

        List<Team> result = teamService.getTeam();
        assertEquals(1, result.size());
        assertEquals("Ajax", result.get(0).getName());
    }

    @Test
    public void deleteTeam(){
        Long id = 1L;
        teamService.deleteTeam(id);
        verify(teamRepository, times(1)).deleteById(id);
    }

    @Test
    public void testAddTeam() {
        Team team = new Team();
        team.setName("Ajax");
        team.setLeague("Eredivisie");
        team.setCountry("Holanda");

        when(teamRepository.save(any(Team.class))).thenReturn(team);

        ResponseEntity<Object> response = teamService.addTeam(team);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertNotNull(responseBody.get("data"));
        assertEquals("Successfully saved", responseBody.get("message"));

        verify(teamRepository, times(1)).save(any(Team.class));
    }

    // Additional Tests
    @Test
    public void getTeam_emptyList(){
        when(teamRepository.findAll()).thenReturn(Collections.emptyList());

        List<Team> result = teamService.getTeam();
        assertTrue(result.isEmpty());
    }

    @Test
    public void addTeam_existingTeam(){
        Team team = new Team();
        team.setName("Ajax");
        team.setLeague("Eredivisie");
        team.setCountry("Holanda");

        when(teamRepository.save(any(Team.class))).thenThrow(new RuntimeException("Team already exists"));

        assertThrows(RuntimeException.class, () -> {
            teamService.addTeam(team);
        });

        verify(teamRepository, times(1)).save(any(Team.class));
    }
}
