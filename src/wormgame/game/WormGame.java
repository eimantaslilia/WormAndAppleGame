package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);
        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);
        this.worm = new Worm(width / 2, height / 2, Direction.DOWN);

        setApple(newApple());
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }

        this.worm.move();
        if (worm.runsInto(apple)){
            worm.grow();
            setApple(newApple());

        } if (worm.runsIntoItself()){
            this.continues = false;
        }

        if (hitsBorder()){
            continues = false;
        }
        updatable.update();
        setDelay(500 / worm.getLength());
    }
    public Worm getWorm(){
        return this.worm;
    }
    public void setWorm(Worm worm){
        this.worm = worm;
    }
    public Apple getApple(){
        return this.apple;
    }
    public void setApple(Apple apple){
        this.apple = apple;
    }
    public Apple newApple() {
        Random random = new Random();
        this.apple = new Apple(random.nextInt(width), random.nextInt(height));
        for (Piece piece : worm.getPieces()) {
            if (apple.runsInto(piece)) {
                this.apple = new Apple(random.nextInt(width), random.nextInt(height));
            }
        }
        return this.apple;
    }
    public boolean hitsBorder(){
        for(Piece piece : worm.getPieces()){
            if (piece.getX() == width){
                return true;
            } else if(piece.getX() == 0){
                return true;
            } else if (piece.getY() == height){
                return true;
            } else if (piece.getY() == 0){
                return true;
            }
        }
        return false;
    }
}