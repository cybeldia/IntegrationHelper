package com.shaffer.integrationhelper.events;

import java.util.List;

import org.springframework.context.ApplicationEvent;

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
