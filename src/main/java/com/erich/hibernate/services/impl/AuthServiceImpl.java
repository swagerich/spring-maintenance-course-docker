package com.erich.hibernate.services.impl;

import com.erich.hibernate.dto.auth.LoginDto;
import com.erich.hibernate.dto.auth.RegistroDto;
import com.erich.hibernate.entity.auth.Role;
import com.erich.hibernate.entity.auth.Username;
import com.erich.hibernate.exception.NotFoundException;
import com.erich.hibernate.exception.ResourceException;
import com.erich.hibernate.repository.RoleRepository;
import com.erich.hibernate.repository.UsernameRepository;
import com.erich.hibernate.security.jwt.JwtTokenProvider;
import com.erich.hibernate.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UsernameRepository userRepo;

    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getNombreOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtTokenProvider.generateToken(authenticate);
    }

    @Override
    public String registro(RegistroDto registroDto) {
        if (userRepo.existsByEmail(registroDto.getEmail())) {
            throw new ResourceException("Upss,El nombre " + registroDto.getEmail() + " ya existe");
        }
        if (userRepo.existsByNombre(registroDto.getNombre())) {
            throw new ResourceException("Upss,El nombre " + registroDto.getNombre() + " ya existe");
        }

        Username user = Username.builder()
                .nombre(registroDto.getNombre())
                .apellido(registroDto.getApellido())
                .email(registroDto.getEmail())
                .password(passwordEncoder.encode(registroDto.getPassword()))
                .build();
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepo.findByAuthority("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("No se encontro el nombre del Rol"));
        roles.add(userRole);
        user.setRoles(roles);
        userRepo.save(user);

        return "User Registrado Satisfactoriamente!!!!";
    }
}
