package thread;

/** yield() - 다른 스레드에게 자신에게 주어진 실행시간을 양보한다.
 * 
 * Ex. 스케줄러에 의해 1초의 실행시간을 할당받은 스레드가 0.5초의 시간동안 작업한 상태에서 
 *     yield() 가 호출되면, 나머지 0.5초는 포기하고 다시 실행대기상태가 된다.
 * 
 * .. 아직 이해 못함..
 */
public class ThreadEx18 {

    public static void main(String[] args) {
        ThreadEx18_1 th1 = new ThreadEx18_1("*");
        ThreadEx18_1 th2 = new ThreadEx18_1("**");
        ThreadEx18_1 th3 = new ThreadEx18_1("***");
        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend();
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume();
            Thread.sleep(3000);
            th1.stop();
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e) {
        }
    }
}

class ThreadEx18_1 implements Runnable {

    boolean suspended = false;
    boolean stopped = false;

    Thread th;

    public ThreadEx18_1(String name) {
        this.th = new Thread(this, name);
    }

    @Override
    public void run() {
        String name = this.th.getName();

        while(!stopped) {
            if(!suspended) {
                System.out.println(name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(name + " - interrupted");
                } 
            } else {
                Thread.yield();
            }
        }
        System.out.println(name + " - stopped");
    }

    public void suspend() {
        suspended = true;
        th.interrupt();
        System.out.println(th.getName() + " - interrupt() by suspend()");
    }

    public void stop() {
        stopped = true;
        th.interrupt();
        System.out.println(th.getName() + " - interrupt() by stop()");
    }

    public void resume() { suspended = false; }
    public void start() { th.start(); } 

}
