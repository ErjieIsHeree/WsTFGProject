package com.erjieisheree.yonuncaconia.controller;

public class GameCreate {

    private static final GameCreate instance = new GameCreate();

    private String userInput;
    private int selectedQty;
    private boolean isPredefined;
    private String predefinedOption;

    private GameCreate() {
    }

    public static GameCreate getInstance() {
        return instance;
    }

    public void loadGame(String userInput, String predefinedOption, int selectedQty, boolean isPredefined) {
        this.userInput = userInput;
        this.predefinedOption = predefinedOption;
        this.selectedQty = selectedQty;
        this.isPredefined = isPredefined;

        // Aquí va la lógica de preparación del juego
    }

    private void getAIResponse(String userPrompt) {

    }

}
