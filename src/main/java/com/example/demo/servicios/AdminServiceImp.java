package com.example.demo.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.TsscAdmin;
import com.example.demo.repositorios.AdminRepository;
@Service
public class AdminServiceImp implements AdminService{

	public static final String SUPER_ADMIN= "superadmin";
	public static final String ADMIN= "admin";
	
	@Autowired
	AdminRepository adminR;
	
	@Override
	public void save(TsscAdmin admin) {
		adminR.save(admin);
	}

	@Override
	public Optional<TsscAdmin> findById(long id) {
		return adminR.findById(id);
	}

	@Override
	public Iterable<TsscAdmin> findAll() {
		return adminR.findAll();
	}

	@Override
	public void delete(TsscAdmin user) {
		adminR.delete(user);
	}


}
