package com.main.football.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.football.client.TeamStandingRestClient;
import com.main.football.dto.TeamStandingDto;
import com.main.football.exception.BadRequestException;
import com.main.football.model.Country;
import com.main.football.model.Leagues;
import com.main.football.model.TeamStanding;
import com.main.football.model.TeamStandingRequest;


@Service
public class TeamStandingService {

	Logger log= LoggerFactory.getLogger(TeamStandingService.class);
	
	private final TeamStandingRestClient teamStandingRestClient;

	  @Autowired
	  public TeamStandingService(TeamStandingRestClient teamStandingRestClient) {
	    this.teamStandingRestClient = teamStandingRestClient;
	  }

	  public TeamStandingDto getTeamStanding(TeamStandingRequest teamStandingRequest) {
	    //Validate the request
	    TeamStanding teamStandingDefault = getDefaultTeamStanding(teamStandingRequest);
	    List<Country> countries = getCountries();
	    Country country = getCountryByName(teamStandingRequest, countries);
	    if (!isValidateCountryResponse(teamStandingRequest, teamStandingDefault, country)) {
	      return TeamStandingDto.from(teamStandingDefault);
	    }
	    teamStandingDefault.setCountryId(country.getId());

	    List<Leagues> leaguesList = getLeagues(country.getId());
	    Leagues leagues = getLeaguesByName(teamStandingRequest, leaguesList);
	    if (!isValidLeagueResponse(teamStandingRequest, teamStandingDefault, leagues)) {
	      return(TeamStandingDto.from(teamStandingDefault));
	    }
	    teamStandingDefault.setLeagueId(leagues.getLeagueId());
	    List<TeamStanding> teamStandings = getTeamStanding(leagues.getLeagueId());
	    log.info("team standing found {}", teamStandings.toString());

	    TeamStanding teamStandingsFiltered = getFilteredTeamStanding(teamStandingRequest,
	        teamStandings);
	    teamStandingsFiltered.setCountryId(country.getId());
	    log.info("team standing filtered found {}", teamStandingsFiltered.toString());
	    if (teamStandingsFiltered.getTeamId()==0){
	      return TeamStandingDto.from(teamStandingDefault);
	    }

	    return TeamStandingDto.from(teamStandingsFiltered);
	  }

	  private Country getCountryByName(TeamStandingRequest teamStandingRequest,
	      List<Country> countries) {
	    return countries.stream()
	        .filter(c -> teamStandingRequest.getCountryName().equalsIgnoreCase(c.getName())).findFirst()
	        .orElse(null);
	  }

	  private Leagues getLeaguesByName(TeamStandingRequest teamStandingRequest,
	      List<Leagues> leaguesList) {
	    return leaguesList.stream()
	        .filter(l -> teamStandingRequest.getLeagueName().equalsIgnoreCase(l.getLeaugeName()))
	        .findFirst().orElse(null);
	  }

	  private TeamStanding getFilteredTeamStanding(TeamStandingRequest teamStandingRequest,
	      List<TeamStanding> teamStandings) {
	    return teamStandings.stream()
	        .filter(t -> teamStandingRequest.getTeamName().equalsIgnoreCase(t.getTeamName()))
	        .findFirst().orElse(null);
	  }

	  private boolean isValidLeagueResponse(TeamStandingRequest teamStandingRequest,
	      TeamStanding teamStandingDefault, Leagues leagues) {
	    if (Objects.isNull(leagues)) {
	      throw new BadRequestException(
	          "leagues Not Found by name " + teamStandingRequest.getLeagueName());
	    }
	    log.info("league found {}", leagues.toString());
	    if (leagues.getLeagueId() == 0) {
	      return false;
	    }
	    return true;
	  }

	  private boolean isValidateCountryResponse(TeamStandingRequest teamStandingRequest,
	      TeamStanding teamStandingDefault, Country country) {
	    if (Objects.isNull(country)) {
	      throw new BadRequestException(
	          "Country Not Found by name " + teamStandingRequest.getCountryName());
	    }
	    log.info("Country found {}", country.toString());

	    if (country.getId() == 0) {
	      teamStandingDefault.setCountryId(0);
	      return false;
	    }
	    return true;
	  }

	  private TeamStanding getDefaultTeamStanding(TeamStandingRequest teamStandingRequest) {
	    TeamStanding teamStanding = new TeamStanding();
	    teamStanding.setTeamName(teamStandingRequest.getTeamName());
	    teamStanding.setCountyName(teamStandingRequest.getCountryName());
	    teamStanding.setLegueName(teamStandingRequest.getLeagueName());
	    return teamStanding;
	  }

	  private List<Country> getCountries() {
	    return new ArrayList<>(Arrays.asList(teamStandingRestClient.getCountries()));
	  }

	  private List<Leagues> getLeagues(int countryId) {
	    return new ArrayList<>(Arrays.asList(teamStandingRestClient.getLeagues(countryId)));
	  }


	  private List<TeamStanding> getTeamStanding(int leagueId) {
	    return new ArrayList<>(Arrays.asList(teamStandingRestClient.getTeamStanding(leagueId)));
	  }
}
