package code.ui.mutual;

import code.container.Order;
import code.logic.RequestLogic;
import code.pojo.Chess;
import code.ui.DisplayUi;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class DisplayMutual {

    private Order order;

    private DisplayUi displayUi;

    public DisplayMutual(Order order, DisplayUi displayUi){
        this.order = order;
        order.displayMutual = this;

        this.displayUi = displayUi;

        // 清空之前设置的监听器
        order.responseService.removeAllListen(RequestLogic.STEP);
        // 重新设置监听器
        order.responseService.addListen(RequestLogic.STEP, () -> setTip("你的回合")); //添加一个监听器
    }

    // 提示初始信息
    public void showInitMsg(){
        if(order.chessboardLogic.getCamp()== Chess.CAMP_RED)
            JOptionPane.showMessageDialog(displayUi, "你的阵营为红方,由你先出棋");
        else
            JOptionPane.showMessageDialog(displayUi, "你的阵营为黑方,由对方先出棋");
    }

    // 设置提示信息
    public void setTip(String tip){
        displayUi.setTip(tip);
    }

    // 重置界面
    public void reset(){
        displayUi.resetChessboard(order, order.chessboardLogic.getCamp()); // 重新设置一个新的棋盘
        setMsg(); // 设置需要变动的信息
        displayUi.repaint();
        showInitMsg(); // 展示初始信息
    }

    // 设置后面会改变的初始信息
    public void setMsg(){
        // 设置回合
        if(order.chessboardLogic.getCamp() == Chess.CAMP_RED) // 红方先
            setTip("你的回合");
        else setTip("对方的回合");
    }

    // 游戏结束界面
    public void gameOver(int camp){
        displayUi.repaint();
        // 一方输
        if(camp == Chess.CAMP_BLACK){
            JOptionPane.showMessageDialog(displayUi,
                    "游戏结束, 红色方胜利");
        } else{
            JOptionPane.showMessageDialog(displayUi,
                    "游戏结束, 黑色方胜利");
        }
        back();
    }

    // 展示一个提示界面
    public void showMsg(String msg){
        JOptionPane.showMessageDialog(displayUi, msg);
    }

    // 展示一个询问界面
    public boolean confirmDialog(String msg){
        int rst = JOptionPane.showConfirmDialog(displayUi, msg, "询问", JOptionPane.YES_NO_OPTION);
        return rst == JOptionPane.YES_OPTION;
    }

    // 关闭界面
    public void windowClosing(WindowEvent e) {
        order.requestLogic.giveUp();
        // 关闭窗口
        displayUi.setVisible(false);
        displayUi.dispose();
        System.exit(0);
    }

    public void regretConfirm(){
        if(confirmDialog("是否悔棋?")){
            order.requestLogic.regret();
        }
    }

    public void giveUpConfirm(){
        if(confirmDialog("是否认输?")){
            order.requestLogic.giveUp();
        }
    }

    public void peaceConfirm(){
        if(confirmDialog("是否求和?")){
            order.requestLogic.peace();
        }
    }

    public void restartConfirm(){
        if(confirmDialog("是否重新开始?")){
            order.requestLogic.restart();
        }
    }

    // 返回连接界面
    public void back(){
        displayUi.setVisible(false);
        order.startUiMutual.setVisible(true);
    }
}
