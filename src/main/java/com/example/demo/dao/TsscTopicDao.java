package com.example.demo.dao;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscTopic;

@Repository
@Scope("singleton")
public class TsscTopicDao implements ITsscTopicDao{

    @PersistenceContext
    private EntityManager entityManager;
    
	@Override
	public void save(TsscTopic entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TsscTopic entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TsscTopic entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TsscTopic findById(Long codigo) {
		return entityManager.find(TsscTopic.class, codigo);		
	}

	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select t from TsscTopic t";
		return 	entityManager.createQuery(jpql).getResultList();	
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		Query q = entityManager.createQuery("SELECT t "
				+ "FROM TsscTopic t "
				+ "WHERE t.description = :description");
		q.setParameter("description", description);
		return q.getResultList();
	}
	
	@Override
	public List<TsscTopic> findByName(String name) {
		Query q = entityManager.createQuery("SELECT t "
				+ "FROM TsscTopic t "
				+ "WHERE t.name = :name");
		q.setParameter("name", name);
		return q.getResultList();
	}
	
	@Override
	public List<TsscTopic> findByGameSchedule(LocalDate date) {
		Query q = entityManager.createQuery("SELECT t "
				+ "FROM TsscGame g JOIN g.tsscTopic t "
				+ "ON g.scheduledDate = :date "
				+ "ORDER BY g.scheduledTime");
		q.setParameter("date", date);
		return q.getResultList();
	}
}
