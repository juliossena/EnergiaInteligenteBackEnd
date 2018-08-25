package com.julio.energiaInteligente.request.dto;

import java.io.Serializable;

public class FirebaseRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private FirebaseRequestNotificationDTO notification;
	private String to;

	public FirebaseRequestDTO() {

	}

	public FirebaseRequestDTO(String body, String title, String to) {
		super();
		this.notification = new FirebaseRequestNotificationDTO(body, title);
		this.to = to;
	}

	public FirebaseRequestNotificationDTO getNotification() {
		return notification;
	}

	public void setNotification(FirebaseRequestNotificationDTO notification) {
		this.notification = notification;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
