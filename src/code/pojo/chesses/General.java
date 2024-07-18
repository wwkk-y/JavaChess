package code.pojo.chesses;

import code.pojo.Chess;
import code.pojo.ChessBoard;
import code.pojo.Position;

import java.util.ArrayList;
import java.util.List;

// 将
public class General extends Chess {
    private static final String blackImgPath = "src\\img\\chess0.png";
    private static final String redImgPath = "src\\img\\chess7.png";

    @Override
    public String toString() {
        return "将" + getCamp();
    }

    public General(ChessBoard chessBoard, Position pos, int camp, int width, int height) {
        super(chessBoard, pos, camp);
        super.setImage(width, height);
    }

    public General(ChessBoard chessBoard, int x, int y, int camp, int width, int height) {
        super(chessBoard, x, y, camp);
        super.setImage(width, height);
    }

    @Override
    public List<Position> getAllViablePos() {
        List<Position> poss = new ArrayList<>();
        Position pos = this.getPos();
        // 上下左右判断一圈
        if(judgePos(pos.getLeft())) poss.add(pos.getLeft());
        if(judgePos(pos.getRight())) poss.add(pos.getRight());
        if(judgePos(pos.getUp())) poss.add(pos.getUp());
        if(judgePos(pos.getDown())) poss.add(pos.getDown());
        // 对将
        if(this.getCamp() == Chess.CAMP_BLACK){
            for(int y = 7; y <= 9; ++y){
                if(getChessBoard().getChess(y, pos.x) instanceof General){
                    // 判断中间是不是全为null
                    int y1 = pos.y + 1;
                    for(; y1 < y; ++y1){
                        if(getChessBoard().getChess(y1, pos.x) != null) break;
                    }
                    if(y1 == y){
                        poss.add(new Position(pos.x, y));
                    }
                    break;
                }
            }
        } else {
            for(int y = 0; y <= 2; ++y){
                if(getChessBoard().getChess(y, pos.x) instanceof General){
                    // 判断中间是不是全为null
                    int y1 = pos.y - 1;
                    for(; y1 > y; --y1){
                        if(getChessBoard().getChess(y1, pos.x) != null) break;
                    }
                    if(y1 == y){
                        poss.add(new Position(pos.x, y));
                    }
                    break;
                }
            }
        }
        return poss;
    }

    @Override
    public void setImgPath() {
        setRedImgPath(redImgPath);
        setBlackImgPath(blackImgPath);
    }

    /**
     * 判断指定位置是否可以过去
     */
    @Override
    public boolean judgePos(Position pos){
        // 将 只能再田字格里面移动
        // X:3 4 5
        // Y:
        //  红 0 1 2
        //  黑 7 8 9
        if(pos.x<3||pos.x>5||pos.y<0|| (pos.y>2 && pos.y<7) ||pos.y>9) return false;
        return super.judgePos(pos);
    }
}
