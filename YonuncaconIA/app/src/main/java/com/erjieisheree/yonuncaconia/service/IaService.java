package com.erjieisheree.yonuncaconia.service;

import java.util.List;

public interface IaService {

    /**
     * Obtains the answer in a Map<String, Object> format.
     * The only key is "f", which contains an array of strings with the answer.
     * <p>
     * <b>IMPORTANT</b> in order to work right it needs an String with JSON format.
     * The preferred format is {"Topic": "user_input", "Style": "user_input", "Quantity": "user_input"}
     *
     * @param topic Topic of the answer
     * @param style Style of the answer
     * @param quantity Quantity of answers needed
     * @return Map<String, Object>
     */
    List<String> getPhrases(String topic, String style, String quantity);
}
