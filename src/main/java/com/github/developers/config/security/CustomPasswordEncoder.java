package com.github.developers.config.security;

import com.github.developers.utils.HashUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence charSequence) {
    return HashUtils.getSecureHashString(charSequence.toString());
  }

  @Override
  public boolean matches(CharSequence charSequence, String s) {
    return true;
  }

  //  @Override
  //  public boolean matches(CharSequence charSequence, String encodedPassword) {
  //    return HashUtils.getSecureHashString(charSequence.toString()).equals(encodedPassword);
  //  }
}
