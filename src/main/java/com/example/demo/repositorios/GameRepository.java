package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.TsscGame;

@Repository
public interface GameRepository extends CrudRepository<TsscGame, Long>{

}
