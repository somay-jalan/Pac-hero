package com.example.pachero;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
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

public class Load_page_controller implements Initializable {
    public Button gamePage;
    public AnchorPane loadPage;
    public Button rules;
    public Button shop;
    public Text rulesText;
    public Text shopText;
    public Text highscore;
    public Text cherry;
    public Text highscoreText;
    public Text cherryText;
    public Text lastScore;
    public Text lastScoreText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage.setOpacity(0);
        getData();
        fadeInTransition();
    }

    private void fadeInTransition(){
        FadeTransition fadeTransitionGamePane = new FadeTransition(Duration.millis(500), loadPage);
        fadeTransitionGamePane.setFromValue(0);
        fadeTransitionGamePane.setToValue(1);
        fadeTransitionGamePane.play();
    }
    @FXML

    public void getData(){
        try {
            Path path = Paths.get("src/main/resources/Data/HighScoreData.txt");
            Scanner scanner = new Scanner(path);
            highscore.setText(String.valueOf(scanner.nextInt()));
            highscore.setLayoutX(1000-(highscore.getBoundsInParent().getMaxX()-highscore.getBoundsInParent().getMinX()));
            scanner.close();
        }catch (Exception e){
            highscore.setText(String.valueOf(0));
        }
        try {
            Path path = Paths.get("src/main/resources/Data/LastScoreData.txt");
            Scanner scanner = new Scanner(path);
            lastScore.setText(String.valueOf(scanner.nextInt()));
            lastScore.setLayoutX(1000-(lastScore.getBoundsInParent().getMaxX()-lastScore.getBoundsInParent().getMinX()));
            scanner.close();
        }catch (Exception e){
            lastScore.setText(String.valueOf(0));
        }
        try {
            Path path = Paths.get("src/main/resources/Data/CherryCountData.txt");
            Scanner scanner = new Scanner(path);
            int cherryCount=scanner.nextInt();
            cherry.setText(String.valueOf(cherryCount));
            cherry.setLayoutX(1000-(cherry.getBoundsInParent().getMaxX()-cherry.getBoundsInParent().getMinX()));
            scanner.close();
        }catch (Exception e){
            cherry.setText(String.valueOf(0));
        }
    }

    @FXML
    private void gamePage(ActionEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), loadPage);
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
    public void shopPage(ActionEvent event) throws IOException{
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), loadPage);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event1 -> {
            try {
                loadShopPage(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fadeTransition.play();
    }
    private void loadShopPage(Event event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Shop_page.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void rulesPage(ActionEvent event) throws IOException{
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), loadPage);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event1 -> {
            try {
                loadRulesPage(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fadeTransition.play();
    }
    private void loadRulesPage(Event event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Rules_page.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void hoverHighScoreIcon(MouseEvent mouseEvent){
        Timeline timelineFadeIn=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(highscoreText.opacityProperty(),0)),
                new KeyFrame(Duration.millis(100),new KeyValue(highscoreText.opacityProperty(),1))
        );
        timelineFadeIn.play();
    }
    public void hoverHighScoreIconExit(MouseEvent mouseEvent){
        Timeline timelineFadeOut=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(highscoreText.opacityProperty(),1)),
                new KeyFrame(Duration.millis(100),new KeyValue(highscoreText.opacityProperty(),0))
        );
        timelineFadeOut.play();
    }

    public void hoverCherriesIcon(MouseEvent mouseEvent){
        Timeline timelineFadeIn=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(cherryText.opacityProperty(),0)),
                new KeyFrame(Duration.millis(100),new KeyValue(cherryText.opacityProperty(),1))
        );
        timelineFadeIn.play();
    }
    public void hoverCherriesIconExit(MouseEvent mouseEvent){
        Timeline timelineFadeOut=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(cherryText.opacityProperty(),1)),
                new KeyFrame(Duration.millis(100),new KeyValue(cherryText.opacityProperty(),0))
        );
        timelineFadeOut.play();
    }
    public void hoverLastScoreIcon(MouseEvent mouseEvent){
        Timeline timelineFadeIn=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(lastScoreText.opacityProperty(),0)),
                new KeyFrame(Duration.millis(100),new KeyValue(lastScoreText.opacityProperty(),1))
        );
        timelineFadeIn.play();
    }
    public void hoverLastScoreIconExit(MouseEvent mouseEvent){
        Timeline timelineFadeOut=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(lastScoreText.opacityProperty(),1)),
                new KeyFrame(Duration.millis(100),new KeyValue(lastScoreText.opacityProperty(),0))
        );
        timelineFadeOut.play();
    }

    public void onHoverRules(MouseEvent mouseEvent) {
        rulesText.setEffect(new Glow(1));
    }

    public void onHoverShop(MouseEvent mouseEvent) {
        shopText.setEffect(new Glow(1));
    }

    public void removeHoverRules(MouseEvent mouseEvent) {
        rulesText.setEffect(null);
    }

    public void removeHoverShop(MouseEvent mouseEvent) {
        shopText.setEffect(null);
    }
}
