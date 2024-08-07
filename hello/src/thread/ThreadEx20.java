package thread;

/** join() - 다른 스레드의 작업을 기다린다.
 * 
 * 자신이 하고 있는 작업을 잠시 멈추고 다른 스레드가 지정된 시간동안 작업을 수행하도록 할 때 사용한다.
 * 시간을 지정하지 않으면, 해당 스레드가 작업을 모두 마칠 때까지 기다리게 된다.
 * 작업 중에 다른 스레드의 작업이 먼저 수행되어야할 필요가 있을 때 join() 을 사용한다.
 * 
 * join() 도 sleep() 처럼 interrupt() 에 의해 대기상태에서 벗어날 수 있으며, 
 * join() 이 호출되는 부분을 try-catch 문으로 감싸야 한다.
 * 
 * join() 이 sleep() 과 다른 점은 join() 은 현재 스레드가 아닌 특정 스레드에 대해 동작하므로 static 메서드가 아니라는 것이다.
 * 
 * 아래 예제는 메모리를 계속 감소시키면서 매 반복마다 if 문으로 메모리를 확인해 남은 메모리가 전체 40% 미만일 경우,
 * interrupt() 를 호출하여 즉시 가비지 컬렉터 스레드를 깨워 gc() 를 수행하도록 한다.
 * 
 * 그러나, join() 을 사용하지 않으면 MAX_MEMORY 가 1000임에도 불구하고 usedMemory 의 값이 1000을 넘는 경우가 존재한다.
 * 이것은 스레드 gc 가 interrupt() 에 의해 깨어났음에도 불구하고 gc() 가 수행되기 이전에 main 스레드의 작업이 수행되어 
 * 메모리를 사용하기 때문이다.
 * 그래서 스레드 gc 를 깨우는 것뿐만 아니라 join() 을 이용해 스레드 gc 에 작업할 시간을 어느 정도 주고 main 스레드를 기다리도록 하여,
 * 사용할 수 있는 메모리가 확보된 다음에 작업을 계속 하는 것이 필요하다.
 * 그리하여 join() 을 호출하여 스레드 gc 가 0.1초 동안 수행될 수 있도록 해주었다.
 */
public class ThreadEx20 {

    public static void main(String[] args) {
        ThreadEx20_1 gc = new ThreadEx20_1();
        gc.setDaemon(true); // 데몬 스레드로 생성 -> 10초마다 가비지 수행 후 다시 대기 (무한반복)
        gc.start();

        int requiredMemory = 0;

        for (int i = 0; i < 20; i++) {
            requiredMemory = (int)(Math.random() * 10) * 20;

            // 필요한 메모리가 사용할 수 있는 양보다 크거나 전체 메모리의 60% 이상을 사용했을 경우 gc 깨운다
            if(gc.freeMemory() < requiredMemory 
                    || gc.freeMemory() < gc.totalMemory() * 0.4) {
                gc.interrupt(); // 잠자고 있는 쓰레드 gc 를 깨움
                try {
                    gc.join(100);   // 스레드 gc 가 작업할 시간 부여(0.1초)
                } catch (InterruptedException e) {
                }
            }

            gc.usedMemory += requiredMemory;
            System.out.println("usedMemory: " + gc.usedMemory);
        }
    }
}

// 10초마다 한 번씩 가비지 컬렉션 수행하는 스레드
class ThreadEx20_1 extends Thread {
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;

    public void run() {
        while(true) {
            try {
                Thread.sleep(10 * 1000); // 10초
            } catch (InterruptedException e) {
                System.out.println("Awaken by interrupt().");
            }

            gc();
            System.out.println("Garbage Collected. Free Memory : " + freeMemory());
        }
    }

    public void gc() {
        usedMemory -= 300;
        if(usedMemory < 0) usedMemory = 0;
    }

    public int totalMemory() { return MAX_MEMORY; }
    public int freeMemory() { return MAX_MEMORY - usedMemory; }
}
