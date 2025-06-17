package com.erjieisheree.yonuncaconia.service.apis;

import com.erjieisheree.yonuncaconia.service.IaService;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DeepseekApi implements IaService {

    @Override
    public List<String> getPhrases(String topic, String style, String quantity) {

        String info = "{\"Topic\": \"" + topic + "\", \"Style\": \"" + style + "\", \"Quantity\": \"" + quantity + "\"}";
        JsonNode root = null;
        List<String> answer = new ArrayList<>();

        try {
            URL url = new URL("http://10.0.2.2:5000/deepseek");
            InputStream deepseekStream = APIsUtils.getStream(url, info, "POST");
            root = APIsUtils.getResponse(deepseekStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (root == null) {
            throw new IllegalStateException();
        }

        JsonNode phrases = root.get("f");
        for (JsonNode phrase : phrases) {
            answer.add(phrase.asText());
        }

        return answer;
    }

    
}
