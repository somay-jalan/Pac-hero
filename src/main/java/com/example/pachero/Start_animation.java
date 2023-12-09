package com.example.pachero;




public class Start_animation implements Runnable {

    private final int animationTime;

    private final TextOutput textoutput;

    public Start_animation(int animationTime, TextOutput textoutput){
        this.animationTime=animationTime;
        this.textoutput=textoutput;
    }
    @Override
    public void run(){
        try{
            textoutput.writeText("2");
            Thread.sleep(animationTime);
            textoutput.writeText("22");
            Thread.sleep(animationTime);
            textoutput.writeText("222");
            Thread.sleep(animationTime);
            textoutput.writeText("2222");
            Thread.sleep(animationTime);
            textoutput.writeText("22222");
            Thread.sleep(animationTime);
            textoutput.writeText("2222");
            Thread.sleep(animationTime);
            textoutput.writeText("222");
            Thread.sleep(animationTime);
            textoutput.writeText("22");
            Thread.sleep(animationTime);
            textoutput.writeText("2");
            Thread.sleep(animationTime);
            textoutput.writeText("");
            Thread.sleep(animationTime);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

}
