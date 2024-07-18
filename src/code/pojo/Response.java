package code.pojo;

public class Response {
    private String ip; // 目标ip
    private int port; // 指定自己开放端口接收信息

    @Override
    public String toString() {
        return "Response{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }

    public Response(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }


    public Response() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        System.out.println("response port: " + this.port + " -> " + port);
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        System.out.println("response ip: " + this.ip + " -> " + ip);
        this.ip = ip;
    }
}
