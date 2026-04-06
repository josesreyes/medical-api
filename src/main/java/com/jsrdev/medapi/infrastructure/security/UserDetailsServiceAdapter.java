package com.jsrdev.medapi.infrastructure.security;

import com.jsrdev.medapi.infrastructure.persistence.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var entity = userJpaRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));

        List<SimpleGrantedAuthority> authorities = entity.getProfiles().stream()
                .map(p -> new SimpleGrantedAuthority("ROLE_" + p.getName().toUpperCase()))
                .toList();

        return new User(entity.getLogin(), entity.getPassword(), authorities);
    }
}
