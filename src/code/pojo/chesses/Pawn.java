package code.pojo.chesses;

import code.pojo.Chess;
import code.pojo.ChessBoard;
import code.pojo.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Chess {

    private static final String blackImgPath = "src\\img\\chess6.png";
    private static final String redImgPath = "src\\img\\chess13.png";

    @Override
    public String toString() {
        return "兵" + getCamp();
    }

    public Pawn(ChessBoard chessBoard, Position pos, int camp, int width, int height) {
        super(chessBoard, pos, camp);
        super.setImage(width, height);
    }

    public Pawn(ChessBoard chessBoard, int x, int y, int camp, int width, int height) {
        super(chessBoard, x, y, camp);
        super.setImage(width, height);
    }

    @Override
    public void setImgPath() {
        setRedImgPath(redImgPath);
        setBlackImgPath(blackImgPath);
    }

    @Override
    public List<Position> getAllViablePos() {
        List<Position> poss = new ArrayList<>();
        Position pos = this.getPos();

        if(getCamp() == Chess.CAMP_RED && judgePos(pos.getUp()))
            poss.add(pos.getUp());
        if(getCamp() == Chess.CAMP_BLACK && judgePos(pos.getDown())) poss.add(pos.getDown());

        // 不是己方才能左右
        if(getCamp() == Chess.CAMP_RED && pos.y < 5 ||
                getCamp() == Chess.CAMP_BLACK && pos.y > 4){
            if(judgePos(pos.getLeft())) poss.add(pos.getLeft());
            if(judgePos(pos.getRight())) poss.add(pos.getRight());
        }

        return poss;
    }

}
