package poo.Final;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameBoard extends JPanel implements Runnable, KeyListener {

    Thread gameThread;
    Mapa mapa = new Mapa();
    Player player = new Player();
    Blinky blinky = new Blinky(9, 13); // Blinky começa no meio do mapa
    Pinky pinky = new Pinky(9, 14); // Posição inicial do Pinky
    Inky inky = new Inky(9, 12); // Posição inicial do Inky
    Clyde clyde = new Clyde(9, 11); // Posição inicial do Clyde
    String[] tela;
    String direcaoDesejada = player.getDirecao();
    int highscore = 0;
    int fantasmaComido = 0;

    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;

    private final int FPS = 12;

    public GameBoard() {
        gameThread = new Thread(this);
        addKeyListener(this);
        setFocusable(true);
    }


    private String[] drawGame() {
        String[] gameDrawn = mapa.drawMap().clone();
        char[] vetor = gameDrawn[player.getCoordy()].toCharArray();
        vetor[player.getCoordx()] = player.getAvatar();
        gameDrawn[player.getCoordy()] = String.valueOf(vetor);

        char[] blinkyLine = gameDrawn[blinky.getCoordy()].toCharArray();
        blinkyLine[blinky.getCoordx()] = 'B';
        gameDrawn[blinky.getCoordy()] = String.valueOf(blinkyLine);

        char[] pinkyLine = gameDrawn[pinky.getCoordy()].toCharArray();
        pinkyLine[pinky.getCoordx()] = 'P';
        gameDrawn[pinky.getCoordy()] = String.valueOf(pinkyLine);

        char[] inkyLine = gameDrawn[inky.getCoordy()].toCharArray();
        inkyLine[inky.getCoordx()] = 'I';  // 'I' representa o Inky
        gameDrawn[inky.getCoordy()] = String.valueOf(inkyLine);

        char[] clydeLine = gameDrawn[clyde.getCoordy()].toCharArray();
        clydeLine[clyde.getCoordx()] = 'C';  // 'C' representa o Clyde
        gameDrawn[clyde.getCoordy()] = String.valueOf(clydeLine);


        return gameDrawn;
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
        tela = drawGame();
    }

    @Override
    public void run() {
        while (this.gameThread.isAlive()) {

            double drawInterval = (double) 1000 / FPS;
            double nextDrawTime = System.currentTimeMillis() + drawInterval;

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.currentTimeMillis();

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {

        player.comer(mapa);
        player.move(mapa);
        if (direcaoDesejada != player.getDirecao()) {
            player.changeDirection(direcaoDesejada, mapa);
        }

        blinky.moverFantasma(mapa, player.getCoordx(), player.getCoordy(), blinky.getCoordx(), blinky.getCoordy());
        pinky.moverFantasma(mapa, player.getCoordx(), player.getCoordy(), blinky.getCoordx(), blinky.getCoordy());
        inky.moverFantasma(mapa, player.getCoordx(), player.getCoordy(), blinky.getCoordx(), blinky.getCoordy());
        clyde.moverFantasma(mapa, player.getCoordx(), player.getCoordy(), blinky.getCoordx(), blinky.getCoordy());

        // Verifica colisão entre Pac-Man e fantasmas
        verificarColisao();

        // Reduz a duração do modo especial
        player.reduzirModoEspecial(fantasmaComido);
        if(player.getTempoModoEspecial() <= 0){
            blinky.setImune(false);
            pinky.setImune(false);
            inky.setImune(false);
            clyde.setImune(false);
        }

        tela = drawGame();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 20));

        for (int i = 0; i < tela.length; i++) {
            g.drawString(tela[i], 10, (i + 1) * 15);
        }

        g.drawString("Pontos: " + this.player.getPontos(), 400, 420);
        g.drawString("Especial: " + this.player.getTempoModoEspecial(), 400, 440);
        g.drawString("Highsocre: " + this.highscore,400,460);
    }

    public void verificarColisao() {

        if (player.getCoordx() == blinky.getCoordx() && player.getCoordy() == blinky.getCoordy() ) {
            if (player.isModoEspecial() && !blinky.isImune()) {
                // Pac-Man pode comer o fantasma
                blinky.respawn();
                player.aumentarPontos((int) (200 * Math.pow(2, fantasmaComido)));
                fantasmaComido++; // Pontuação bônus por capturar um fantasma
                blinky.setImune(true);
            } else {
                // Pac-Man foi capturado, o jogo reinicia ou ele perde uma vida
                reiniciarJogo();
            }
        } else if (player.getCoordx() == pinky.getCoordx() && player.getCoordy() == pinky.getCoordy() ) {
            if (player.isModoEspecial() && !pinky.isImune()) {
                // Pac-Man pode comer o fantasma
                pinky.respawn();
                player.aumentarPontos((int) (200 * Math.pow(2, fantasmaComido)));
                fantasmaComido++; // Pontuação bônus por capturar um fantasma
                pinky.setImune(true);
            } else {
                // Pac-Man foi capturado, o jogo reinicia ou ele perde uma vida
                reiniciarJogo();
            }
        } else if (player.getCoordx() == inky.getCoordx() && player.getCoordy() == inky.getCoordy() ) {
            if (player.isModoEspecial() && !inky.isImune()) {
                // Pac-Man pode comer o fantasma
                inky.respawn();
                player.aumentarPontos((int) (200 * Math.pow(2, fantasmaComido)));
                fantasmaComido++; // Pontuação bônus por capturar um fantasma
                inky.setImune(true);
            } else {
                // Pac-Man foi capturado, o jogo reinicia ou ele perde uma vida
                reiniciarJogo();
            }
        } else if (player.getCoordx() == clyde.getCoordx() && player.getCoordy() == clyde.getCoordy() ) {
            if (player.isModoEspecial() && !clyde.isImune()) {
                // Pac-Man pode comer o fantasma
                clyde.respawn();
                player.aumentarPontos((int) (200 * Math.pow(2, fantasmaComido)));
                fantasmaComido++; // Pontuação bônus por capturar o Clyde
                clyde.setImune(true);
            } else {
                // Pac-Man foi capturado, o jogo reinicia ou ele perde uma vida
                reiniciarJogo();
            }
        }
    }

    public void reiniciarJogo() {
        if(highscore < player.getPontos()){
            highscore = player.getPontos();
        }
        mapa = new Mapa();

        player.setCoordx(14); // Posição inicial do Pac-Man
        player.setCoordy(22);
        player.setDirecao("esquerda");
        player.setPontos(0);
        player.setTempoModoEspecial(0);
        player.setModoEspecial(false);

        blinky.respawn();
        blinky.setImune(false);
        pinky.respawn();
        pinky.setImune(false);
        inky.respawn();
        inky.setImune(false);
        clyde.respawn();
        clyde.setImune(false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                upPressed = true;
                direcaoDesejada = player.changeDirection("cima", mapa);
            }
            case KeyEvent.VK_DOWN -> {
                downPressed = true;
                direcaoDesejada = player.changeDirection("baixo", mapa);
            }
            case KeyEvent.VK_LEFT -> {
                leftPressed = true;
                direcaoDesejada = player.changeDirection("esquerda", mapa);
            }
            case KeyEvent.VK_RIGHT -> {
                rightPressed = true;
                direcaoDesejada = player.changeDirection("direita", mapa);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_DOWN -> downPressed = false;
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}