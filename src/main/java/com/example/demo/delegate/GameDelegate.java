package com.example.demo.delegate;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTopic;

public interface GameDelegate {

	/*
	 * Read and get an Game
	 */
	public TsscGame GET_Game(int id);
	
	/*
	 * Read and get the Topic of a Game
	 */
	public TsscTopic GET_TopicGame(int idGame, int idTopic);
	
	/*
	 *  Create an Game from a topic
	 */
	public TsscGame POST_GameWithTopic(TsscGame POSTGame); 
	
	/*
	 * Update/Replace the Topic of a Game
	 */
	public void PUT_TopicGame(TsscTopic PUTTopic, TsscGame PUTGame);
	
	/*
	 * Create an Game
	 */
	public TsscGame POST_Game(TsscGame POSTGame);
	
	/*
	 * Update/Replace an Game
	 */
	public void PUT_Game(TsscGame PUTGame);
	
	/*
	 * Delete an Game 
	 */
	public void DELETE_Game(TsscGame DELETEGame);
	
}
