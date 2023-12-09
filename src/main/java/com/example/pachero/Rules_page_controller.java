package com.example.pachero;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Rules_page_controller implements Initializable {
    public AnchorPane rulesPage;
    public Text cherry;
    public ImageView back;
    public Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rulesPage.setOpacity(0);
        getData();
        fadeInTransition();
    }
    private void fadeInTransition(){
        FadeTransition fadeTransitionGamePane = new FadeTransition(Duration.millis(500), rulesPage);
        fadeTransitionGamePane.setFromValue(0);
        fadeTransitionGamePane.setToValue(1);
        fadeTransitionGamePane.play();
    }

    public void getData(){
        try {
            Path path = Paths.get("src/main/resources/Data/CherryCountData.txt");
            Scanner scanner = new Scanner(path);
            int cherryCount=scanner.nextInt();
            cherry.setText(String.valueOf(cherryCount));
            cherry.setLayoutX(1000-(cherry.getBoundsInParent().getMaxX()-cherry.getBoundsInParent().getMinX()));
            scanner.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            cherry.setText(String.valueOf(0));
        }
    }

    @FXML
    private void hoverLoadButton(MouseEvent event){
        back.setEffect(new Glow(1));
    }

    @FXML
    private void hoverLoadButtonExit(MouseEvent event){
        back.setEffect(null);
    }

    @FXML
    private void load_button(ActionEvent event) {
        ClickMusic click= new ClickMusic();
        Thread clickMusic=new Thread(click);
        clickMusic.start();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), rulesPage);
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
