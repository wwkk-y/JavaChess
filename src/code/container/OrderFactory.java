package code.container;

import code.logic.RequestLogic;
import code.logic.ResponseLogic;
import code.service.RequestService;
import code.service.ResponseService;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class OrderFactory {

    public static Order getOrder(){
        // 设置输出流编码为 UTF-8
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Order order = new Order();
        // 绑定对应逻辑
        new ResponseLogic(order);
        new RequestLogic(order);

        // 绑定服务
        new ResponseService(order, 3301);
        new RequestService(order);



        return order;
    }

}
