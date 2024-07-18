package main;

import code.ui.StartUi;

public class TwoWindow {

    public static void main(String[] args) {
        new Thread(() -> new StartUi().setVisible(true)).start();
        new Thread(()-> new StartUi().setVisible(true)).start();
    }

}
