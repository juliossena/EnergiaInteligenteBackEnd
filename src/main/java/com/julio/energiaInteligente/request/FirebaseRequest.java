package com.julio.energiaInteligente.request;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.julio.energiaInteligente.domain.Dispositivos;
import com.julio.energiaInteligente.request.dto.FirebaseRequestDTO;

public class FirebaseRequest {

	public static String endpoint = "https://fcm.googleapis.com/fcm/send";
	public static String authorization = "key=AAAAIHYulMw:APA91bHkrlL6i7RHXBHRqyEjSvxbUL_84Zzq-0HYSE1gk09K_yMzqqfmXLEo0K-HXH3MWhonYnKAdH3Cf6DD8_-fSO-UDFmeqSwqyPC1CmxWqZtpE-LZ3AMzepinf8Eabvc1mWrJDo4Z";

	public static void enviarMensagens(String titulo, String mensagem, List<Dispositivos> dispositivos)
			throws IOException {

		for (Dispositivos dispositivo : dispositivos) {
			if (dispositivo.isAtivo()) {
				String postUrl = endpoint;
				Gson gson = new Gson();
				String json = gson.toJson(new FirebaseRequestDTO(mensagem, titulo, dispositivo.getIdDispositivo()));
				
				HttpClient httpClient = HttpClientBuilder.create().build();
				HttpPost post = new HttpPost(postUrl);
				StringEntity postingString = new StringEntity(json);// gson.tojson() converts your pojo to json
				post.setEntity(postingString);
				post.setHeader("Content-type", "application/json");
				post.setHeader("Authorization", authorization);
				/*HttpResponse response = */
				httpClient.execute(post);
			}
		}
	}
}
