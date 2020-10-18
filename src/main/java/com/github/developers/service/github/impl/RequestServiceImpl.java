package com.github.developers.service.github.impl;

import com.github.developers.service.github.RequestService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestServiceImpl implements RequestService {

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${github.base.search.name.url}")
  private String endpoint;

  @Override
  public HttpResponse<JsonNode> searchByName(String username) {
    return Unirest.get(endpoint).routeParam("param", "users").routeParam("q",username).asJson();
  }


}
