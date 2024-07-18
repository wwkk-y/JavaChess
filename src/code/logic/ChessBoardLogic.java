package code.logic;

import code.container.Order;
import code.pojo.*;
import code.pojo.chesses.General;

import java.awt.*;
import java.util.List;
import java.util.Stack;

public class ChessBoardLogic {

    private final Order order;

    private ChessBoard chessBoard; // 棋盘

    private final Player player; // 玩家信息

    public ChessBoardLogic(Order order, int xBegin, int yBegin, int width, int height, int camp) {
        this.order = order;
        order.chessboardLogic = this;

        this.chessBoard = new ChessBoard(xBegin, yBegin, width, height);
        this.player = new Player(camp, chessBoard);
    }

    public int getCamp(){
        return player.camp;
    }

    /**
     * 因为所有逻辑都是基于红方在下来处理的，所以当黑方在下时需要反转坐标
     * 绘制时为黑方也需要反转达到颠倒的效果
     * @param pos 需要反转的坐标
     * @return 反转后的坐标
     */
    private Position getRealPos(Position pos){
        // 如果是黑方 反转坐标
        if(getCamp() == Chess.CAMP_BLACK){
            return new Position(8 - pos.x, 9 - pos.y);
        }
        return pos;
    }

    /**
     * 绘制棋盘
     */
    public void paint(Graphics g){
        int xBegin = chessBoard.getXBegin();
        int yBegin = chessBoard.getYBegin();
        int width = chessBoard.getWidth();
        int height = chessBoard.getHeight();
        for (int y = 0; y <= 9; ++y){
            for(int x = 0; x <= 8; ++x){
                Chess chess = chessBoard.getChess(y, x);
                if(chess != null){
                    // 根据阵营计算绘制真实坐标
                    Position pos = getRealPos(new Position(x, y));
                    g.drawImage(chess.getImage(),
                            xBegin + width * pos.x,
                            yBegin + height * pos.y,
                            null);
                }
            }
        }
        paintRoad(g); // 绘制路径
    }

    /**
     * 绘制选中的棋子可以走的路径
     * @param g 绘制对象
     */
    public void paintRoad(Graphics g){
        int xBegin = chessBoard.getXBegin();
        int yBegin = chessBoard.getYBegin();
        int width = chessBoard.getWidth();
        int height = chessBoard.getHeight();
        Chess selected = player.getSelected();
        List<Position> positions = player.getPositions();
        if(selected != null && positions.size() > 0){
            int camp = selected.getCamp();
            int r = 10;
            for (Position position : positions) {
                // 根据阵营计算绘制真实坐标
                Position pos = getRealPos(position);
                int x = pos.x + xBegin + width * pos.x + width / 2 - r / 2;
                int y = pos.y + yBegin + height * pos.y + height / 2 - r / 2;
                if(camp == Chess.CAMP_RED) g.setColor(Color.BLUE);
                if(camp == Chess.CAMP_BLACK) g.setColor(Color.WHITE);
                g.fillOval(x - r / 2, y - r / 2, r, r);
            }
        }
    }

    /**
     * 对于选中的位置处理
     * 1. 如果可以走 尝试移动之前选择的棋子
     * 2. 如果不可以走 设置选中的棋子为空
     */
    public void setRoads(Position pos){
        // 根据阵营计算逻辑处理时红方在下的坐标
        pos = getRealPos(pos);
        if(player.judgePos(pos)){
            // 如果目标位置可以走
            moveSelectedTo(pos);
        } else {
            // 目标位置不可以走，更新选择的棋子
            player.setSelected(null); // 如果不可以走 ,selected 设置为false
        }
        order.chessboardMutual.repaint();
    }

    /**
     * 尝试移动之前选择的棋子到目标位置
     * @param pos 目标位置
     */
    private void moveSelectedTo(Position pos){
        if(player.getSelected() == null){
            Chess next = player.getChessBoard().getChess(pos);
            player.setSelected(next);
        } else {
            // 走了一步
            Position oldPos = player.getSelected().getPos();
            order.requestLogic.step(oldPos, pos);
        }
    }


    /**
     * 移动一步
     */
    public void step(Position oldPos, Position newPos){

        // 如果旧的位置为General 则一方输
        Chess eatChess = chessBoard.getChess(newPos);

        // 交换当前出棋的阵营
        player.changeCurCamp();

        // 设置新位置为旧位置的棋子并把原来的位置设置为null
        Chess chess = chessBoard.getChess(oldPos);
        chessBoard.setChess(oldPos, null);
        chessBoard.setChess(newPos, chess);

        // 更新棋盘界面
        order.chessboardMutual.repaint();

        // 如果旧的位置为General 则一方输
        if(eatChess instanceof General){
            order.displayMutual.gameOver(eatChess.getCamp());
        }
    }

    private final Stack<MoveProcess> processes = new Stack<>(); // 保存步骤

    /**
     * 步骤记录容器里面添加一步步骤
     */
    public void addProcess(Position oldPos, Position newPos){
        processes.add(new MoveProcess(oldPos, newPos, chessBoard));
    }

    /**
     * 悔棋，往回走两步
     */
    public void backTwoStep(){
        if(processes.size() < 2){
            order.displayMutual.showMsg("步骤太少，无法悔棋");
            return;
        }
        MoveProcess process = processes.pop();
        chessBoard.setChess(process.getNewPos(), process.getNewChess());
        chessBoard.setChess(process.getOldPos(), process.getOldChess());
        System.out.println("[back] " + process);
        process = processes.pop();
        chessBoard.setChess(process.getNewPos(), process.getNewChess());
        chessBoard.setChess(process.getOldPos(), process.getOldChess());
        System.out.println("[back] " + process);
        order.chessboardMutual.repaint();
    }
}
