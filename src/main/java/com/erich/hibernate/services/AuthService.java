package com.erich.hibernate.services;

import com.erich.hibernate.dto.auth.LoginDto;
import com.erich.hibernate.dto.auth.RegistroDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String registro(RegistroDto registroDto);
}
