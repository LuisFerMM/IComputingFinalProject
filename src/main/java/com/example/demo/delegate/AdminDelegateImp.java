package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.TsscAdmin;

@Component
public class AdminDelegateImp implements AdminDelegate{

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8081/";
	
	public AdminDelegateImp() {
		restTemplate = new RestTemplate();
	}

	@Override
	public TsscAdmin GET_Admin(int id) {		
		TsscAdmin tsscAdmin = restTemplate.getForObject(SERVER+"admin/"+id, TsscAdmin.class);
		return tsscAdmin;
	}

	@Override
	public TsscAdmin POST_Admin(TsscAdmin POSTAdmin) {
		TsscAdmin tsscAdmin = restTemplate.postForObject(SERVER+"admin", POSTAdmin , TsscAdmin.class);
		return tsscAdmin;
	}

	@Override
	public void PUT_Admin(TsscAdmin PUTAdmin) {
		restTemplate.put(SERVER+"admin/"+PUTAdmin.getId(), PUTAdmin, TsscAdmin.class);		
	}

	@Override
	public void DELETE_Admin(TsscAdmin DELETEAdmin) {
		restTemplate.delete(SERVER+"admin/"+DELETEAdmin.getId());
	}

}
