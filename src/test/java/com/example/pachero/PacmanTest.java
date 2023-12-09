package com.example.pachero;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacmanTest {
    public Pacman pacman;

    @BeforeEach
    public void setUP(){
        pacman=new Pacman(null);
    }

    @Test
    void startLayoutXTest(){
        assertEquals(291,pacman.getStartLayoutX());
    }
    @Test
    void startLayoutYTest(){
        assertEquals(320,pacman.getStartLayoutY());
    }

}