package com.github.developers.service.impl;

import com.github.developers.model.dto.UserDTO;
import com.github.developers.repository.UserRepository;
import com.github.developers.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Autowired private UserService<UserDTO> userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDTO userDTO = userService.findByEmail(email);
    List<GrantedAuthority> authorities =
        Arrays.asList(new SimpleGrantedAuthority("ROLE " + userDTO.getRole().name()));
    org.springframework.security.core.userdetails.User userDetails =
        new org.springframework.security.core.userdetails.User(
            userDTO.getEmail(), userDTO.getPassword(), authorities);
    return userDetails;
  }
}
