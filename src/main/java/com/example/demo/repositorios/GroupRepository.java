package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscGroup;
@Repository
public interface GroupRepository extends CrudRepository<TsscGroup, Long>{

}
