package com.main.football.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class TeamStandingRequest {

	@NonNull
	private String teamName;
	@NonNull
	private String leagueName;
	@NonNull
	private String countryName;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
	
}
