package com.example.pachero;

import javafx.scene.shape.Line;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Pacman_StickTest {
    private Pacman_Stick pacmanStick;

    @BeforeEach
    public void setUp(){
        pacmanStick=new Pacman_Stick(new Line());
    }

    @Test
    void startLayoutXTest(){
        assertEquals(310,pacmanStick.getStartLayoutX());
    }
    @Test
    void startLayoutYTest(){
        assertEquals(320,pacmanStick.getStartLayoutY());
    }

}