package code.ui;

import code.container.Order;
import code.ui.mutual.DisplayMutual;
import code.pojo.Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DisplayUi extends JFrame {
    private JButton regret; // 悔棋
    private JButton giveUp; // 认输

    private JButton peace; // 求和

    private JButton reStart; // 重新开始

    private JLabel tip; // 提示信息

    private JLabel camp; // 阵营信息

    private ChessBoardUi chessBoardUi;

    private DisplayMutual mutual; // 事件


    public DisplayUi(Order order, int camp){
        this.mutual = new DisplayMutual(order, this);

        setUi(order, camp); // 绑定界面
        mutual.setMsg(); // 设置需要变动的信息
        setEvent(); // 设置事件
        mutual.showInitMsg(); // 提示初始信息
    }

    public void setTip(String tip){
        this.tip.setText(tip);
    }


    // 设置固定的界面
    private void setUi(Order order, int camp){
        setTitle("对局界面");
        chessBoardUi = new ChessBoardUi(order, camp);
        setSize(new Dimension(chessBoardUi.getWidth() + 20,
                chessBoardUi.getHeight() + 70)); // 设置界面大小
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mutual.windowClosing(e);
            }
        });
        add(chessBoardUi, BorderLayout.CENTER);
        add(getDown(camp),BorderLayout.SOUTH);
        setLocationRelativeTo(null); // 居中显示窗口
        setResizable(false); // 不允许调整大小
    }

    // 最下方的按钮和标签
    private JPanel getDown(int camp){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setSize(getWidth(), 10);
        if(camp == Chess.CAMP_RED){
            this.camp = new JLabel("你的阵营: 红       当前回合:");
        } else {
            this.camp = new JLabel("你的阵营: 黑       当前回合:");
        }
        panel.add(this.camp);
        tip = new JLabel("tips");
        panel.add(tip);
        panel.add(Box.createHorizontalStrut(10));
        regret = new JButton("悔棋");
        panel.add(regret);
        giveUp = new JButton("认输");
        panel.add(giveUp);
        peace = new JButton("求和");
        panel.add(peace);
        reStart = new JButton("重新开始");
        panel.add(reStart);
        return panel;
    }

    // 设置事件
    private void setEvent(){
        regret.addActionListener(e -> mutual.regretConfirm());
        giveUp.addActionListener(e -> mutual.giveUpConfirm());
        peace.addActionListener(e -> mutual.peaceConfirm());
        reStart.addActionListener(e -> mutual.restartConfirm());
    }


    public void resetChessboard(Order order, int camp){
        remove(chessBoardUi);
        chessBoardUi = new ChessBoardUi(order, camp);
        add(chessBoardUi, BorderLayout.CENTER);
    }

}
