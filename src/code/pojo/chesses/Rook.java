package code.pojo.chesses;

import code.pojo.Chess;
import code.pojo.ChessBoard;
import code.pojo.Position;
import code.utils.ChessUtils;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Chess{
    private static final String blackImgPath = "src\\img\\chess4.png";
    private static final String redImgPath = "src\\img\\chess11.png";

    @Override
    public String toString() {
        return "车" + getCamp();
    }

    public Rook(ChessBoard chessBoard, Position pos, int camp, int width, int height) {
        super(chessBoard, pos, camp);
        super.setImage(width, height);
    }

    public Rook(ChessBoard chessBoard, int x, int y, int camp, int width, int height) {
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

        // 搜索 四个方向
        // x +
        for(int x = pos.x + 1; x <= 8; ++x){
            Position position = new Position(x, pos.y);
            if(!ChessUtils.notExistChess(this, position)) {
                if(judgePos(position)) poss.add(position);
                break;
            }
            poss.add(position);
        }
        // x -
        for(int x = pos.x - 1; x >= 0; --x){
            Position position = new Position(x, pos.y);
            if(!ChessUtils.notExistChess(this, position)) {
                if(judgePos(position)) poss.add(position);
                break;
            }
            poss.add(position);
        }
        // y +
        for(int y = pos.y + 1; y <= 9; ++y){
            Position position = new Position(pos.x, y);
            if(!ChessUtils.notExistChess(this, position)) {
                if(judgePos(position)) poss.add(position);
                break;
            }
            poss.add(position);
        }
        // y -
        for(int y = pos.y - 1; y >= 0; --y){
            Position position = new Position(pos.x, y);
            if(!ChessUtils.notExistChess(this, position)) {
                if(judgePos(position)) poss.add(position);
                break;
            }
            poss.add(position);
        }

        return poss;
    }
}
