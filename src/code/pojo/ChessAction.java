package code.pojo;

import code.utils.ChessUtils;

import java.util.List;

public interface ChessAction {

    /**
     * 得到所有可以移动的位置
     * @return 所有可以移动的位置
     */
    List<Position> getAllViablePos();

    /**
     * 设置背景图像路径
     */
    void setImgPath();

    /**
     * 判断对应位置是否可以走
     * @return true表示可以走
     */
    default boolean judgePos(Position pos){
        return ChessUtils.judgePos((Chess) this,pos);
    }

}
