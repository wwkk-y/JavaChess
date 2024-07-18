package code.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CommUtils {

    private static String myIp;

    /**
     * 给指定 ip: port 发送信息
     * @param targetIp 指定对方地址
     * @param targetPort 指定对方端口
     * @param msg 发送的信息
     */
    public static void sendMsg(String targetIp, int targetPort, String msg){
        System.out.println("send " + targetIp + ":" + targetPort + "/" + msg);
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(targetIp);
            byte[] buffer = msg.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address,targetPort);
            socket.send(packet);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            if(socket != null) socket.close();
        }
    }

    /**
     * 接收指定ip, port的信息
     * @param targetIp 指定对方地址
     * @param myPort 指定自己的端口
     * @return 接收到的信息
     */
    public static String getMsg(String targetIp, int myPort){
        byte[] buffer = new byte[1024]; // 接收消息的缓冲区
        String msg = null;
        DatagramSocket socket = null;
        try {
            InetAddress address = InetAddress.getByName(targetIp);
            socket = new DatagramSocket(myPort, address);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            // 如果没有接收到任何数据包，程序将一直等待在socket.receive(packet)处，直到接收到数据包或发生异常。
            socket.receive(packet);
            msg = new String(packet.getData(), 0, packet.getLength());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            if(socket != null) socket.close();
        }
        return msg;
    }

    /**
     * 接收指定ip, port的信息
     * @param targetIp 指定对方地址
     * @param myPort 指定自己的端口
     * @param timeout 超时时间ms
     * @return 接收到的信息
     */
    public static String getMsg(String targetIp, int myPort, int timeout){
        byte[] buffer = new byte[1024]; // 接收消息的缓冲区
        String msg = null;
        DatagramSocket socket = null;
        try {
            InetAddress address = InetAddress.getByName(targetIp);
            socket = new DatagramSocket(myPort, address);
            socket.setSoTimeout(timeout); // 设置接收时间
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            // 如果没有接收到任何数据包，程序将一直等待在socket.receive(packet)处，直到接收到数据包或发生异常。
            socket.receive(packet);
            msg = new String(packet.getData(), 0, packet.getLength());
            socket.close();
        } catch (Exception e) {
            System.out.print("-");
//            System.out.println("get error " + targetIp + ":" + myPort);
            if(socket != null) socket.close();
        }
        if(msg != null)  System.out.println("get " + targetIp + ":" + myPort + "/" + msg);
        return msg;
    }

    /**
     * 接收指定port的信息
     * @param myPort 指定自己的端口
     * @param timeout 超时时间ms
     * @return 接收到的信息
     */
    public static String getMsg(int myPort, int timeout){
        byte[] buffer = new byte[1024]; // 接收消息的缓冲区
        String msg = null;
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(myPort);
            socket.setSoTimeout(timeout); // 设置接收时间
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            // 如果没有接收到任何数据包，程序将一直等待在socket.receive(packet)处，直到接收到数据包或发生异常。
            socket.receive(packet);
            msg = new String(packet.getData(), 0, packet.getLength());
            socket.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.print("-");
            if(socket != null) socket.close();
        }
        if(msg != null) System.out.println("get :" + myPort + "/" + msg);
        return msg;
    }


    /**
     * 得到自己的ip地址
     * @return 自己的ip
     */
    public static String getMyIp(){
        if(myIp == null){
            try {
                InetAddress localhost = InetAddress.getLocalHost();
                myIp = localhost.getHostAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return myIp;
    }
}
