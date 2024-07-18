package code.utils;

import java.util.ArrayList;
import java.util.List;

public class MsgUtils {
    // 通信协议 adr?param0&param1&param2... 的形式

    public static String getAdr(String msg){
        return msg.substring(0, msg.indexOf("?"));
    }

    public static List<String> getParams(String msg){
        List<String> list = new ArrayList<>();
        int index = msg.indexOf("?") + 1;
        if(index == 0) return list; // 没有参数
        // 开始解析参数
        while(index < msg.length()){
            int end = msg.indexOf("&", index);
            if(end == -1) list.add(msg.substring(index));
            else list.add(msg.substring(index, end));
            index = end + 1;
        }
        return list;
    }

    public static String format(String adr, Object ...objects){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(adr).append("?");
        for (Object obj : objects) {
            stringBuilder.append(obj).append("&");
        }
        return stringBuilder.toString();
    }


}
