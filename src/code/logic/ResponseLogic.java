package code.logic;

import code.container.Order;
import code.pojo.Chess;
import code.pojo.Position;
import code.ui.DisplayUi;
import code.utils.CommUtils;

import java.util.List;

public record ResponseLogic(Order order) {

    // Request 不能由逻辑层直接发送的adr,只能做出响应时发送
    private static final String PEACE_OK = "peaceOk"; // 同意求和

    private static final String PEACE_NO = "peaceNo"; // 不同意求和

    private static final String REGRET_OK = "regretOk"; // 同意悔棋

    private static final String REGRET_NO = "regretNo"; // 不同意悔棋

    private static final String RESTART_OK = "restartOk"; // 同意重新开始

    private static final String RESTART_NO = "restartNo"; // 拒绝重新开始

    public ResponseLogic(Order order) {
        this.order = order;
        order.responseLogic = this;
    }

    public void step(List<String> params) {
        Position oldPos = new Position(params.get(0), params.get(1));
        Position newPos = new Position(params.get(2), params.get(3));
        // 未改变之前步骤容器里添加上这一步
        order.chessboardLogic.addProcess(oldPos, newPos);
        // 移动棋子到指定位置
        order.chessboardLogic.step(oldPos, newPos);
        // 设置提示
        order.displayMutual.setTip("你的回合");
    }

    public void giveUp(List<String> params) {
        order.displayMutual.showMsg("对方认输");
        order.displayMutual.back();
    }


    public void regret(List<String> params) {
        if (order.displayMutual.confirmDialog("对方想要悔棋, 是否同意?")) {
            // 同意
            order.requestService.send(REGRET_OK);
            order.chessboardLogic.backTwoStep();
        } else {
            // 不同意
            order.requestService.send(REGRET_NO);
        }
    }

    public void regretOk(List<String> params) {
        order.chessboardLogic.backTwoStep();
    }

    public void regretNo(List<String> params) {
        order.displayMutual.showMsg("对方不同意悔棋");
    }

    public void connect(List<String> params) {
        if (order.startUiMutual.isVisible()) {
            // startUi可见时才监听
            // 谁先发送让对方当红方的信息，谁就当黑方，
            // 与对方建立连接, 请求让他当黑方即可
            int camp = Integer.parseInt(params.get(0));
            String targetIp = params.get(1);
            int targetPort = Integer.parseInt(params.get(2));
            // 询问是否建立连接
            // 如果收到了让自己当黑方的信息，就是自己发的请求，就不用问了
            if (camp == Chess.CAMP_BLACK ||
                    order.startUiMutual.confirmDialog(targetIp + ":" + targetPort + "请求与你建立连接,是否同意?")
            ) {
                order.setTargetAdr(targetIp, targetPort);
                order.requestLogic.connect(Chess.CAMP_BLACK, CommUtils.getMyIp(), order.responseService.getMyPort());
                // 进入游戏开始界面
                new DisplayUi(order, camp).setVisible(true);
                order.startUiMutual.setVisible(false);
            }

        }
    }

    public void peace(List<String> params) {
        if (order.displayMutual.confirmDialog("对方想要求和, 是否同意?")) {
            // 同意
            order.requestService.send(PEACE_OK);
            order.displayMutual.reset();
        } else {
            // 不同意
            order.requestService.send(PEACE_NO);
        }
    }

    public void peaceOk(List<String> params) {
        order.displayMutual.reset();
    }

    public void peaceNo(List<String> params) {
        order.displayMutual.showMsg("对方不同意求和");
    }

    public void restart(List<String> params) {
        if (order.displayMutual.confirmDialog("对方想要重新开始, 是否同意?")) {
            // 同意
            order.requestService.send(RESTART_OK);
            order.displayMutual.reset();
        } else {
            // 不同意
            order.requestService.send(RESTART_NO);
        }
    }

    public void restartOk(List<String> params) {
        order.displayMutual.reset();
    }

    public void restartNo(List<String> params) {
        order.displayMutual.showMsg("对方不同意重新开始");
    }
}
