package com.example.demo.delegate;

import com.example.demo.modelo.TsscTopic;

public interface TopicDelegate {

	/*
	 * Read and get an Topic
	 */
	public TsscTopic GET_Topic(int id);
	
	/*
	 * Create an Topic
	 */
	public TsscTopic POST_Topic(TsscTopic POSTTopic);
	
	/*
	 * Update/Replace an Topic
	 */
	public void PUT_Topic(TsscTopic PUTTopic);
	
	/*
	 * Delete an Topic 
	 */
	public void DELETE_Topic(TsscTopic DELETETopic);
}
