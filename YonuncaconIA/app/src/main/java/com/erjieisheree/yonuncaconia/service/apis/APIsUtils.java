package com.erjieisheree.yonuncaconia.service.apis;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.text.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIsUtils {

    public static InputStream getStream(URL url, String send, String method) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");

        if (method.equals("POST") || method.equals("PATCH")) {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = send.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        } else {
            conn.setDoOutput(false);
        }

        int code = conn.getResponseCode();
        return (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();
    }

    public static JsonNode getResponse(InputStream responseStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            String responseJson = StringEscapeUtils.unescapeJava(response.toString());
            if (responseJson.startsWith("\"") && responseJson.endsWith("\"") && responseJson.length() > 1) {
                responseJson = responseJson.substring(1, responseJson.length() - 1);
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
            return mapper.readTree(responseJson);
        }
    }
    
}
