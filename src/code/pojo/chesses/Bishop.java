package code.pojo.chesses;

import code.pojo.Chess;
import code.pojo.ChessBoard;
import code.pojo.Position;
import code.utils.ChessUtils;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Chess {
    private static final String blackImgPath = "src\\img\\chess2.png";
    private static final String redImgPath = "src\\img\\chess9.png";

    @Override
    public String toString() {
        return "象" + getCamp();
    }

    public Bishop(ChessBoard chessBoard, Position pos, int camp, int width, int height) {
        super(chessBoard, pos, camp);
        super.setImage(width, height);
    }

    public Bishop(ChessBoard chessBoard, int x, int y, int camp, int width, int height) {
        super(chessBoard, x, y, camp);
        super.setImage(width, height);
    }

    @Override
    public List<Position> getAllViablePos() {
        List<Position> poss = new ArrayList<>();
        Position pos = this.getPos();
        // 四个点
        if(ChessUtils.notExistChess(this, pos.getUpright()) && judgePos(pos.getUpright().getUpright())) poss.add(pos.getUpright().getUpright());
        if(ChessUtils.notExistChess(this, pos.getUpLeft()) && judgePos(pos.getUpLeft().getUpLeft())) poss.add(pos.getUpLeft().getUpLeft());
        if(ChessUtils.notExistChess(this, pos.getDownRight()) && judgePos(pos.getDownRight().getDownRight())) poss.add(pos.getDownRight().getDownRight());
        if(ChessUtils.notExistChess(this, pos.getDownLeft()) && judgePos(pos.getDownLeft().getDownLeft())) poss.add(pos.getDownLeft().getDownLeft());
        return poss;
    }

    @Override
    public void setImgPath() {
        setRedImgPath(redImgPath);
        setBlackImgPath(blackImgPath);
    }

    @Override
    public boolean judgePos(Position pos) {
        if(getCamp() == CAMP_RED && pos.y < 5) return false;
        else if(getCamp() == CAMP_BLACK && pos.y > 4) return false;
        return super.judgePos(pos);
    }
}
