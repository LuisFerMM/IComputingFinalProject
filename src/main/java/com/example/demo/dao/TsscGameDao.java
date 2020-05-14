package com.example.demo.dao;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTopic;

@Repository
@Scope("singleton")
public class TsscGameDao implements ITsscGameDao{

    @PersistenceContext
    private EntityManager entityManager;
    
	@Override
	public void save(TsscGame entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TsscGame entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TsscGame entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TsscGame findById(Long codigo) {
		return entityManager.find(TsscGame.class, codigo);		
	}

	@Override
	public List<TsscGame> findAll() {
		String jpql = "Select g from TsscGame g";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
	@Override
	public List<TsscGame> findByTopicName(String name) {
		Query q = entityManager.createQuery("SELECT g "
				+ "FROM TsscGame g JOIN g.tsscTopic t "
				+ "ON t.name = :name");
		q.setParameter("name", name);
		return q.getResultList();
	}
	
	@Override
	public List<TsscGame> findByTopicId(long id) {
		Query q = entityManager.createQuery("SELECT g "
				+ "FROM TsscGame g "
				+ "WHERE g.tsscTopic.id = :id");
		q.setParameter("id", id);
		return q.getResultList();
	}
	
	@Override
	public List<TsscGame> findByTopicDescription(String description) {
		Query q = entityManager.createQuery("SELECT g "
				+ "FROM TsscGame g JOIN g.tsscTopic t "
				+ "ON t.description = :description");
		q.setParameter("description", description);
		return  q.getResultList();
	}
	
	@Override
	public List<TsscGame> findByDateRange(LocalDate schIniDate, LocalDate schLstDate) {
		Query q = entityManager.createQuery("SELECT g "
				+ "FROM TsscGame g "
				+ "WHERE g.scheduledDate <= :lstD "
				+ "AND g.scheduledDate >= :iniD");
		q.setParameter("lstD", schLstDate);
		q.setParameter("iniD", schIniDate);
		return q.getResultList();
	}
	
	@Override
	public List<TsscGame> findByDateAndTimeRange(LocalDate schDate, LocalTime schIniTime, LocalTime schLstTime) {
		Query q = entityManager.createQuery("SELECT g "
				+ "FROM TsscGame g "
				+ "WHERE g.scheduledDate = :date "
				+ "AND g.scheduledTime >= :iniT "
				+ "AND g.scheduledTime <= :lstT");
		q.setParameter("date", schDate);
		q.setParameter("iniT", schIniTime);
		q.setParameter("lstT", schLstTime);
		return q.getResultList();
	}
	
	@Override
	public List<TsscGame> findByDateAndStrangeRestrictions(LocalDate date) {
		Query q = entityManager.createQuery("SELECT g "
				+ "FROM TsscGame g "
				+ "WHERE g.scheduledDate = :date "
				+ "AND (size(g.tsscStories)<10 "
				+ "OR size(g.tsscTimecontrols) = 0)");
		q.setParameter("date", date);
		return q.getResultList();
	}
}
