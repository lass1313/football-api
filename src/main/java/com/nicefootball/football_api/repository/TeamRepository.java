package com.nicefootball.football_api.repository;

import com.nicefootball.football_api.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {

}
