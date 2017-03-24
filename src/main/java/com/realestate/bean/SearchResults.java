package com.realestate.bean;

public class SearchResults {
	private Message message;

	private Response response;

	private Request request;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "ClassPojo [message = " + message + ", response = " + response
				+ ", request = " + request + "]";
	}
}
