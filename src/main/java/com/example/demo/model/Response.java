package com.example.demo.model;

public class Response {

	Integer id;
	String response;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Response() {

	}

	public Response(Integer id, String response) {
		super();
		this.id = id;
		this.response = response;
	}

}
