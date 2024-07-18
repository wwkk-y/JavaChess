package code.pojo;

public class Request {
    private String ip; // 目标ip
    private int port; // 目标端口

    @Override
    public String toString() {
        return "Request{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }

    public Request(String ip, int port, String msg) {
        this.ip = ip;
        this.port = port;
    }

    public Request(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Request() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        System.out.println("request ip: " + this.ip + " -> " + ip);
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        System.out.println("request port: " + this.port + " -> " + port);
        this.port = port;
    }
}
