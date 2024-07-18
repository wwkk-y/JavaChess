package code.pojo;


import java.util.List;

public class Player {
    private int curCamp = Chess.CAMP_RED; // 当前出棋的阵营
    public final int camp; // 自己的阵营

    private List<Position> positions; // 路径,选中的棋子可以走的位置

    private Chess selected; // 选择的棋子

    private ChessBoard chessBoard; // 绑定的棋盘

    public Player(int camp, ChessBoard chessBoard){
        this.camp = camp;
        this.chessBoard = chessBoard;
    }

    public int getCurCamp() {
        return curCamp;
    }

    public void setCurCamp(int curCamp) {
        this.curCamp = curCamp;
    }

    public void changeCurCamp(){
        if(this.curCamp == Chess.CAMP_BLACK) this.setCurCamp(Chess.CAMP_RED);
        else this.setCurCamp(Chess.CAMP_BLACK);
        this.setSelected(null); // 更改了阵营后选择的棋子清空
    }

    public Chess getSelected() {
        return selected;
    }

    public void setSelected(Chess selected) {
        if(selected == null || selected.getCamp() == this.camp){
            this.selected = selected;
            updatePositions();
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void updatePositions(){
        if(selected !=null)
            this.positions = this.selected.getAllViablePos();
    }

    // 判断路径是否可以走
    public boolean judgePos(Position pos){
        if(curCamp != camp) return false; // 不是当前出棋的阵营一定不可以走
        if(selected == null) return true; // 没有选择棋子一定可以走
        if(positions == null) return false; // 没有路径一定不可以走
        return positions.contains(pos); // 如果包含就可以走
    }

}
