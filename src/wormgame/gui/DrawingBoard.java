package wormgame.gui;

import wormgame.domain.Piece;
import wormgame.game.WormGame;

import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel implements Updatable {
    private WormGame wormGame;
    private int pieceLength;

    public DrawingBoard(WormGame wormGame, int pieceLength){
        super.setBackground(Color.lightGray);
        this.wormGame = wormGame;
        this.pieceLength = pieceLength;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        for (Piece p : this.wormGame.getWorm().getPieces()){
            g.fill3DRect(p.getX()*pieceLength, p.getY()*pieceLength, pieceLength, pieceLength, true );
        }

        g.setColor(Color.RED);
        g.fillOval(wormGame.getApple().getX()*pieceLength,wormGame.getApple().getY()*pieceLength, pieceLength, pieceLength );
    }

    @Override
    public void update() {
        super.repaint();
    }
}