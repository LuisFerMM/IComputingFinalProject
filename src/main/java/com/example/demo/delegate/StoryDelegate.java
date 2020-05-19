package com.example.demo.delegate;

import com.example.demo.modelo.TsscStory;

public interface StoryDelegate {

	/*
	 * Read and get an Story
	 */
	public TsscStory GET_Story(int id);
	
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
