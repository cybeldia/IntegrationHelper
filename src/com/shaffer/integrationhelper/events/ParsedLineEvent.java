package com.shaffer.integrationhelper.events;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.shaffer.integrationhelper.model.InCodeBenefit;
import com.shaffer.integrationhelper.model.InCodeEmployee;
import com.shaffer.integrationhelper.service.impl.ProcessorThread;

public class ParsedLineEvent extends ApplicationEvent {
	private List<?> parsedLines;


	public ParsedLineEvent(Object source, List<?> parsedLines) {
		super(source);
		this.parsedLines = parsedLines;
	}


	public List<?> getLines() {
		return parsedLines;
	}

}
