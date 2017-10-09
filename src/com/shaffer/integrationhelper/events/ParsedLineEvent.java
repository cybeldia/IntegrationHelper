package com.shaffer.integrationhelper.events;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.shaffer.integrationhelper.model.InCodeEmployee;

public class ParsedLineEvent extends ApplicationEvent {
	private List<InCodeEmployee> parsedLines;

	public ParsedLineEvent(Object source, List<InCodeEmployee> parsedLines) {
		super(source);
		this.parsedLines = parsedLines;
	}
	
	public List<InCodeEmployee> getInCodeLines() {
		return parsedLines;
	}

}
