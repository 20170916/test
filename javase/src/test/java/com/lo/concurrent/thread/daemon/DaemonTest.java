package com.lo.concurrent.thread.daemon;

public class DaemonTest {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 守护线程的声明周期什么时候结束由自己和jvm共同决定；
         * 如果jvm还为结束，守护线程可以正常退出，并不是要等到所有用户线程结束才退出。
         * 等到所有用户线程退出后再退出的守护线程本身的声明周期就很长。
         */
        Thread daemon_thread=new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                System.out.println("i am a daemon thread++++++"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemon_thread.setDaemon(true);
        daemon_thread.setName("daemon_thread");
        daemon_thread.start();

        Thread user_thread=new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                System.out.println("i am a user thread-----"+i);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        user_thread.setName("user_thread");
        user_thread.start();

        for(;;){
            //Monitor Ctrl-Break是idea创建的监控线程
            Thread.currentThread().getThreadGroup().list();
            Thread.sleep(1000);
        }
    }

    public static void countThread() throws InterruptedException {
        ThreadGroup currentGroup =Thread.currentThread().getThreadGroup();
        /*while(currentGroup !=null){
            currentGroup = currentGroup.getParent();
        }*/

        for(;;){
            final int ac = Thread.activeCount();
            System.out.println("count:"+ac);
            Thread[] acThread=new Thread[ac];
            currentGroup.enumerate(acThread);

            for (Thread thread : acThread) {
                System.out.println("id: "+thread.getId()+" name: "+ thread.getName() + " state: "+thread.getState());
            }
            Thread.sleep(500);
        }
    }
}
