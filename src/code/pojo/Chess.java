package code.pojo;

import code.utils.UiUtils;

import java.awt.image.BufferedImage;

public abstract class Chess implements ChessAction{
    public static final int  CAMP_RED = 0;// 表示红方
    public static final int CAMP_BLACK = 1;// 表示黑方

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    private ChessBoard chessBoard; // 地图信息
    private Position pos; // 坐标
    private int camp; // 阵营
    private BufferedImage image; // 背景图像

    private String redImgPath; // 红方图像
    private String blackImgPath; // 黑方图像


    protected Chess(ChessBoard chessBoard,Position pos,int camp){
        this.chessBoard = chessBoard;
        this.camp = camp;
        this.setImgPath(); // 设置图像路径
        // 设置地图
        setPos(pos);
    }

    protected Chess(ChessBoard chessBoard, int x, int y, int camp){
        this(chessBoard, new Position(x, y), camp);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos; // 设置自己的位置
        chessBoard.setMap(pos,this); // 设置map里的位置
    }


    public BufferedImage getImage() {
        return image;
    }

    protected void setImage(BufferedImage image) {
        this.image = image;
    }

    protected void setImage(int width, int height){
        if(this.camp == CAMP_RED) setImage(UiUtils.getImg(redImgPath, width, height));
        else setImage(UiUtils.getImg(blackImgPath, width, height));
    }
    public int getCamp() {
        return camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }


    public void setRedImgPath(String redImgPath) {
        this.redImgPath = redImgPath;
    }


    public void setBlackImgPath(String blackImgPath) {
        this.blackImgPath = blackImgPath;
    }
}
