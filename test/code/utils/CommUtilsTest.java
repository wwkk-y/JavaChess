package code.utils;

public class CommUtilsTest {
    public static void main(String[] args) {
        new Thread(()->{
            CommUtils.sendMsg(CommUtils.getMyIp(), 3301, "hello world");
        }).start();
        System.out.println(CommUtils.getMsg(3301,1000));
    }
}
