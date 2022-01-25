package concurrent;

/**
 * @author wangjianhua
 * @Description 线程状态转换以及基本操作 interrupt 优雅安全 比stop会强很多
 * @date 2022/1/25/025 22:28
 */
public class InterruptDemo {

    public static void main(String[] args) {
        //sleepThread线程睡眠1000ms
        final Thread sleepThread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        //busyThread一直执行死循环 死循环并不会清除标志位
        Thread busyThread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    ;
                }

            }
        };
        sleepThread.start();
        busyThread.start();
        sleepThread.interrupt();
        busyThread.interrupt();
        //在main方法中会持续监测sleepThread 一旦sleepThread的中断标志位清零 即sleepThread.isInterrupted()返回为false才会继续向下执行
        while (sleepThread.isInterrupted()) {
            ;
        }

        System.out.println("sleepThread is interrupt"+sleepThread.isInterrupted());
        System.out.println("busyThread is interrupt"+busyThread.isInterrupted());
    }
}
