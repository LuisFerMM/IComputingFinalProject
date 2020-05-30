package com.example.demo.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.TsscTimecontrol;
@Component
public class TimeDelegateImp implements TimeDelegate{

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8081/backapi/";
	
	public TimeDelegateImp() {
		restTemplate = new RestTemplate();
	}

	@Override
	public TsscTimecontrol GET_Time(long id) {
		TsscTimecontrol tsscAdmin = restTemplate.getForObject(SERVER+"timelines/"+id, TsscTimecontrol.class);
		return tsscAdmin;
	}

	@Override
	public Iterable<TsscTimecontrol> GET_times(long id) {
		TsscTimecontrol[] tsscTimecontrols = restTemplate.getForObject(SERVER+"games/"+id+"/timelines", TsscTimecontrol[].class);
		List<TsscTimecontrol> liTimecontrols = Arrays.asList(tsscTimecontrols);
		return liTimecontrols;
	}

	@Override
	public void PUT_Time(TsscTimecontrol PUTTime) {
		restTemplate.put(SERVER+"timelines/"+PUTTime.getId(), PUTTime, TsscTimecontrol.class);		
		
	}

	@Override
	public void DELETE_Time(TsscTimecontrol DELETETime) {
		restTemplate.delete(SERVER+"timelines/"+DELETETime.getId());		
	}

	@Override
	public TsscTimecontrol POST_Time(TsscTimecontrol POSTTime, long idG) {
		TsscTimecontrol tsscAdmin = restTemplate.postForObject(SERVER+"games/"+idG+"/timelines", POSTTime , TsscTimecontrol.class);
		return tsscAdmin;
	}

}
