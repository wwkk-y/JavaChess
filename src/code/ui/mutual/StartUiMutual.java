package code.ui.mutual;

import code.container.Order;
import code.logic.RequestLogic;
import code.pojo.Chess;
import code.ui.StartUi;
import code.utils.CommUtils;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class StartUiMutual {

    private final Order order;

    private StartUi startUi;

    public StartUiMutual(Order order, StartUi startUi){
        this.order = order;
        order.startUiMutual = this;

        this.startUi = startUi;
        startUi.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // 窗口每一次可见时就把目标地址设置为null，这样就可以接收所有地址发过来的请求
                order.responseService.setTargetIp(null);
            }
        });
    }

    public void connectReq(String targetIp, int targetPort){
        // 只设置request ,不然response就只响应targetIp了
        order.requestService.setTargetIp(targetIp);
        order.requestService.setTargetPort(targetPort);

        order.requestLogic.connect(
                Chess.CAMP_RED,
                CommUtils.getMyIp(),
                order.responseService.getMyPort()
        );
    }

    public boolean isVisible(){
        return startUi.isVisible();
    }

    public void setVisible(boolean visible){
        startUi.setVisible(visible);
    }

    // 展示一个询问界面
    public boolean confirmDialog(String msg){
        int rst = JOptionPane.showConfirmDialog(startUi, msg, "询问", JOptionPane.YES_NO_OPTION);
        return rst == JOptionPane.YES_OPTION;
    }

    // 展示一个提示界面
    public void showMsg(String msg){
        JOptionPane.showMessageDialog(startUi, msg);
    }
}
