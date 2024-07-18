package code.ui;


import code.container.Order;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 用于实时绑定端口
 */
public class PortListener implements DocumentListener {
    private final Order order;
    private final PortInput input;


    public PortListener(Order order, PortInput input){
        this.order = order;
        this.input = input;
    }

    private void bindPort(){
        int port = Integer.parseInt(input.getText());
        order.responseService.setMyPort(port);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        bindPort();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        bindPort();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //
    }
}
