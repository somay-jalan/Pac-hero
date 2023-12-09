package com.example.pachero;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Shop_page_controller implements Initializable {

    public AnchorPane shopPage;
    public Text cherry;
    public Button backButton;
    public ImageView back;
    public Rectangle pacmanBox;
    public Rectangle pokemonPacmanBox;
    public Rectangle spanishPacmanBox;
    public Rectangle vikingPacmanBox;
    public Rectangle piratePacmanBox;
    public Text pacmanText;
    public Rectangle pacmanTextBox;
    public Text pokemonPacmanText;
    public Rectangle pokemonPacmanTextBox;
    public Text spanishPacmanText;
    public Rectangle spanishPacmanTextBox;
    public Text vikingPacmanText;
    public Rectangle vikingPacmanTextBox;
    public Text piratePacmanText;
    public Rectangle piratePacmanTextBox;
    public Rectangle pacmanTextBoxTransparent;
    public Rectangle pokemonPacmanTextBoxTransparent;
    public Rectangle vikingPacmanTextBoxTransparent;
    public Rectangle spanishPacmanTextBoxTransparent;
    public Rectangle piratePacmanTextBoxTransparent;
    public ImageView pacmanImage;
    public ImageView pokemonPacmanImage;
    public ImageView spanishPacmanImage;
    public ImageView vikingPacmanImage;
    public ImageView piratePacmanImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shopPage.setOpacity(0);
        getData();
        fadeInTransition();
    }
    private void fadeInTransition(){
        FadeTransition fadeTransitionGamePane = new FadeTransition(Duration.millis(500), shopPage);
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
            cherry.setText(String.valueOf(0));
        }
        try {
            Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
            Scanner scanner = new Scanner(path);
            for(int i=0;i<5;i++) {
                int value=Integer.parseInt(scanner.nextLine());
                String print_stat="BUY";
                if(value==-1){
                    print_stat="BUY";
                }else if(value==0){
                    print_stat="EQUIP";
                }else if(value==1) {
                    print_stat="EQUIPPED";
                }
                if(i==0){
                    pacmanText.setText(print_stat);
                    pacmanText.setWrappingWidth(pacmanBox.getWidth());
                    pacmanText.setLayoutX(pacmanBox.getLayoutX()+2);
                }else if(i==1){
                    pokemonPacmanText.setText(print_stat);
                    pokemonPacmanText.setWrappingWidth(pokemonPacmanBox.getWidth());
                    pokemonPacmanText.setLayoutX(pokemonPacmanBox.getLayoutX()+2);

                }else if(i==2){
                    spanishPacmanText.setText(print_stat);
                    spanishPacmanText.setWrappingWidth(spanishPacmanBox.getWidth());
                    spanishPacmanText.setLayoutX(spanishPacmanBox.getLayoutX()+2);

                } else if (i==3) {
                    vikingPacmanText.setText(print_stat);
                    vikingPacmanText.setWrappingWidth(vikingPacmanBox.getWidth());
                    vikingPacmanText.setLayoutX(vikingPacmanBox.getLayoutX()+2);

                }else {
                    piratePacmanText.setText(print_stat);
                    piratePacmanText.setWrappingWidth(piratePacmanBox.getWidth());
                    piratePacmanText.setLayoutX(piratePacmanBox.getLayoutX()+2);

                }
            }
            scanner.close();
        }catch (Exception e){
            System.out.println("An error occured");
        }
    }
