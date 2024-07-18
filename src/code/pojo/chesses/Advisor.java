package code.pojo.chesses;

import code.pojo.Chess;
import code.pojo.ChessBoard;
import code.pojo.Position;

import java.util.ArrayList;
import java.util.List;

public class Advisor extends Chess {
    private static final String blackImgPath = "src\\img\\chess1.png";
    private static final String redImgPath = "src\\img\\chess8.png";

    @Override
    public String toString() {
        return "士" + getCamp();
    }

    public Advisor(ChessBoard chessBoard, Position pos, int camp, int width, int height) {
        super(chessBoard, pos, camp);
        super.setImage(width, height);
    }

    public Advisor(ChessBoard chessBoard, int x, int y, int camp, int width, int height) {
        super(chessBoard, x, y, camp);
        super.setImage(width, height);
    }

    @Override
    public List<Position> getAllViablePos() {
        List<Position> poss = new ArrayList<>();
        Position pos = this.getPos();
        if(judgePos(pos.getDownLeft())) poss.add(pos.getDownLeft());
        if(judgePos(pos.getDownRight())) poss.add(pos.getDownRight());
        if(judgePos(pos.getUpLeft())) poss.add(pos.getUpLeft());
        if(judgePos(pos.getUpright())) poss.add(pos.getUpright());
        return poss;
    }

    @Override
    public void setImgPath() {
        setRedImgPath(redImgPath);
        setBlackImgPath(blackImgPath);
    }

    @Override
    public boolean judgePos(Position pos) {
        // 士 只能再田字格里面移动
        // X:3 4 5
        // Y:
        //  红 0 1 2
        //  黑 7 8 9
        if(pos.x<3||pos.x>5||pos.y<0|| (pos.y>2 && pos.y<7) ||pos.y>9) return false;
        return super.judgePos(pos);
    }
}
