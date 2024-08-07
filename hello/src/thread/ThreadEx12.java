package thread;

/** sleep(long mills) - 일정시간동안 스레드를 일시정지 상태로 한다.
 * 
 * sleep() 에 의해 일시정지 상태가 된 스레드는 지정된 시간이 다 되거나 interrupt() 이 호출(InterruptedException)되면,
 * 잠에서 깨어나 다시 실행대기 상태가 된다.
 * 
 * sleep() 은 항상 현재 실행 중인 스레드에 대해 작동한다
 * -> 참조변수.sleep() 로 하더라도 실제로 영향을 받는 것은 main() 을 실행하는 main 스레드이다.
 * -> sleep() 은 static 으로 선언되어 있으며, Thread.sleep(2000) 으로 사용해야 한다.
 */
public class ThreadEx12 {

    public static void main(String[] args) {
        ThreadEx12_1 th1 = new ThreadEx12_1();
        ThreadEx12_2 th2 = new ThreadEx12_2();
        th1.start();
        th2.start();

        try {
            // th1.sleep(2000);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        
        System.out.println("<<main 종료>>");
    } // main
}

class ThreadEx12_1 extends  Thread {

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.println("<<th1 종료>>");
    } // run()
}

class ThreadEx12_2 extends  Thread {

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.println("<<th2 종료>>");
    } // run()
}
