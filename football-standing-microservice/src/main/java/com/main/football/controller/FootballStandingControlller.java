package com.main.football.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.football.dto.TeamStandingDto;
import com.main.football.model.TeamStandingRequest;
import com.main.football.service.TeamStandingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/service/team/standing")
public class FootballStandingControlller {

	Logger log= LoggerFactory.getLogger(TeamStandingService.class);
	
	private final TeamStandingService teamStandingService;
	

	 @Autowired
	  public FootballStandingControlller(TeamStandingService teamStandingService) {
	    this.teamStandingService = teamStandingService;
	  }

	  @GetMapping
	  public ResponseEntity<TeamStandingDto> getStandings(@Validated TeamStandingRequest teamStandingRequest) {
	    log.info("Request {}", teamStandingRequest.toString());
	    return ResponseEntity.ok(teamStandingService.getTeamStanding(teamStandingRequest));
	  }
}
