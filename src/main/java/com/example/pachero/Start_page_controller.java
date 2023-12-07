package com.example.pachero;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Start_page_controller implements Initializable {

    public Text dots;
    static Start_animation startAnimation;
    public Text heading;

    public Text eater;
    public Text ghost;
    public AnchorPane landing_page;
    public VBox startpage;
    public Text nextpage;
    public Button next_page;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextOutput textoutput = textOut -> Platform.runLater(() -> dots.setText(textOut));
        startAnimation=new Start_animation(300,textoutput);
    }
    @FXML
    static void dot_animation(){
        Thread thread=new Thread(startAnimation);
        thread.start();

    }

    @FXML
    private void next_button(ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), landing_page);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event1 -> {
            try {
                loadGamePage(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fadeTransition.play();
    }
    private void loadGamePage(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game_page.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        scene.setOnKeyPressed(Game_page_controller.gameLogic::keyboardControl);
        scene.setOnKeyReleased(Game_page_controller.gameLogic::keyboardControl);
        stage.show();
    }
}