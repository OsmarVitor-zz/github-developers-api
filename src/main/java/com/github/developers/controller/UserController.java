package com.github.developers.controller;

import com.github.developers.model.User;
import com.github.developers.model.dto.UserDTO;
import com.github.developers.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService<UserDTO> userService;

  @PostMapping
  ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
    User user = userService.create(userDTO);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
        .buildAndExpand(user.getUuid()).toUri()).build();
  }

  @GetMapping("/{email}")
  ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
    return ResponseEntity.ok(userService.findByEmail(email));
  }

  @GetMapping("find-all")
  ResponseEntity<Page<UserDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "15") int size){
    return ResponseEntity.ok(userService.findAll(PageRequest.of(page, size)));
  }

}
