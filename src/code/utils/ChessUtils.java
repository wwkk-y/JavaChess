package code.utils;

import code.pojo.Chess;
import code.pojo.Position;


public class ChessUtils {
    // 判断指定位置是否可以走
    public static boolean judgePos(Chess chess, Position pos){
        if(pos.x<0||pos.x>8||pos.y<0|| pos.y>9) return false; // 越界
        if(chess == null) return true; // 如果为空，可以走
        Chess next = chess.getChessBoard().getChess(pos);
        // 位置为空 或者 阵营不同
        return next == null || chess.getCamp() != next.getCamp();
    }

    // 判断指定位置是否存在棋子, 返回true表示可行
    public static boolean notExistChess(Chess chess, Position pos){
        if(chess == null) return false;
        if(pos.x<0||pos.x>8||pos.y<0|| pos.y>9) return false; // 越界
        return chess.getChessBoard().getChess(pos) == null;
    }

    // 判断指定位置是否存在敌人 为true表示存在
    public static boolean enemyPresence(Chess chess, Position pos){
        if(chess == null) return false;
        if(pos.x<0||pos.x>8||pos.y<0|| pos.y>9) return false; // 越界
        Chess next = chess.getChessBoard().getChess(pos);
        // 不为空且阵营不同
        return next != null && chess.getCamp() != next.getCamp();
    }
}
