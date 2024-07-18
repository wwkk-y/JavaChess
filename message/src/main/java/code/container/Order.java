package code.container;

import code.logic.RequestLogic;
import code.logic.ResponseLogic;
import code.service.RequestService;
import code.service.ResponseService;

import javax.swing.*;

public class Order {

    // 管理交互层 逻辑层 服务层
    public ResponseLogic responseLogic;
    public RequestLogic requestLogic;

    // 服务层
    public ResponseService responseService;
    public RequestService requestService;

    public JTextArea messageArea;

    public JFrame frame;

    // protected 只能同一个包内使用必须通过工厂创建
    protected Order(){};

    public void setTargetIp(String targetIp){
        this.responseService.setTargetIp(targetIp);
        this.requestService.setTargetIp(targetIp);
    }

    public void setTargetAdr(String targetIp, int targetPort){
        setTargetIp(targetIp);
        this.requestService.setTargetPort(targetPort);
    }

}
