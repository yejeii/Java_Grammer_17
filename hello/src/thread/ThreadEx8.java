package thread;

/** 쓰레드의 우선순위
 * 
 * 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받는다.
 * main() 를 수행하는 쓰레드는 우선순위가 5 -> main() 에서 생성하는 쓰레드의 우선순위는 역시 5가 된다.
 * 
 * 그러나, 멀티코어에서는 쓰레드의 우선순위에 따른 차이가 거의 없다.
 * 멀티코어라 해도, OS 마다 다른 방식으로 스케쥴링하기 떄문에, 어떤 OS에서 실행하느냐에 따라 다른 결과를 얻을 수 있다.
 * 굳이 우선순위에 차등을 두어 쓰레드를 실행하려면, 특정 OS 의 스케쥴링 정책과 JVM 의 구현을 직접 확인해봐야 한다.
 * 자바는 쓰레드가 우선순위에 따라 어떻게 다르게 처리되어야 하는지에 대해 강제 X -> 쓰레드의 우선순위와 관련된 구현이 JVM 마다 다를 수 있기 때문이다.
 * 만일 확인한다 하더라도 OS 의 스케쥴러에 종속적이라서 어느 정도 예측만 가능한 정도일 뿐 정확히 알 수는 없다...
 * 
 * 쓰레드에 우선순위를 두는것보단 차라리 작업에 우선순위를 두어 PriorityQueue 에 저장해 놓고, 
 * 우선순위가 높은 작업이 먼저 처리되도록 하는 것이 나을 수 있다.
 */
public class ThreadEx8 {
    static long startTime = 0;
    public static void main(String[] args) {

        ThreadEx8_1 th1 = new ThreadEx8_1();
        ThreadEx8_2 th2 = new ThreadEx8_2();

        th2.setPriority(7);
        
        System.out.println("Priority of th1 (-) : " + th1.getPriority());
        System.out.println("Priority of th2 (|) : " + th2.getPriority());

        th1.start();
        startTime = System.currentTimeMillis();

        th2.start();
        startTime = System.currentTimeMillis();
    }
}

class ThreadEx8_1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
            for (int j = 0; j < 10000000; j++) {    // 작업을 지연시키기 위한 for문
            }
        }
        System.out.println("th1 소요시간: " + (System.currentTimeMillis() - ThreadEx8.startTime));
    }   
}

class ThreadEx8_2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
            for (int j = 0; j < 10000000; j++) {    // 작업을 지연시키기 위한 for문
            }
        }
        System.out.println("th2 소요시간: " + (System.currentTimeMillis() - ThreadEx8.startTime));
    }
    
}
