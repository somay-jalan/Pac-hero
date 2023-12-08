package com.example.pachero;


import javafx.animation.Timeline;
import javafx.event.ActionEvent;


import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Game_LogicTest {
    private Game_Logic gameLogic;
    @BeforeEach
    public void setUp(){
        gameLogic=new Game_Logic(new Pacman(new Costume(null)),new Platform(new Rectangle()),new Platform(new Rectangle()),new Perfect(new Rectangle()),new Pacman_Stick(new Line()),new AnchorPane(),new Text("0"),new Text("0"),new AnchorPane(),new AnchorPane(),new Game_page_controller());
    }
    @Test
    public void setDataTest(){
        assertDoesNotThrow(()->gameLogic.setData());
    }
    @Test
    public void resetKeyboardTest(){
        gameLogic.resetKeyboard(new ActionEvent());
        assertEquals(0,gameLogic.getPacmanRotated());
        assertEquals(0,gameLogic.getKey_pressed());
    }

    @Test
    public void animationListTest(){
        assertEquals(0,gameLogic.getAnimationList().size());
        Timeline timeline=new Timeline();
        gameLogic.getAnimationList().add(timeline);
        assertEquals(1,gameLogic.getAnimationList().size());
        gameLogic.getAnimationList().remove(timeline);
        assertEquals(0,gameLogic.getAnimationList().size());
    }

}