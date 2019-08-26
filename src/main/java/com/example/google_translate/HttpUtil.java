package com.example.google_translate;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class HttpUtil {
	
	private static OkHttpClient client;
	
	public static String get(String url) throws IOException {
		Request.Builder builder = new Request.Builder();
		builder.url(url);
		builder.method("GET", null);
		OkHttpClient client = getOkHttpClient();
		Response response = client.newCall(builder.build()).execute();
		return response.body().string();
	}
	
	public static OkHttpClient getOkHttpClient() {
		if (client == null) {
			client = new OkHttpClient.Builder().build();
		}
		return client;
	}
}
