package com.example.demo.servicios;

import com.example.demo.modelo.TsscGroup;
public interface GroupService {

	public TsscGroup createGroup (TsscGroup group);
	public TsscGroup updateGroup (TsscGroup group);
	public TsscGroup getGroup (Long id);
	public void deleteGroup (Long id);
}
