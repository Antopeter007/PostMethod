package com.Rest.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetMethod {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Map<String, Object>> responseArray = new ArrayList<>();
		HttpHeaders header = new HttpHeaders();
		RestTemplate template = new RestTemplate();
		String url = "https://jsonplaceholder.typicode.com/posts";
		HttpEntity<String> entity = new HttpEntity<String>(header);
		ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
		HttpStatus statusCode = response.getStatusCode();
		String body = response.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (statusCode == response.getStatusCode().OK) {
			try {
				Object jsonString = mapper.readValue(body, new TypeReference<Object>() {
				});
				List<Map<String, Object>> jsonArray = (List<Map<String, Object>>) jsonString;
				Map<String, Object> responseMap = new HashMap<String, Object>();
				for (Map<String, Object> map : jsonArray) {
					String id = String.valueOf(map.get("id"));
					String title = String.valueOf(map.get("title"));
					responseMap.put("ID", id);
					responseMap.put("Title", title);
					responseArray.add(responseMap);
					System.out.println(responseArray);
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}

}
