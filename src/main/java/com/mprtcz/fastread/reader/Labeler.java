package com.mprtcz.fastread.reader;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * @author mprtcz
 */
public class Labeler {
    private Label previousWordLabel;
    private Label mainLabel;
    private Label nextWordLabel;

    public Labeler(Label previousWordLabel, Label mainLabel, Label nextWordLabel) {
        System.out.println("creating with previousWordLabel, mainLabel, nextWordLabel =  "+previousWordLabel+ mainLabel+ nextWordLabel);
        this.previousWordLabel = previousWordLabel;
        this.mainLabel = mainLabel;
        this.nextWordLabel = nextWordLabel;
    }

    public void setWords(String previousWord, String currentWord, String nextWord) {
        Platform.runLater(() -> previousWordLabel.setText(previousWord));
        Platform.runLater(() -> mainLabel.setText(currentWord));
        Platform.runLater(() -> nextWordLabel.setText(nextWord));
    }

    public void setPreviousWord(String previousWord) {
        Platform.runLater(() -> previousWordLabel.setText(previousWord));
    }
    public void setCurrentWord(String currentWord) {
        System.out.println("mainLabel = " + mainLabel);

        Platform.runLater(() -> mainLabel.setText(currentWord));
    }
    public void setNextWord(String nextWord) {
        Platform.runLater(() -> nextWordLabel.setText(nextWord));
    }

    void setOnlyCurrentWord(String currentWord) {
        Platform.runLater(() -> previousWordLabel.setText(""));
        Platform.runLater(() -> mainLabel.setText(currentWord));
        Platform.runLater(() -> nextWordLabel.setText(""));
    }

}
