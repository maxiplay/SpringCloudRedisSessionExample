package com.worldline.springcloudarchetype.authredisbased.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.worldline.springcloudarchetype.userdatamodel.model.Right;
import com.worldline.springcloudarchetype.userdatamodel.model.User;
import com.worldline.springcloudarchetype.userdatamodel.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private boolean enabled = true;
	private boolean accountNonExpired = true;
	private boolean credentialsNonExpired = true;
	private boolean accountNonLocked = true;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		Optional<User> userRetrieved = userRepository.findOneByLogin(login);
		User user = userRetrieved
				.orElseThrow(() -> new UsernameNotFoundException("User '"
						+ login + "' could not be found"));

		Set<Right> rights = user.getRole().getRights();

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(
				rights.size());

		for (Right right : rights) {
			grantedAuthorities
					.add(new SimpleGrantedAuthority(right.toString()));
		}

		return new org.springframework.security.core.userdetails.User(
				user.getLogin(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuthorities);
	}

}