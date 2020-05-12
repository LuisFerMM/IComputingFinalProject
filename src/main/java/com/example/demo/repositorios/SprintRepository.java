package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscSprint;

@Repository
public interface SprintRepository extends CrudRepository<TsscSprint, Long>{

}
