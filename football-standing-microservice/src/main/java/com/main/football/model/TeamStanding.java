package com.main.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TeamStanding {

	@JsonProperty(value ="country_id")
	private int countryId;
	@JsonProperty(value ="country_Name")
	private String countyName;
	@JsonProperty(value = "legue_Name")
	private String legueName;
	@JsonProperty(value = "legue_Id")
	private int leagueId;
	@JsonProperty(value = "team_Name")
	private String teamName;
	@JsonProperty(value = "team_Id")
	private int teamId;
	@JsonProperty(value = "overall_position")
	private int overallPosition;
	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getLegueName() {
		return legueName;
	}
	public void setLegueName(String legueName) {
		this.legueName = legueName;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getOverallPosition() {
		return overallPosition;
	}
	public void setOverallPosition(int overallPosition) {
		this.overallPosition = overallPosition;
	}
	
	
	
	
}
