package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscStory;
@Repository
public interface StoryRepository extends CrudRepository<TsscStory, Long>{

}
