package com.example.demo.servicios;

import java.util.Optional;

import com.example.demo.modelo.TsscAdmin;

public interface AdminService {

	public void save(TsscAdmin admin);

	public Optional<TsscAdmin> findById(long id);

	public Iterable<TsscAdmin> findAll();

	public void delete(TsscAdmin user);

}
