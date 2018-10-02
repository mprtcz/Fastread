package com.mprtcz.fastread;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;


/**
 * @author mprtcz
 */
public class FastredController {

    @FXML
    private TextFlow mainTextFlow;

    @FXML
    private ProgressBar textProgressBar;

    @FXML
    private Slider speedSlider;

    @FXML
    void onSpeedSliderDragDetected(MouseEvent event) {

    }

}
