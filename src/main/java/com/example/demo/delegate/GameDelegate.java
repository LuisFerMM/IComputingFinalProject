package com.example.demo.delegate;

import java.net.URISyntaxException;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTopic;

public interface GameDelegate {

	/*
	 * Read and get an Game
	 */
	public TsscGame GET_Game(long id);
	
	/*
	 * Read and get the Topic of a Game
	 */
	public TsscTopic GET_TopicGame(long idGame, long idTopic);
	
	/*
	 *  Create an Game from a topic
	 */
	public TsscGame POST_GameWithTopic(TsscGame POSTGame, long idTopic); 
	
	/*
	 * Obtener todos los juegos
	 */
	public Iterable<TsscGame> GET_Games();
	
	/*
	 * Obtener todos los topics de un juego
	 */
	public Iterable<TsscTopic> GET_TopicsGame();
	
	/*
	 * Update/Replace the Topic of a Game
	 */
	public void PUT_TopicGame(TsscTopic PUTTopic, TsscGame PUTGame);
	
	/*
	 * Create an Game
	 */
	public TsscGame POST_Game(TsscGame POSTGame) throws URISyntaxException;
	
	/*
	 * Update/Replace an Game
	 */
	public void PUT_Game(TsscGame PUTGame);
	
	/*
	 * Delete an Game 
	 */
	public void DELETE_Game(TsscGame DELETEGame);
	
}
