package code.service;

import code.container.Order;
import code.pojo.Request;
import code.utils.CommUtils;
import code.utils.MsgUtils;

import java.util.Arrays;

public class RequestService {

    private final Request request = new Request();

    private final Order order;

    public RequestService(Order order){
        this.order = order;
        order.requestService = this;
    }

    public void send(String adr, Object ...params){
        System.out.println("[request send] " + request);
        System.out.println("[adr] " + adr);
        System.out.println("[params] " + Arrays.toString(params));
        CommUtils.sendMsg(request.getIp(), request.getPort(), MsgUtils.format(adr, params));
    }

    public void setTargetIp(String ip){
        request.setIp(ip);
    }

    public String getTargetIp(){
        return request.getIp();
    }

    public void setTargetPort(int port){
        request.setPort(port);
    }

    public int getTargetPort(){
        return request.getPort();
    }
}
