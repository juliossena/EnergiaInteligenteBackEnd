package com.julio.energiaInteligente.request.dto;

public class FirebaseRequestNotificationDTO {

	private String body;
	private String title;

	public FirebaseRequestNotificationDTO() {
		
	}
	
	public FirebaseRequestNotificationDTO(String body, String title) {
		super();
		this.body = body;
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
