package com.dms.dmsmoneyapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dms.dmsmoneyapi.model.Usuario;
import com.dms.dmsmoneyapi.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		Usuario usuario = usuarioOptional
				.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorreta"));

		return new User(email, usuario.getSenha(), gettingPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> gettingPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authority = new HashSet<>();

		usuario.getPermissoes().forEach(p -> authority.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));

		return authority;
	}

}
