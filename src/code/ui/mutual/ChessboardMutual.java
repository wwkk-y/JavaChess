package code.ui.mutual;

import code.container.Order;
import code.ui.ChessBoardUi;
import code.utils.ChessboardUtils;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ChessboardMutual {

    private final ChessBoardUi chessBoardUi;
    private final Order order;

    private ChessboardUtils utils = ChessBoardUi.utils;

    public ChessboardMutual(Order order, ChessBoardUi chessBoardUi){
        this.order = order;
        order.chessboardMutual = this;

        this.chessBoardUi = chessBoardUi;
    }

    // 绘制棋子
    public void paintChess(Graphics g){
        order.chessboardLogic.paint(g);
    }

    // 处理棋盘点击事件
    public void chessboardClickEvent(MouseEvent e){
        order.chessboardLogic.setRoads(utils.getPosition(e));
    }

    public void repaint(){
        chessBoardUi.repaint();
    }
}
