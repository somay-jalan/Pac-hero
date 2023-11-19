package com.example.pachero;

public class Game_Logic {
    private Pacman pacman;
    private Platform cur_platform;
    private Platform next_platform;
    private Perfect perfect;
    private Pacman_Stick pacmanStick;
    private Ghost ghost;
    private Cherry cherry;

    public Game_Logic(Pacman pacman, Platform cur_platform, Platform next_platform, Perfect perfect, Pacman_Stick pacmanStick, Ghost ghost, Cherry cherry) {
        this.pacman = pacman;
        this.cur_platform = cur_platform;
        this.next_platform = next_platform;
        this.perfect = perfect;
        this.pacmanStick = pacmanStick;
        this.ghost = ghost;
        this.cherry = cherry;
    }

    public void GameStop(){
    }

    public void GamePause(){
    }

    public void GamePlay(){
    }

}
