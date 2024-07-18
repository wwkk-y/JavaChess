package code;

import code.container.Order;
import code.container.OrderFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {

    public void createApplication(String targetIp, int targetPort, int myPort){
        Order order = OrderFactory.getOrder(); // 服务员
        // 设置通信的ip和端口
        order.requestService.setTargetIp(targetIp);
        order.requestService.setTargetPort(targetPort);
        order.responseService.setTargetIp(targetIp);
        order.responseService.setMyPort(myPort);

        // 创建窗口
        JFrame frame = new JFrame("通信窗口");
        order.frame = frame;
        frame.setSize(500, 400);

        JPanel up = new JPanel();
        JTextField func = new JTextField(15);
        func.setText("communication");
        JTextField message = new JTextField(20);
        JButton btn = new JButton("发送");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.requestService.send(func.getText(), message.getText());
            }
        });
        up.add(message);
        up.add(btn);
        up.add(func);

        frame.add(up, BorderLayout.NORTH);

        JPanel center = new JPanel();
        JTextArea messageArea = new JTextArea(18, 40);
        center.add(new JScrollPane(messageArea));
        order.messageArea = messageArea;
        frame.add(center);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Thread(() -> new Application().createApplication("127.0.0.1", 3001, 3002)).start();
        new Thread(() -> new Application().createApplication("127.0.0.1", 3002, 3001)).start();
    }
}
