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
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.media.MediaPlayer;

import java.io.File;
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
        ClickMusic click= new ClickMusic();
        Thread clickMusic=new Thread(click);
        clickMusic.start();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), landing_page);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event1 -> {
            try {
                loadLoadPage(event);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        fadeTransition.play();
    }
    private void loadLoadPage(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Load_page.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}