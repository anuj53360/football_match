package com.main.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Leagues {

	@JsonProperty(value = "county_id")
	private int countryId;
	@JsonProperty(value = "country_name")
	private String countryName;
	@JsonProperty(value = "league_name")
	private String leaugeName;
	@JsonProperty(value = "league_id")
	private int leagueId;
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLeaugeName() {
		return leaugeName;
	}
	public void setLeaugeName(String leaugeName) {
		this.leaugeName = leaugeName;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	
	
}
