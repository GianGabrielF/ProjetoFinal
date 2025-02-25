package poo.Final;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameBoard painel = new GameBoard();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        window.setTitle("Pac-Man Ascii");
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);

        GameBoard jogo = new GameBoard();
        window.add(jogo);
        jogo.requestFocusInWindow();

        painel.startGameThread();

    }
}