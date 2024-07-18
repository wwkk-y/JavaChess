package code.ui;

import code.container.Order;
import code.container.OrderFactory;
import code.ui.mutual.StartUiMutual;
import code.utils.CommUtils;

import javax.swing.*;
import java.awt.*;

public class StartUi extends JFrame {

    public final Order order = OrderFactory.getOrder(); // 服务员

    private JLabel myIp;
    private JTextField targetIp; // 目标ip
    private PortInput myPort; // 自己的端口
    private PortInput targetPort; // 目标端口
    private JButton confirmButton; // 发送请求的按钮

    private final StartUiMutual mutual;

    public StartUi() {
        mutual = new StartUiMutual(order, this);
        setUi();
        setEvent();
    }

    public void setEvent(){
        // 自己的端口实时修改
        myPort.getDocument().addDocumentListener(
                new PortListener(order, myPort)
        );

        // 发送连接请求
        confirmButton.addActionListener(e -> mutual.connectReq(
                targetIp.getText(), Integer.parseInt(targetPort.getText())
        ));
    }

    public void setUi(){
        setTitle("连接界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400, 350)); // 设置界面大小

        myIp = new JLabel("  本地 IP 地址: " + CommUtils.getMyIp());
        myIp.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(40)); // 添加垂直间距
        add(myIp);
        add(Box.createVerticalStrut(20)); // 添加垂直间距

        targetIp = new JTextField(10);
        targetIp.setText(CommUtils.getMyIp());
        addTextFieldWithLabel("目标 IP 地址： ", targetIp);

        targetPort = new PortInput(10);
        targetPort.setText("3301");
        addTextFieldWithLabel("输入目标端口：", targetPort);

        myPort = new PortInput(10);
        myPort.setText("3301");
        addTextFieldWithLabel("输入本地端口：", myPort);

        confirmButton = new JButton("连接");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(confirmButton);
        add(Box.createVerticalStrut(50)); // 添加垂直间距

        pack();
        setLocationRelativeTo(null); // 居中显示窗口
        setResizable(false); // 不允许调整大小

    }

    private void addTextFieldWithLabel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JLabel label = new JLabel(labelText);
//        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 14)); // 设置字体
        panel.add(label);
        panel.add(textField);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panel);
    }

}
