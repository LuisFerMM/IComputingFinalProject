package com.example.demo.dao;


import java.util.List;

import com.example.demo.modelo.TsscAdmin;

public interface ITsscAdminDao {

	public void save(TsscAdmin entity);
	public void update(TsscAdmin entity);
	public void delete(TsscAdmin entity);
	public TsscAdmin findById(Long codigo);
	public List<TsscAdmin> findAll();
	List<TsscAdmin> findByUser(String user);
}
