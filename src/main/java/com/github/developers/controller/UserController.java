package com.github.developers.controller;

import com.github.developers.config.security.JwtManager;
import com.github.developers.model.User;
import com.github.developers.model.dto.LoginDTO;
import com.github.developers.model.dto.UserDTO;
import com.github.developers.model.dto.UserLoginResponseDTO;
import com.github.developers.service.UserService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin("*")
@Api(value = "")
@RequestMapping("user")
public class UserController {

  @Autowired private UserService<UserDTO> userService;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtManager jwtManager;

  @PostMapping("/create")
  ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    User user = userService.create(userDTO);
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(user.getUuid())
                .toUri())
        .build();
  }

  @PostMapping("/login")
  ResponseEntity<UserLoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
    Authentication authenticate = authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authenticate);

    org.springframework.security.core.userdetails.User userDetails =
        (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok(jwtManager.createToken(userDetails.getUsername(), roles));
  }

  @GetMapping("/{id}")
  ResponseEntity<UserDTO> findById(@PathVariable(name = "id") UUID uuid) {
    return ResponseEntity.ok(userService.findById(uuid));
  }

  @GetMapping("find-all")
  ResponseEntity<Page<UserDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "15") int size) {
    return ResponseEntity.ok(userService.findAll(PageRequest.of(page, size)));
  }

  @DeleteMapping()
  ResponseEntity<Void> deleteUser(@RequestBody UserDTO userDTO) {
    userService.delete(userDTO);
    return ResponseEntity.noContent().build();
  }

  @PutMapping()
  ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO) {
    userService.update(userDTO);
    return ResponseEntity.noContent().build();
  }
}
