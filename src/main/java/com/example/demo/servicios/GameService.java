package com.example.demo.servicios;

import com.example.demo.modelo.TsscGame;
public interface GameService {

	public TsscGame createGame (TsscGame game);
	public TsscGame createGameWithTopic (TsscGame game, Long idTopic);
	public TsscGame createGameWithTopic2 (TsscGame game, Long idTopic);
	public TsscGame getGame (Long game);
	public TsscGame updateGame (TsscGame game);
	public void delete (TsscGame game);
	public Iterable<TsscGame> findAll();
	TsscGame updateGameWithTopic2(TsscGame game, Long idTopic);
}
