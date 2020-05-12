package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscTopic;
@Repository
public interface TopicRepository extends CrudRepository<TsscTopic, Long>{

}
