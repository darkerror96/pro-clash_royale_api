/**
 * 
 */
package com.collabera.consume;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author rutpatel
 *
 */
public class ConsumeAPI {

	private RestTemplate restTemplate;
	private HttpEntity<String> entity;

	public ConsumeAPI(RestTemplate restTemplate, HttpEntity<String> entity) {
		this.restTemplate = restTemplate;
		this.entity = entity;
	}

	public ResponseEntity<String> getPlayerDetails(String playerTag) {
		ResponseEntity<String> response = restTemplate.exchange("https://api.royaleapi.com/player/" + playerTag,
				HttpMethod.GET, entity, String.class);

		return response;
	}

	public ResponseEntity<String> getPlayersDetails(String[] playersTag) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < playersTag.length; i++) {
			sb.append(playersTag[i]);
			if (i < playersTag.length - 1) {
				sb.append(",");
			}
		}

		ResponseEntity<String> response = restTemplate.exchange("https://api.royaleapi.com/player/" + sb,
				HttpMethod.GET, entity, String.class);

		return response;
	}

	public ResponseEntity<String> getPopularPlayerDetails() {

		ResponseEntity<String> response = restTemplate.exchange("https://api.royaleapi.com/popular/players",
				HttpMethod.GET, entity, String.class);

		return response;
	}

	public ResponseEntity<String> getTopPlayerDetails() {

		ResponseEntity<String> response = restTemplate.exchange("https://api.royaleapi.com/top/players", HttpMethod.GET,
				entity, String.class);

		return response;
	}

}
