package code.service;

import code.container.Order;
import code.container.OrderFactory;
import code.logic.RequestLogic;
import code.utils.CommUtils;

public class RequestServiceTest {
    public static void main(String[] args) throws InterruptedException {
        Order order = OrderFactory.getOrder();
        order.requestService.setTargetPort(3301);
        order.requestService.setTargetIp(CommUtils.getMyIp());
        System.out.println(order.responseService.getMyPort());
//        order.responseService.setMyPort(3301);
//        order.responseService.startListen();

        int i = 100;
        while(i-->0){
            order.requestService.send(RequestLogic.CONNECT);
            Thread.sleep(2000);
        }

    }
}
