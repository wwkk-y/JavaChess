package code.service;

import code.container.Order;
import code.logic.ResponseLogic;
import code.pojo.Response;
import code.utils.CommUtils;
import code.utils.MsgUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseService {
    private boolean goOn = true; // 线程标志

    private final Response response = new Response();

    private final Order order;

    public Map<String, List<Runnable>> listens = new HashMap<>(); // 执行的方法

    public ResponseService(Order order, int myPort) {
        this.order = order;
        order.responseService = this;

        setMyPort(myPort);
        // 每次检测1秒避免卡线程
        // 解析类型
        // 运行用户添加的方法
        // 调用响应处理发起的请求
        // 调用响应服务
        //监听线程
        new Thread(() -> {
            while (true) {
                while (goOn) {
                    ResponseLogic logic = order.responseLogic;
                    String msg = null;
                    if(response.getIp() == null){
                        msg = CommUtils.getMsg(response.getPort(), 1000);
                    }
                    else{
                        msg = CommUtils.getMsg(response.getIp(), response.getPort(), 1000); // 每次检测1秒避免卡线程
                    }
                    if (msg == null) continue;
                    // 解析类型
                    String adr = MsgUtils.getAdr(msg);
                    // 运行用户添加的方法
                    runListens(adr);
                    // 调用响应处理发起的请求
                    try {
                        // 解析参数
                        List<String> params = MsgUtils.getParams(msg);
                        System.out.println("[response get] " + response);
                        System.out.println("[adr] " + adr);
                        System.out.println("[params] " + params);

                        // 将解析的信息交给配置的逻辑处理器处理
                        Method method = logic.getClass().getDeclaredMethod(adr, List.class);
                        method.invoke(logic, params); //
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 添加监听到信息后的执行函数
     * @param type 接收到的信息的类型
     */
    public void addListen(String type,Runnable runnable){
        listens.computeIfAbsent(type, k -> new ArrayList<>());
        listens.get(type).add(runnable);
    }

    public void removeAllListen(String type){
        if(listens.get(type) != null){
            listens.clear();
        }
    }

    private void runListens(String msgType){
        if(listens.get(msgType) != null){
            for (Runnable runnable : listens.get(msgType)) {
                runnable.run();
            }
        }
    }

    public void startListen(){
        goOn = true;
    }

    public void stopListen(){
        goOn = false;
    }

    public void setTargetIp(String ip){
        response.setIp(ip);
    }

    public void setMyPort(int port){
        response.setPort(port);
    }

    public int getMyPort(){
        return response.getPort();
    }

}
