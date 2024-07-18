package code.utils;


import code.pojo.Position;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ChessboardUtils {
    private final int xBegin;
    private final int yBegin;
    private final int width;
    private final int height;

    public ChessboardUtils(int xBegin, int yBegin, BufferedImage background){
        this.xBegin = xBegin;
        this.yBegin = yBegin;
        // 计算棋盘坐标信息
        int bW = background.getWidth();
        int bH = background.getHeight();
        width = (bW - xBegin * 2) / 9;
        height = (bH - yBegin * 2) / 10;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // 计算在棋盘里的y下标
    public int getYIndex(MouseEvent e){
        int y = (e.getY() - yBegin) / height;
        if(y < 0) y = 0;
        else if(y > 9) y = 9;
        return y;
    }


    // 计算在棋盘里的x下标
    public int getXIndex(MouseEvent e){
        int x = (e.getX() - xBegin) / width;
        if(x < 0) x = 0;
        else if(x > 8) x = 8;
        return x;
    }

    public Position getPosition(MouseEvent e){
        return new Position(getXIndex(e), getYIndex(e));
    }

}
