package com.tresfs.gerenciamento.config;

import com.tresfs.gerenciamento.repository.FuncionarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig implements UserDetailsService {

    private final  FuncionarioRepository funcionarioRepository;

    public AuthConfig (FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) funcionarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }


}
