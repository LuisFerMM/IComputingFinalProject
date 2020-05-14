package com.example.demo.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.TsscAdminDao;
import com.example.demo.modelo.TsscAdmin;
@Service
public class AdminServiceImp implements AdminService{

	public static final String SUPER_ADMIN= "superadmin";
	public static final String ADMIN= "admin";
	
	@Autowired
	TsscAdminDao adminR;
	
	@Override
	@Transactional
	public void save(TsscAdmin admin) {
		adminR.save(admin);
	}

	@Override
	public Optional<TsscAdmin> findById(long id) {
		return Optional.of(adminR.findById(id));
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
