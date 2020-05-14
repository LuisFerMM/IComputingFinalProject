package com.example.demo.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscAdmin;
import com.example.demo.modelo.TsscGame;

@Repository
@Scope("singleton")
public class TsscAdminDao implements ITsscAdminDao{

    @PersistenceContext
    private EntityManager entityManager;
    
	@Override
	public void save(TsscAdmin entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TsscAdmin entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TsscAdmin entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TsscAdmin findById(Long codigo) {
		return entityManager.find(TsscAdmin.class, codigo);		
	}

	@Override
	public List<TsscAdmin> findAll() {
		String jpql = "Select a from TsscAdmin a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
	@Override
	public List<TsscAdmin> findByUser(String user) {
		String jpql = "Select a from TsscAdmin a where a.user = :user";
		return 	entityManager.createQuery(jpql).setParameter("user", user).getResultList();
	}
}
