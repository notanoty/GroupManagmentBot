package org.notanoty.HttpRequests;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientSingleton {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // GET Request
    public static String sendGetRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // POST Request
    public static String sendPostRequest(String url, Object requestBody) throws Exception {
        String jsonRequest = OBJECT_MAPPER.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // PUT Request
    public static String sendPutRequest(String url, Object requestBody) throws Exception {
        String jsonRequest = OBJECT_MAPPER.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonRequest, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // DELETE Request
    public static String sendDeleteRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

