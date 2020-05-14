package com.example.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TsscAdminDao;
import com.example.demo.modelo.TsscAdmin;
import com.example.demo.servicios.AdminService;
import com.example.demo.servicios.AdminServiceImp;

import lombok.extern.java.Log;

@Log
@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	@Autowired
	private TsscAdminDao adminR;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TsscAdmin admin = adminR.findByUser(username).get(0);
		if (admin != null) {
			User.UserBuilder builder = User.withUsername(username).password(admin.getPassword()).roles(admin.getSuperAdmin());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("Admin not found.");
		}
	}
}