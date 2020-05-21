package com.example.demo.delegate;

import com.example.demo.modelo.TsscStory;

public interface StoryDelegate {

	/*
	 * Read and get an Story
	 */
	public TsscStory GET_Story(long id);
	
	/*
	 * Obtener todos los Story
	 */
	public Iterable<TsscStory> GET_Stories();
	
	/*
	 * Obtiene todas las stories asociadas a un game
	 */
	public Iterable<TsscStory> GET_GameStories(long id);
	
	/*
	 * Eliminar el story de un juego
	 */
	public void DELETE_StoryGame(long idG, long idS);
	
	/*
	 * Create an Story
	 */
	public TsscStory POST_Story(TsscStory POSTStory);
	
	/*
	 * Update/Replace an Story
	 */
	public void PUT_Story(TsscStory PUTStory);
	
	/*
	 * Delete an Story 
	 */
	public void DELETE_Story(TsscStory DELETEStory);
}
