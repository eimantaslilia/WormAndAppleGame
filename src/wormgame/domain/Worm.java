package wormgame.domain;

import wormgame.Direction;

import java.util.ArrayList;
import java.util.List;

public class Worm {
    private Direction direction;
    private List<Piece> pieces = new ArrayList<Piece>();
    private boolean growth = false;

    public Worm(int originalX, int originalY, Direction originalDirection){
        this.pieces.add(new Piece(originalX, originalY));
        this.direction = originalDirection;
    }
    public Direction getDirection(){
        return this.direction;
    }
    public void setDirection(Direction dir){
        this.direction = dir;
    }
    public int getLength(){
        return pieces.size();
    }

    public List<Piece> getPieces(){
        return pieces;
    }
    public void move(){
        int lastIndex = getLength() - 1;
        int newX = pieces.get(lastIndex).getX();
        int newY = pieces.get(lastIndex).getY();

        if(direction == Direction.RIGHT){
            pieces.add(new Piece(newX+1, newY));
        } else if(direction == Direction.LEFT){
            pieces.add(new Piece(newX-1, newY));
        } else if (direction == Direction.UP){
            pieces.add(new Piece(newX, newY-1));
        } else if (direction == Direction.DOWN){
            pieces.add(new Piece(newX, newY+1));
        }
        if(growth){
            growth = false;
            return;
        }
        if (getLength() > 3){
            pieces.remove(0);
        }
    }
    public void grow(){
        this.growth = true;
    }
    public boolean runsInto(Piece piece){
        for(Piece onePiece : pieces){
            if(onePiece.runsInto(piece)) {
                return true;
            }
        }
        return false;
    }
    public boolean runsIntoItself(){
        for (int i = 0; i <= getLength()-1; i++) {
            for (int j = 0; j < getLength() - 1; j++) {
                if(pieces.get(i).runsInto(pieces.get(j))){
                    if(i == j){

                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}