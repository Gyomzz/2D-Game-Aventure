package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // cause this window to be sized to fit the prefrred size and layouts of its subcomponents
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}