package br.senai.sc.edu.aulaapi.service;

import br.senai.sc.edu.aulaapi.model.Usuario;
import br.senai.sc.edu.aulaapi.model.dto.NovoUsuario;
import br.senai.sc.edu.aulaapi.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvar(NovoUsuario novoUsuario){
        Usuario usuario = new Usuario();
        usuario.setNome(novoUsuario.getNome());
        usuario.setEmail(novoUsuario.getEmail());
        usuario.setPermissao(novoUsuario.getPermissao());
        String senhaCrypt = passwordEncoder.encode(novoUsuario.getSenha());
        usuario.setSenha(senhaCrypt);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUsuarioAutenticacao(String email){
        return usuarioRepository.findByEmail(email);
    }


}
