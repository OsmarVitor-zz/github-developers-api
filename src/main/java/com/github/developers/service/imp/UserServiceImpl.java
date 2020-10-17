package com.github.developers.service.imp;

import com.github.developers.model.User;
import com.github.developers.model.dto.UserDTO;
import com.github.developers.repository.UserRepository;
import com.github.developers.service.UserService;
import com.github.developers.utils.ConverterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService<UserDTO> {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User create(UserDTO dto) {
    //tratar exception caso o usuario ja tenha cadastro
    User user = User.newBuilder().name(dto.getName()).birthDate(dto.getBirthDate()).email(dto.getEmail()).build();
    return userRepository.save(user);
  }

  @Override
  public UserDTO update(UserDTO dto) {
    return null;
  }

  @Override
  public void delete(UserDTO dto) {

  }

  @Override
  public Page<UserDTO> findAll(Pageable pageable) {
    return userRepository.findAll(pageable).map(user -> ConverterDTO.convertToDTO(user));
  }

  @Override
  public UserDTO findByEmail(String email) {
    return null;
  }
}
