package com.example.demo.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscStory;
import com.example.demo.modelo.TsscTopic;

@Repository
@Scope("singleton")
public class TsscStoryDao implements ITsscStoryDao{

    @PersistenceContext
    private EntityManager entityManager;
    
	@Override
	public void save(TsscStory entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TsscStory entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TsscStory entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TsscStory findById(Long codigo) {
		return entityManager.find(TsscStory.class, codigo);		
	}

	@Override
	public List<TsscStory> findAll() {
		String jpql = "Select a from TsscStory a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}

	public Iterable<TsscStory> findByGameId(long id) {
		String jpql = "Select a from TsscStory a where a.";
		return 	entityManager.createQuery(jpql).getResultList();
	}
	
}
