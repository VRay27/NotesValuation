package com.noteanalyzer.bean;

public class Response {
	private Results results;

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ClassPojo [results = " + results + "]";
	}
}
