package com.erich.hibernate.security;

import com.erich.hibernate.entity.auth.Username;
import com.erich.hibernate.exception.NotFoundException;
import com.erich.hibernate.repository.UsernameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class CustomUserDetails {
    private final UsernameRepository usernameRepository;

    public CustomUserDetails(UsernameRepository usernameRepository) {
        this.usernameRepository = usernameRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return nombreOrEmail -> {
            Username username1  = usernameRepository.findByNombreOrEmail(nombreOrEmail,nombreOrEmail).orElseThrow(() ->
                    new NotFoundException("Username no encontrado con su nombre o email")) ;
            Set<SimpleGrantedAuthority> authorities = username1.getRoles()
                    .stream().map(u -> new SimpleGrantedAuthority(u.getAuthority()))
                    .collect(Collectors.toSet());
            return new User(nombreOrEmail,username1.getPassword(),authorities);
        };
    }
}
