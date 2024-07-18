package code.logic;

import code.container.Order;

import javax.swing.*;
import java.util.List;

public record ResponseLogic(Order order) {

    // Request 不能由逻辑层直接发送的adr,只能做出响应时发送
    public ResponseLogic(Order order) {
        this.order = order;
        order.responseLogic = this;
    }

    public void communication(List<String> params){
        order.messageArea.append(order.responseService.getTargetIp() + ": " + params.get(0) + "\n");
    }

    public void test(List<String> params){
        JOptionPane.showMessageDialog(order.frame, "hello");
    }

    public void a(List<String> params){
        JOptionPane.showMessageDialog(order.frame, params);
    }

}
