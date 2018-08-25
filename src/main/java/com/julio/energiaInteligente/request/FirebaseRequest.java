package com.julio.energiaInteligente.request;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
				URL url = new URL(endpoint);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();

				con.setRequestMethod("POST");
				con.setRequestProperty("Content-type", "application/json");
				con.setRequestProperty("Authorization", authorization);
				con.setDoInput(true);
				con.setDoOutput(true);

				Gson gson = new Gson();

				String json = gson.toJson(new FirebaseRequestDTO(mensagem, titulo, dispositivo.getIdDispositivo()));

				OutputStreamWriter wr = null;
				wr = new OutputStreamWriter(con.getOutputStream());
				wr.write(json);
				wr.flush();
			}
		}
	}
}
