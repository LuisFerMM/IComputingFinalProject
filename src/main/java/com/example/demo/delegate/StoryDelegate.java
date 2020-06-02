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
	 * Update/Replace an Story
	 */
	public void PUT_Story(TsscStory PUTStory);
	
	/*
	 * Delete an Story 
	 */
	void DELETE_Story(long id);

	TsscStory POST_Story(TsscStory POSTStory, long idG);

	

	
}
