package com.github.developers.controller;

import com.github.developers.service.github.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/github")
public class GithubController {

  @Autowired
  private RequestService serivce;

  @GetMapping("search-by-name/{name}")
  public String searchByName(@PathVariable(name = "name") String name){
    return serivce.searchByName(name).getBody().toString();
  }

  @GetMapping("search-by-language/{language}")
  public String searchRepositoriesByTopic(@PathVariable(name = "language") String language){
    return serivce.searchByLanguage(language).getBody().toString();
  }
}
