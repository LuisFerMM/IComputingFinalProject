package com.example.demo.delegate;

import com.example.demo.modelo.TsscStory;

public interface StoryDelegate {

	/*
	 * Read and get an Story
	 */
	public TsscStory GET_Story(long id);
	
	/*
	 * Obtiene todas las stories asociadas a un game
	 */
	public Iterable<TsscStory> GET_GameStories(long id);
	
	/*
	 * Eliminar el story de un juego
	 */
	public void DELETE_StoryGame(long idG, long idS);
	
	/*
	 * Update/Replace an Story
	 */
	public void PUT_Story(TsscStory PUTStory);
	
	/*
	 * Delete an Story 
	 */
	public void DELETE_Story(TsscStory DELETEStory);

	TsscStory POST_Story(TsscStory POSTStory, long idG);
}
