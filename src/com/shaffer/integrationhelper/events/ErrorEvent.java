package com.shaffer.integrationhelper.events;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class ErrorEvent extends ApplicationEvent {
	
	private List<String> error;

	public ErrorEvent(Object source, List<String> error) {
		super(source);
		this.error = error;
	}
	
	public List<String> getError() {
		if(error.isEmpty()) {
			error.add("No errors found in file");
		}
		return error;
	}

}
