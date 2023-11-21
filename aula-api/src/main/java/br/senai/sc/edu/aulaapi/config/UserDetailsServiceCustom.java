package br.senai.sc.edu.aulaapi.config;

import br.senai.sc.edu.aulaapi.model.Usuario;
import br.senai.sc.edu.aulaapi.service.UsuarioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    private final UsuarioService usuarioService;

    public UserDetailsServiceCustom(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioService.getUsuarioAutenticacao(username);
        if(!usuario.isPresent())
            new UsernameNotFoundException("Usuário não encontrado!");

        return new UserDetailsCustom(usuario);
    }
}
