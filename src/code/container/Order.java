package code.container;

import code.logic.ChessBoardLogic;
import code.logic.RequestLogic;
import code.logic.ResponseLogic;
import code.ui.mutual.ChessboardMutual;
import code.ui.mutual.DisplayMutual;
import code.ui.mutual.StartUiMutual;
import code.service.RequestService;
import code.service.ResponseService;

public class Order {

    // 管理交互层 逻辑层 服务层
    // 交互层
    public ChessboardMutual chessboardMutual;
    public DisplayMutual displayMutual;
    public StartUiMutual startUiMutual;

    // 逻辑层
    public ChessBoardLogic chessboardLogic;
    public ResponseLogic responseLogic;
    public RequestLogic requestLogic;

    // 服务层
    public ResponseService responseService;
    public RequestService requestService;

    // 必须通过工厂创建
    protected Order(){};

    public void setTargetIp(String targetIp){
        this.responseService.setTargetIp(targetIp);
        this.requestService.setTargetIp(targetIp);
    }

    public void setTargetAdr(String targetIp, int targetPort){
        setTargetIp(targetIp);
        this.requestService.setTargetPort(targetPort);
    }

}
