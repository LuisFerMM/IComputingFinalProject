package com.example.demo.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscAdmin;
import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscStory;
import com.example.demo.modelo.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TsscTimecontrolDao implements ITsscTimecontrolDao{

    @PersistenceContext
    private EntityManager entityManager;
    
	@Override
	public void save(TsscTimecontrol entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TsscTimecontrol entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TsscTimecontrol entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TsscTimecontrol findById(Long codigo) {
		return entityManager.find(TsscTimecontrol.class, codigo);		
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String jpql = "Select tc from TsscTimecontrol tc";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
