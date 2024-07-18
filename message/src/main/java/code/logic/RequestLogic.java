package code.logic;

import code.container.Order;
import code.utils.CommUtils;

import java.util.Objects;

public class RequestLogic {

    private final Order order;

    public RequestLogic(Order order){
        this.order = order;
        order.requestLogic = this;
    }

    /**
     * 发送信息
     */
    public void communication(String func, String message){
        order.requestService.send(func, message);
    }

    public void communication(String message){
        order.requestService.send("communication", message);
    }

}
