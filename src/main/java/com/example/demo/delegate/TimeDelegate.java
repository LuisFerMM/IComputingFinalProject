package com.example.demo.delegate;

import com.example.demo.modelo.TsscTimecontrol;

public interface TimeDelegate {
	
	/*
	 * Read and get an Story
	 */
	public TsscTimecontrol GET_Time(long id);
	
	/*
	 * Obtiene todas las stories asociadas a un game
	 */
	public Iterable<TsscTimecontrol> GET_times(long id);
	
	/*
	 * Update/Replace an Story
	 */
	public void PUT_Time(TsscTimecontrol PUTTime);
	
	/*
	 * Delete an Story 
	 */
	public void DELETE_Time(TsscTimecontrol DELETETime);

	/*
	 * Actualiza un Time
	 */
	public TsscTimecontrol POST_Time(TsscTimecontrol POSTTime, long idG);

}