//    private void serializeCostumeAtStart(){
//        try {
//            ImageView costumeImage = new ImageView();
//            Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
//            Scanner scanner = new Scanner(path);
//            for(int i=0;i<5;i++){
//                int value=Integer.parseInt(scanner.nextLine());
//                if(value==1){
//                    Costume pacmanCostume;
//                    if(i==0){
//
//                    }
//                }
//            }
//        }catch (Exception e){
//            System.out.println("An error occured");
//        }
//
//    }
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
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), shopPage);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event1 -> {
            try {
                loadLoadPage(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
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

    @FXML
    private void hoverPacmanBox(MouseEvent event){
        pacmanBox.setStroke(Color.WHITE);
        pacmanBox.setCursor(Cursor.HAND);
    }

    @FXML
    private void hoverPacmanBoxExit(MouseEvent event){
        pacmanBox.setStroke(Color.BLACK);
    }
    @FXML
    private void hoverPokemonPacmanBox(MouseEvent event){
        pokemonPacmanBox.setStroke(Color.WHITE);
        pokemonPacmanBox.setCursor(Cursor.HAND);
    }

    @FXML
    private void hoverPokemonPacmanBoxExit(MouseEvent event){
        pokemonPacmanBox.setStroke(Color.BLACK);
    }

    @FXML
    private void hoverSpanishPacmanBox(MouseEvent event){
        spanishPacmanBox.setStroke(Color.WHITE);
        spanishPacmanBox.setCursor(Cursor.HAND);
    }

    @FXML
    private void hoverSpanishPacmanBoxExit(MouseEvent event){
        spanishPacmanBox.setStroke(Color.BLACK);
    }

    @FXML
    private void hoverVikingPacmanBox(MouseEvent event){
        vikingPacmanBox.setStroke(Color.WHITE);
        vikingPacmanBox.setCursor(Cursor.HAND);
    }

    @FXML
    private void hoverVikingPacmanBoxExit(MouseEvent event){
        vikingPacmanBox.setStroke(Color.BLACK);
    }

    @FXML
    private void hoverPiratePacmanBox(MouseEvent event){
        piratePacmanBox.setStroke(Color.WHITE);
        piratePacmanBox.setCursor(Cursor.HAND);
    }

    @FXML
    private void hoverPiratePacmanBoxExit(MouseEvent event){
        piratePacmanBox.setStroke(Color.BLACK);
    }


    @FXML
    private void tryButton(MouseEvent event){
        System.out.println("HMM");
    }

    @FXML
    private void hoverPacmanTextBox(MouseEvent event){
        pacmanBox.setStroke(Color.WHITE);
        pacmanTextBox.setFill(Color.WHITE);
        pacmanText.setFill(Color.BLACK);
        pacmanTextBoxTransparent.setCursor(Cursor.HAND);
        pacmanTextBox.setOpacity(1);

    }
    @FXML
    private void hoverPacmanTextBoxExit(MouseEvent event){
        pacmanText.setFill(Color.WHITE);
        pacmanTextBox.setOpacity(0);
    }

    @FXML
    private void hoverPokemonPacmanTextBox(MouseEvent event){
        pokemonPacmanBox.setStroke(Color.WHITE);
        pokemonPacmanTextBox.setFill(Color.WHITE);
        pokemonPacmanText.setFill(Color.BLACK);
        pokemonPacmanTextBoxTransparent.setCursor(Cursor.HAND);
        pokemonPacmanTextBox.setOpacity(1);

    }
    @FXML
    private void hoverPokemonPacmanTextBoxExit(MouseEvent event){
        pokemonPacmanText.setFill(Color.WHITE);
        pokemonPacmanTextBox.setOpacity(0);
    }
    @FXML
    private void hoverSpanishPacmanTextBox(MouseEvent event){
        spanishPacmanBox.setStroke(Color.WHITE);
        spanishPacmanTextBox.setFill(Color.WHITE);
        spanishPacmanText.setFill(Color.BLACK);
        spanishPacmanTextBoxTransparent.setCursor(Cursor.HAND);
        spanishPacmanTextBox.setOpacity(1);

    }
    @FXML
    private void hoverSpanishPacmanTextBoxExit(MouseEvent event){
        spanishPacmanText.setFill(Color.WHITE);
        spanishPacmanTextBox.setOpacity(0);
    }
    @FXML
    private void hoverVikingPacmanTextBox(MouseEvent event){
        vikingPacmanBox.setStroke(Color.WHITE);
        vikingPacmanTextBox.setFill(Color.WHITE);
        vikingPacmanText.setFill(Color.BLACK);
        vikingPacmanTextBoxTransparent.setCursor(Cursor.HAND);
        vikingPacmanTextBox.setOpacity(1);

    }
    @FXML
    private void hoverVikingPacmanTextBoxExit(MouseEvent event){
        vikingPacmanText.setFill(Color.WHITE);
        vikingPacmanTextBox.setOpacity(0);
    }

    @FXML
    private void hoverPiratePacmanTextBox(MouseEvent event){
        piratePacmanBox.setStroke(Color.WHITE);
        piratePacmanTextBox.setFill(Color.WHITE);
        piratePacmanText.setFill(Color.BLACK);
        piratePacmanTextBoxTransparent.setCursor(Cursor.HAND);
        piratePacmanTextBox.setOpacity(1);

    }
    @FXML
    private void hoverPiratePacmanTextBoxExit(MouseEvent event){
        piratePacmanText.setFill(Color.WHITE);
        piratePacmanTextBox.setOpacity(0);
    }
    private void shakeCherry(){
        double cherryLayoutX=cherry.getLayoutX();
        double cherryLayoutY=cherry.getLayoutY();
        Timeline shakeCherry=new Timeline(
                new KeyFrame(Duration.ZERO,new KeyValue(cherry.layoutXProperty(),cherry.getLayoutX()),new KeyValue(cherry.layoutYProperty(),cherry.getLayoutY())),
                new KeyFrame(Duration.millis(100),new KeyValue(cherry.layoutXProperty(),cherryLayoutX-2),new KeyValue(cherry.layoutYProperty(),cherryLayoutY-3)),
                new KeyFrame(Duration.millis(200),new KeyValue(cherry.layoutXProperty(),cherryLayoutX),new KeyValue(cherry.layoutYProperty(),cherryLayoutY))

        );
        shakeCherry.play();
    }

    @FXML
    private void clickedPokemonPacmanTextBox(MouseEvent event){
        ClickMusic click= new ClickMusic();
        Thread clickMusic=new Thread(click);
        clickMusic.start();
        if(pokemonPacmanText.getText().equalsIgnoreCase("buy")){
            if(Integer.parseInt(cherry.getText())<20){
                shakeCherry();
            }else{
                try {
                    ArrayList<Integer> valueArray=new ArrayList<>();
                    Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                    Scanner scanner = new Scanner(path);
                    for(int i=0;i<5;i++){
                        if(i==1){
                            valueArray.add(0);
                        }else {
                            valueArray.add(Integer.parseInt(scanner.nextLine()));
                        }
                    }
                    scanner.close();
                    FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                    for(int i=0;i<5;i++){
                        fileWriter.write(String.valueOf(valueArray.get(i)));
                        fileWriter.write("\n");
                    }
                    fileWriter.close();
                    cherry.setText(String.valueOf(Integer.parseInt(cherry.getText())-20));
                    try {
                        FileWriter myWriter = new FileWriter("src/main/resources/Data/CherryCountData.txt");
                        myWriter.write(cherry.getText());
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }
                    getData();
                }catch (Exception e){
                    System.out.println("An error occurred here");
                }
            }
        }else if(pokemonPacmanText.getText().equalsIgnoreCase("equip")){
            try {
                ArrayList<Integer> valueArray=new ArrayList<>();
                Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                Scanner scanner = new Scanner(path);
                for(int i=0;i<5;i++){
                    int value=Integer.parseInt(scanner.nextLine());
                    if(i==1){
                        valueArray.add(1);
                    }else {
                        if(value==1){
                            valueArray.add(0);
                        }else {
                            valueArray.add(value);
                        }
                    }
                }
                scanner.close();
                FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                for(int i=0;i<5;i++){
                    fileWriter.write(String.valueOf(valueArray.get(i)));
                    fileWriter.write("\n");
                }
                fileWriter.close();
                getData();
            }catch (Exception e){
                System.out.println("An error occurred heree");
            }
        }
    }
    @FXML
    private void clickedPacmanTextBox(MouseEvent event){
        ClickMusic click= new ClickMusic();
        Thread clickMusic=new Thread(click);
        clickMusic.start();
        if(pacmanText.getText().equalsIgnoreCase("buy")){
            if(Integer.parseInt(cherry.getText())<0){
                shakeCherry();
            }else{
                try {
                    ArrayList<Integer> valueArray=new ArrayList<>();
                    Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                    Scanner scanner = new Scanner(path);
                    for(int i=0;i<5;i++){
                        if(i==0){
                            valueArray.add(0);
                        }else {
                            valueArray.add(Integer.parseInt(scanner.nextLine()));
                        }
                    }
                    scanner.close();
                    FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                    for(int i=0;i<5;i++){
                        fileWriter.write(String.valueOf(valueArray.get(i)));
                        fileWriter.write("\n");
                    }
                    fileWriter.close();
                    cherry.setText(String.valueOf(Integer.parseInt(cherry.getText())));
                    cherry.setLayoutX(1000-(cherry.getBoundsInParent().getMaxX()-cherry.getBoundsInParent().getMinX()));
                    try {
                        FileWriter myWriter = new FileWriter("src/main/resources/Data/CherryCountData.txt");
                        myWriter.write(cherry.getText());
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }
                    getData();
                }catch (Exception e){
                    System.out.println("An error occurred here");
                }
            }
        }else if(pacmanText.getText().equalsIgnoreCase("equip")){
            try {
                ArrayList<Integer> valueArray=new ArrayList<>();
                Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                Scanner scanner = new Scanner(path);
                for(int i=0;i<5;i++){
                    int value=Integer.parseInt(scanner.nextLine());
                    if(i==0){
                        valueArray.add(1);
                    }else {
                        if(value==1){
                            valueArray.add(0);
                        }else {
                            valueArray.add(value);
                        }
                    }
                }
                scanner.close();
                FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                for(int i=0;i<5;i++){
                    fileWriter.write(String.valueOf(valueArray.get(i)));
                    fileWriter.write("\n");
                }
                fileWriter.close();
                getData();
            }catch (Exception e){
                System.out.println("An error occurred heree");
            }
        }
    }
    @FXML
    private void clickedSpanishPacmanTextBox(MouseEvent event){
        ClickMusic click= new ClickMusic();
        Thread clickMusic=new Thread(click);
        clickMusic.start();
        if(spanishPacmanText.getText().equalsIgnoreCase("buy")){
            if(Integer.parseInt(cherry.getText())<50){
                shakeCherry();
            }else{
                try {
                    ArrayList<Integer> valueArray=new ArrayList<>();
                    Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                    Scanner scanner = new Scanner(path);
                    for(int i=0;i<5;i++){
                        if(i==2){
                            valueArray.add(0);
                        }else {
                            valueArray.add(Integer.parseInt(scanner.nextLine()));
                        }
                    }
                    scanner.close();
                    FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                    for(int i=0;i<5;i++){
                        fileWriter.write(String.valueOf(valueArray.get(i)));
                        fileWriter.write("\n");
                    }
                    fileWriter.close();
                    cherry.setText(String.valueOf(Integer.parseInt(cherry.getText())-50));
                    try {
                        FileWriter myWriter = new FileWriter("src/main/resources/Data/CherryCountData.txt");
                        myWriter.write(cherry.getText());
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }
                    getData();
                }catch (Exception e){
                    System.out.println("An error occurred here");
                }
            }
        }else if(spanishPacmanText.getText().equalsIgnoreCase("equip")){
            try {
                ArrayList<Integer> valueArray=new ArrayList<>();
                Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                Scanner scanner = new Scanner(path);
                for(int i=0;i<5;i++){
                    int value=Integer.parseInt(scanner.nextLine());
                    if(i==2){
                        valueArray.add(1);
                    }else {
                        if(value==1){
                            valueArray.add(0);
                        }else {
                            valueArray.add(value);
                        }
                    }
                }
                scanner.close();
                FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                for(int i=0;i<5;i++){
                    fileWriter.write(String.valueOf(valueArray.get(i)));
                    fileWriter.write("\n");
                }
                fileWriter.close();
                getData();
            }catch (Exception e){
                System.out.println("An error occurred heree");
            }
        }
    }
    @FXML
    private void clickedVikingPacmanTextBox(MouseEvent event){
        ClickMusic click= new ClickMusic();
        Thread clickMusic=new Thread(click);
        clickMusic.start();
        if(vikingPacmanText.getText().equalsIgnoreCase("buy")){
            if(Integer.parseInt(cherry.getText())<100){
                shakeCherry();
            }else{
                try {
                    ArrayList<Integer> valueArray=new ArrayList<>();
                    Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                    Scanner scanner = new Scanner(path);
                    for(int i=0;i<5;i++){
                        if(i==3){
                            valueArray.add(0);
                        }else {
                            valueArray.add(Integer.parseInt(scanner.nextLine()));
                        }
                    }
                    scanner.close();
                    FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                    for(int i=0;i<5;i++){
                        fileWriter.write(String.valueOf(valueArray.get(i)));
                        fileWriter.write("\n");
                    }
                    fileWriter.close();
                    cherry.setText(String.valueOf(Integer.parseInt(cherry.getText())-100));
                    try {
                        FileWriter myWriter = new FileWriter("src/main/resources/Data/CherryCountData.txt");
                        myWriter.write(cherry.getText());
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }
                    getData();
                }catch (Exception e){
                    System.out.println("An error occurred here");
                }
            }
        }else if(vikingPacmanText.getText().equalsIgnoreCase("equip")){
            try {
                ArrayList<Integer> valueArray=new ArrayList<>();
                Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                Scanner scanner = new Scanner(path);
                for(int i=0;i<5;i++){
                    int value=Integer.parseInt(scanner.nextLine());
                    if(i==3){
                        valueArray.add(1);
                    }else {
                        if(value==1){
                            valueArray.add(0);
                        }else {
                            valueArray.add(value);
                        }
                    }
                }
                scanner.close();
                FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                for(int i=0;i<5;i++){
                    fileWriter.write(String.valueOf(valueArray.get(i)));
                    fileWriter.write("\n");
                }
                fileWriter.close();
                getData();
            }catch (Exception e){
                System.out.println("An error occurred heree");
            }
        }
    }

    @FXML
    private void clickedPiratePacmanTextBox(MouseEvent event){
        ClickMusic click= new ClickMusic();
        Thread clickMusic=new Thread(click);
        clickMusic.start();
        if(piratePacmanText.getText().equalsIgnoreCase("buy")){
            if(Integer.parseInt(cherry.getText())<200){
                shakeCherry();
            }else{
                try {
                    ArrayList<Integer> valueArray=new ArrayList<>();
                    Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                    Scanner scanner = new Scanner(path);
                    for(int i=0;i<5;i++){
                        if(i==4){
                            valueArray.add(0);
                        }else {
                            valueArray.add(Integer.parseInt(scanner.nextLine()));
                        }
                    }
                    scanner.close();
                    FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                    for(int i=0;i<5;i++){
                        fileWriter.write(String.valueOf(valueArray.get(i)));
                        fileWriter.write("\n");
                    }
                    fileWriter.close();
                    cherry.setText(String.valueOf(Integer.parseInt(cherry.getText())-200));
                    try {
                        FileWriter myWriter = new FileWriter("src/main/resources/Data/CherryCountData.txt");
                        myWriter.write(cherry.getText());
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }
                    getData();
                }catch (Exception e){
                    System.out.println("An error occurred here");
                }
            }
        }else if(piratePacmanText.getText().equalsIgnoreCase("equip")){
            try {
                ArrayList<Integer> valueArray=new ArrayList<>();
                Path path = Paths.get("src/main/resources/Data/PacmanCostumeData.txt");
                Scanner scanner = new Scanner(path);
                for(int i=0;i<5;i++){
                    int value=Integer.parseInt(scanner.nextLine());
                    if(i==4){
                        valueArray.add(1);
                    }else {
                        if(value==1){
                            valueArray.add(0);
                        }else {
                            valueArray.add(value);
                        }
                    }
                }
                scanner.close();
                FileWriter fileWriter=new FileWriter("src/main/resources/Data/PacmanCostumeData.txt");
                for(int i=0;i<5;i++){
                    fileWriter.write(String.valueOf(valueArray.get(i)));
                    fileWriter.write("\n");
                }
                fileWriter.close();
                getData();
            }catch (Exception e){
                System.out.println("An error occurred heree");
            }
        }
    }
}
