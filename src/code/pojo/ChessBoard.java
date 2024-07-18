package code.pojo;

import code.pojo.chesses.*;

import java.util.Objects;

public class ChessBoard {

    private final Chess[][] map; // 10 * 9 棋盘
    private final int xBegin; // 绘制开始位置
    private final int yBegin; // 绘制开始位置
    private final int width; // 一个棋子的宽
    private final int height; // 一个棋子的高



    public ChessBoard(int xBegin, int yBegin, int width, int height) {
        this.xBegin = xBegin;
        this.yBegin = yBegin;
        this.width = width;
        this.height = height;
        map = new Chess[10][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new Chess[9];
        }
        init();
    }

    public void setMap(Position pos, Chess chess){
        map[pos.y][pos.x] = chess;
    }

    public void setChess(int y, int x, Chess chess){
        setChess(new Position(x, y), chess);
    }

    public void setChess(Position pos, Chess chess){
        if(chess == null) map[pos.y][pos.x] = null;
        else {
            // 会同时设置棋子的pos属性和棋盘的map对应位置
            chess.setPos(pos);
        }
    }

    public Chess getChess(int y, int x){
        return map[y][x];
    }

    public Chess getChess(Position pos){
        return getChess(pos.y, pos.x);
    }

    public int getXBegin() {
        return xBegin;
    }

    public int getYBegin() {
        return yBegin;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * 初始化棋盘
     */
    public void init(){
        // 将
        // 黑 0 4
        // 红 9 4
        new General(this, 4, 0, Chess.CAMP_BLACK, width, height);
        new General(this, 4, 9, Chess.CAMP_RED, width, height);
        // 士
        // 黑  0 3 | 0 5 |
        // 红  9 3 | 9 5
        new Advisor(this, 3, 0, Chess.CAMP_BLACK, width, height);
        new Advisor(this, 5, 0, Chess.CAMP_BLACK, width, height);
        new Advisor(this, 3, 9, Chess.CAMP_RED, width, height);
        new Advisor(this, 5, 9, Chess.CAMP_RED, width, height);
        // 兵
        // 红 6 (0, 2, 4, 6, 8)
        // 黑 3 (0, 2, 4, 6, 8)
        new Pawn(this, 0, 6, Chess.CAMP_RED, width, height);
        new Pawn(this, 2, 6, Chess.CAMP_RED, width, height);
        new Pawn(this, 4, 6, Chess.CAMP_RED, width, height);
        new Pawn(this, 6, 6, Chess.CAMP_RED, width, height);
        new Pawn(this, 8, 6, Chess.CAMP_RED, width, height);
        new Pawn(this, 0, 3, Chess.CAMP_BLACK, width, height);
        new Pawn(this, 2, 3, Chess.CAMP_BLACK, width, height);
        new Pawn(this, 4, 3, Chess.CAMP_BLACK, width, height);
        new Pawn(this, 6, 3, Chess.CAMP_BLACK, width, height);
        new Pawn(this, 8, 3, Chess.CAMP_BLACK, width, height);
        // 象
        // 红 9 2|9 6
        // 黑 0 2|0 6
        new Bishop(this,2,9,Chess.CAMP_RED,width,height);
        new Bishop(this,6,9,Chess.CAMP_RED,width,height);
        new Bishop(this,2,0,Chess.CAMP_BLACK,width,height);
        new Bishop(this,6,0,Chess.CAMP_BLACK,width,height);
        // 马
        // 红 9 1|9 7
        // 黑 0 1|0 7
        new Knight(this, 1, 9, Chess.CAMP_RED, width, height);
        new Knight(this, 7, 9, Chess.CAMP_RED, width, height);
        new Knight(this, 1, 0, Chess.CAMP_BLACK, width, height);
        new Knight(this, 7, 0, Chess.CAMP_BLACK, width, height);
        // 车
        // 红 9 0|9 8
        // 黑 0 0|0 8
        new Rook(this, 0, 9, Chess.CAMP_RED, width, height);
        new Rook(this, 8, 9, Chess.CAMP_RED, width, height);
        new Rook(this, 0, 0, Chess.CAMP_BLACK, width, height);
        new Rook(this, 8, 0, Chess.CAMP_BLACK, width, height);
        // 炮
        // 红 7 1|7 7
        // 黑 2 1|2 7
        new Cannon(this, 1, 7, Chess.CAMP_RED, width, height);
        new Cannon(this, 7, 7, Chess.CAMP_RED, width, height);
        new Cannon(this, 1, 2, Chess.CAMP_BLACK, width, height);
        new Cannon(this, 7, 2, Chess.CAMP_BLACK, width, height);

    }


}


