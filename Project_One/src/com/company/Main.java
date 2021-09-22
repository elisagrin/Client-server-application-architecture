package com.company;

public class Main {

    public static void main(String[] args) {
        WaitNotify flag=new WaitNotify();
        PingPong ping = new PingPong(flag, "ping");
        PingPong pong = new PingPong(flag, "pong");
        new Thread(ping).start();
        new Thread(pong).start();
    }
}

class WaitNotify{
    private int i=0;
    public synchronized void put(String name){
        while (i <= 11) {
            if (name == "ping")
                System.out.println("PING");
            else System.out.println("PONG");
            i++;
            notify();
            try {
                wait();
            }
            catch (InterruptedException e){
            }
        }
    }
}

class PingPong implements Runnable{
    WaitNotify flag;
    String name;
    PingPong(WaitNotify flag, String name){
        this.flag=flag;
        this.name = name;
    }
    public void run(){
        flag.put(name);

    }
}
