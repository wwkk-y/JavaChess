package code.ui;

import code.container.Order;
import code.logic.ChessBoardLogic;
import code.ui.mutual.ChessboardMutual;
import code.utils.ChessboardUtils;
import code.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ChessBoardUi extends JPanel {
    public static final BufferedImage background = UiUtils.getImg("src\\img\\chessboard.png");
    public final static int xBegin = 12;
    public final static int yBegin = 23;

    public final static ChessboardUtils utils = new ChessboardUtils(xBegin, yBegin, background);

    public final static int width = utils.getWidth();
    public final static int height = utils.getHeight();


    ///////////////////////////////////////////////////////

    private ChessboardMutual mutual;

    public ChessBoardUi(Order order, int camp){
        this.mutual = new ChessboardMutual(order, this);
        new ChessBoardLogic(order, xBegin, yBegin, width, height, camp);

        this.setSize(background.getWidth(),background.getHeight());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mutual.chessboardClickEvent(e);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, null);
        mutual.paintChess(g);
    }

}

