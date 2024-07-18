package code.pojo;

/**
 * 工具类用来记录所有移动过程，用来悔棋
 */
public class MoveProcess {
    private final Position oldPos; // 原位置
    private final Chess oldChess; // 原位置的棋子
    private final Position newPos; // 新位置

    private final Chess newChess; // 新位置的棋子


    public MoveProcess(Position oldPos, Position newPos, ChessBoard chessBoard) {
        this.oldPos = oldPos;
        this.oldChess = chessBoard.getChess(oldPos);
        this.newPos = newPos;
        this.newChess = chessBoard.getChess(newPos);
    }

    @Override
    public String toString() {
        return "MoveProcess{" +
                "oldPos=" + oldPos +
                ", oldChess=" + oldChess +
                ", newPos=" + newPos +
                ", newChess=" + newChess +
                '}';
    }

    public Position getOldPos() {
        return oldPos;
    }

    public Chess getOldChess() {
        return oldChess;
    }

    public Position getNewPos() {
        return newPos;
    }

    public Chess getNewChess() {
        return newChess;
    }
}
