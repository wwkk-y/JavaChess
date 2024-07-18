package code.pojo.chesses;

import code.pojo.Chess;
import code.pojo.ChessBoard;
import code.pojo.Position;
import code.utils.ChessUtils;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Chess {
    private static final String blackImgPath = "src\\img\\chess3.png";
    private static final String redImgPath = "src\\img\\chess10.png";

    @Override
    public String toString() {
        return "马" + getCamp();
    }

    public Knight(ChessBoard chessBoard, Position pos, int camp, int width, int height) {
        super(chessBoard, pos, camp);
        super.setImage(width, height);
    }

    public Knight(ChessBoard chessBoard, int x, int y, int camp, int width, int height) {
        super(chessBoard, x, y, camp);
        super.setImage(width, height);
    }

    @Override
    public List<Position> getAllViablePos() {
        List<Position> poss = new ArrayList<>();
        Position pos = this.getPos();
        // 八个点 前进一格斜走一格
        if(ChessUtils.notExistChess(this, pos.getUp())){
            if(judgePos(pos.getUp().getUpright())) poss.add(pos.getUp().getUpright());
            if(judgePos(pos.getUp().getUpLeft())) poss.add(pos.getUp().getUpLeft());
        }
        if(ChessUtils.notExistChess(this, pos.getDown())){
            if(judgePos(pos.getDown().getDownRight())) poss.add(pos.getDown().getDownRight());
            if(judgePos(pos.getDown().getDownLeft())) poss.add((pos.getDown().getDownLeft()));
        }
        if (ChessUtils.notExistChess(this, pos.getLeft())){
            if(judgePos(pos.getLeft().getUpLeft())) poss.add(pos.getLeft().getUpLeft());
            if(judgePos(pos.getLeft().getDownLeft())) poss.add(pos.getLeft().getDownLeft());
        }
        if (ChessUtils.notExistChess(this, pos.getRight())){
            if(judgePos(pos.getRight().getDownRight())) poss.add(pos.getRight().getDownRight());
            if(judgePos(pos.getRight().getUpright())) poss.add(pos.getRight().getUpright());
        }
        return poss;
    }

    @Override
    public void setImgPath() {
        setRedImgPath(redImgPath);
        setBlackImgPath(blackImgPath);
    }
}
