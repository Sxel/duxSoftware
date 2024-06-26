package com.example.exerciseDux.repositories;

import com.example.exerciseDux.model.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
