package com.github.developers.utils;

import com.github.developers.model.User;
import com.github.developers.model.dto.UserDTO;

public final class ConverterDTO {

  public static UserDTO convertToDTO(User user){
    return UserDTO.newBuilder().birthDate(user.getBirthDate()).name(user.getName()).email(user.getEmail()).build();
  }
}
