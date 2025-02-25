package poo.Final;


import javax.swing.*;

import java.awt.*;

import static java.lang.Thread.sleep;

public class GameBoard extends JPanel implements Runnable {


    Thread gameThread;
    Mapa mapa = new Mapa();

    public void startGameThread(){
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }
    @Override
    public void run() {
        while(this.gameThread.isAlive()){

            update();
            repaint();

        }

    }

    public void update(){

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0,0, 800,600);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 20));
        for(int i=0;i<mapa.drawMap().length;i++){
            g.drawString(mapa.drawMap()[i],10,(i+1)*15);

        }
    }
}
