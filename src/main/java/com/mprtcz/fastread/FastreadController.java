package com.mprtcz.fastread;

import com.mprtcz.fastread.reader.Labeler;
import com.mprtcz.fastread.reader.Reader;
import com.mprtcz.fastread.reader.SyllablesResovler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;


/**
 * @author mprtcz
 */
public class FastreadController {

    @FXML
    private TextFlow mainTextFlow;

    @FXML
    private Label previousWordLabel;
    @FXML
    private Label mainLabel;
    @FXML
    private Label nextWordLabel;

    @FXML
    private Button startButton;

    @FXML
    private ProgressBar textProgressBar;

    @FXML
    private Slider speedSlider;

    private Reader reader;

    private Labeler labeler;

    @FXML
    void onSpeedSliderDragDetected(MouseEvent event) {
        reader.changeDelay(speedSlider.getMax() - speedSlider.getValue());
    }

    @FXML
    void onSpeedSliderDragDone(MouseEvent event) {
        reader.changeDelay(speedSlider.getMax() - speedSlider.getValue());
    }

    @FXML
    void onStartButtonClicked(ActionEvent event)  {
        labeler = new Labeler(previousWordLabel, mainLabel, nextWordLabel);
        reader = new Reader(null, new SyllablesResovler(), labeler);
        new Thread(reader::startReading).start();
    }

    @FXML
    void onStopButtonClicked(ActionEvent event)  {
        if (reader != null) {
            reader.stop();
        }
    }

}
