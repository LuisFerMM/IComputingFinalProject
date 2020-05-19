package com.example.demo.delegate;

import com.example.demo.modelo.TsscAdmin;

public interface AdminDelegate {

	/*
	 * Read and get an Admin
	 */
	public TsscAdmin GET_Admin(int id);
	
	/*
	 * Create an Admin
	 */
	public TsscAdmin POST_Admin(TsscAdmin POSTAdmin);
	
	/*
	 * Update/Replace an Admin
	 */
	public void PUT_Admin(TsscAdmin PUTAdmin);
	
	/*
	 * Delete an Admin 
	 */
	public void DELETE_Admin(TsscAdmin DELETEAdmin);
	
}
