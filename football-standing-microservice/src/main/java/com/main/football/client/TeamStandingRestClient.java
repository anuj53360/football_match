package com.main.football.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.main.football.config.RestTemplateWebConfig;
import com.main.football.model.Country;
import com.main.football.model.Leagues;
import com.main.football.model.TeamStanding;

@Service
public class TeamStandingRestClient {

	private static final String APIKEY = "APIkey";
	

	@Value("${football.league.base.url}")
	private String baseUrl;

	@Value("${football.league.action.standings}")
	private String standingsAction;

	@Value("${football.league.action.countries}")
	private String countriesAction;

	@Value("${football.league.action.leagues}")
	private String leaguesAction;

	@Value("${football.league.api}")
	private String api;

//	@Autowired
//	public TeamStandingRestClient(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
//	}
	@Autowired
	RestTemplateWebConfig restTemplateWebConfig;
     
	public Country[] getCountries() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", countriesAction);
		UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
		return getRestTemplateObject()
				.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()), Country[].class)
				.getBody();
	}

	public Leagues[] getLeagues(int countryId) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", leaguesAction);
		queryParams.put("country_id", String.valueOf(countryId));
		UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
		return getRestTemplateObject()
				.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()), Leagues[].class)
				.getBody();
	}

	public TeamStanding[] getTeamStanding(int leagueId) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", standingsAction);
		queryParams.put("league_id", String.valueOf(leagueId));
		UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
		return getRestTemplateObject()
				.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()), TeamStanding[].class)
				.getBody();
	}


	private UriComponentsBuilder getUriComponentsBuilder(String url, Map<String, String> queryParams) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam(APIKEY, api);
		queryParams.forEach(builder::queryParam);
		return builder;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
	
	private RestTemplate getRestTemplateObject() {
		return restTemplateWebConfig.getRestTemplate();
	}

}
