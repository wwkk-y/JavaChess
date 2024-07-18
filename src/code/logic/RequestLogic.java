package code.logic;

import code.container.Order;
import code.pojo.Position;
import code.utils.CommUtils;

import java.util.Objects;

public class RequestLogic {
    // 发送信息类型标识符号
    public static final String STEP = "step"; // 走了一步

    public static final String GIVE_UP = "giveUp"; // 认输

    public static final String REGRET = "regret"; // 请求悔棋

    public static final String CONNECT = "connect"; // 请求连接

    public static final String PEACE = "peace"; // 请求求和

    public static final String RESTART = "restart"; // 请求重新开始

    private final Order order;

    public RequestLogic(Order order){
        this.order = order;
        order.requestLogic = this;
    }

    /**
     * 移动了一步
     */
    public void step(Position oldPos, Position newPos){
        System.out.println( "[request send step] " + oldPos + " -> " + newPos);
        // 发送信息给对方设备
        order.requestService.send(STEP, oldPos.x, oldPos.y, newPos.x, newPos.y);
        ChessBoardLogic chessBoardLogic = order.chessboardLogic;
        // 未改变之前步骤容器里添加上这一步
        chessBoardLogic.addProcess(oldPos, newPos);
        // 移动棋子到指定位置
        chessBoardLogic.step(oldPos, newPos);
        // 设置提示
        order.displayMutual.setTip("对方的回合");
    }

    /**
     * 认输
     */
    public void giveUp(){
        order.requestService.send(GIVE_UP);
        order.displayMutual.back();
    }

    /**
     * 请求悔棋
     */
    public void regret(){
        order.requestService.send(REGRET);
    }

    /**
     * 请求重新开始
     */
    public void restart(){
        order.requestService.send(RESTART);
    }

    /**
     * 请求求和
     */
    public void peace(){
        order.requestService.send(PEACE);
    }

    /**
     * 请求连接
     */
    public void connect(Object ...params){
        String targetIp = order.requestService.getTargetIp();
        int targetPort = order.requestService.getTargetPort();
        // targetIp + targetPort = myIp + myPort 禁止连接
        if(Objects.equals(targetIp, CommUtils.getMyIp()) &&
                Objects.equals(targetPort, order.responseService.getMyPort())){
            order.startUiMutual.showMsg("不能连接自己! 请求对象: " + targetIp + ":" + targetPort);
            return;
        }
        order.requestService.send(CONNECT, params);
    }

}
