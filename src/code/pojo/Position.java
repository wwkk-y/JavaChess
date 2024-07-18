package code.pojo;

import java.util.Objects;

public class Position {
    public int x;
    public int y;

    private Position left;
    private Position right;
    private Position up;
    private Position down;

    private Position upLeft;
    private Position upright;
    private Position downLeft;
    private Position downRight;

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    Position(){}

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(String x, String y){
        this(Integer.parseInt(x.trim()), Integer.parseInt(y.trim()));
    }

    public Position getLeft(){
        if(left == null){
            this.left = new Position(x - 1, y);
        }
        return left;
    }

    public Position getRight(){
        if(right == null){
            this.right = new Position(x + 1, y);
        }
        return right;
    }

    public Position getUp(){
        if(up == null){
            this.up = new Position(x, y - 1);
        }
        return up;
    }

    public Position getDown(){
        if(down == null){
            this.down = new Position(x, y + 1);
        }
        return down;
    }

    public Position getUpLeft() {
        if(upLeft == null){
            upLeft = new Position(x - 1, y - 1);
        }
        return upLeft;
    }


    public Position getUpright() {
        if(upright == null){
            upright = new Position(x + 1, y - 1);
        }
        return upright;
    }

    public Position getDownLeft() {
        if(downLeft == null){
            downLeft = new Position(x - 1, y + 1);
        }
        return downLeft;
    }


    public Position getDownRight() {
        if(downRight == null){
            downRight = new Position(x + 1, y + 1);
        }
        return downRight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
