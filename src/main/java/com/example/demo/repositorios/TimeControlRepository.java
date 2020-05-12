package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscTimecontrol;
@Repository
public interface TimeControlRepository extends CrudRepository<TsscTimecontrol, Long>{

}
