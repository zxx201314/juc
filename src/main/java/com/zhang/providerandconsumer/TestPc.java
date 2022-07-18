package com.zhang.providerandconsumer;


/**
 * 测试：生产者消费者模型--------->利用缓冲区解决：管程法
 * 生产者、消费者、产品
 * 1、缓冲区
 * 2、标志位
 */
public class TestPc {
    public static void main(String[] args) {
        SyncContainer syncContainer = new SyncContainer();
        new Provider(syncContainer).start();
        new Consumer(syncContainer).start();
    }

}

//生产者
class Provider extends Thread {

    SyncContainer syncContainer;

    public Provider(SyncContainer syncContainer){
        this.syncContainer = syncContainer;
    }
    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            syncContainer.push(new Chicken(i));
            System.out.println("生产了" + i + "只鸡");
        }
    }


}

//消费者
class Consumer extends Thread {

    SyncContainer syncContainer;

    public Consumer(SyncContainer syncContainer){
        this.syncContainer = syncContainer;
    }
    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            System.out.println("消费了" + syncContainer.pop().id + "只鸡");
        }
    }
}

//产品
class Chicken {
    int id;
    public Chicken(int id ){
        this.id = id;
    }
}

//缓冲区
class SyncContainer {
    //需要一个固定数量的容器
    Chicken[] chickens = new Chicken[10];
    //容器计数器，用于判断生产和消费的具体数量
    int count = 0;

    //生产者生产商品
    public synchronized void push(Chicken chicken) {
        //如果容器满了，就通知消费者消费
        if (count == chickens.length) {
            //通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        chickens[count] = chicken;
        count++;
        System.out.println("生产者生产了第" + chicken.id + "产品>>>>>>容器容量：" + count);
        //可以通知消费者消费了
        this.notify();
    }

    //消费者消费商品
    public synchronized Chicken pop() {
        //判断能否消费
        if (count == 0) {
            //等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Chicken chicken = chickens[count];
        System.out.println("容器容量："+count+">>>>>消费者消费了第"+ chicken.id+"个产品");
        this.notifyAll();
        return chicken;
    }
}
