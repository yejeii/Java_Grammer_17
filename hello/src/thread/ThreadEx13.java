package thread;

import javax.swing.JOptionPane;

/** interrupt() , interrupted() - 스레드 작업을 취소한다. 
 * 
 * 진행 중인 스레드의 작업이 끝나기 전에 취소시켜야 할 때가 있다.
 * Ex. 큰 파일을 다운받을 때 시간이 너무 오래 걸려 중간에 포기할 경우
 * 
 * interrupt() 는 스레드에게 작업을 멈추라고 요청한다.
 * 단지 멈추라고 요청만 할 뿐, 스레드를 강제]로 종료시키지는 못한다.
 * interrupt() 는 그저 스레드의 interrupted 상태(인스턴스 변수)를 false -> true 로 바꾸는 것일 뿐이다.
 * 
 * interrupted() 는 스레드에 대해 interrupt() 가 호출되었는지를 알려주고, interrupted 상태를 false 로 초기화한다.
 * 
 * 스레드가 sleep(), wait(), join() 에 의해 일시정지 상태(WAITING)에 있을 때 해당 스레드에 대해 interrupt() 을 호출하면, 
 * sleep(), wait(), join() 에서 InterruptedException 이 발생하고 스레드는 실행대기 상태(RUNNABLE)로 바뀐다.
 * 즉, 멈춰있던 스레드를 깨워서 실행가능한 상태로 만든다.
 * 
 * 아래 예제는 카운트다운(스레드) 도중 사용자 입력이 들어오면 카운트다운을 종료하는 예제이다.
*/
public class ThreadEx13 {

    public static void main(String[] args) {
        ThreadEx13_1 th1 = new ThreadEx13_1();
        th1.start();

        // 입력값을 치면 스레드에게 작업 중단 요청
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
        System.out.println("입력하신 값은 " + input + "입니다.");
        th1.interrupt();    // interrupt() 를 호출하면 interrupted 상태가 true 가 됨
        System.out.println("isInterrupted(): " + th1.isInterrupted());
    }
}

// 파일 다운 스레드라고 생각
class ThreadEx13_1 extends Thread {
    @Override
    public void run() {
        int i = 10;

        while(i!=0 && !isInterrupted()) {
            System.out.println(i--);

            // Test 1. 시간 지연
            // for(long x=0; x<2500000000L; x++) { }    

            // Test 2. sleep() 이용한 시간 지연
            //         결과 : 카운트 종료되지 않음 
            //         이유 : sleep() 에 의해 스레드가 잠시 멈춰있을 때 interrupt() 호출하면 InterruptedException 발생,
            //                해당 스레드는 다시 실행대기 상태가 되고, 스레드의 interrupted 상태는 false 로 자동 초기화됨 
            //         해결 : catch 블럭 하위에 interrupt() 호출하여 스레드의 interrupt 상태를 true 로 바꿔줘야 함  
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                interrupt();
            }
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}
