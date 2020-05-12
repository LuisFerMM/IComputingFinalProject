package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscAdmin;
@Repository
public interface AdminRepository extends CrudRepository<TsscAdmin, Long>{

	List<TsscAdmin> findByUser(String user);
	List<TsscAdmin> findBySuperAdmin(String type);
}
