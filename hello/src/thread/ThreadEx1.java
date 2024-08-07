package thread;

/** 동기화의 필요성 
 * 
 * 식당에서 음식(dish)을 만들어서 테이블에 추가(add)하는 요리사(Cook)와 
 * 테이블의 음식을 소비(remove)하는 손님(Customer)을 스레드로 구현
 * 
 * 1. 요리사 스레드가 테이블에 음식을 다 놓은 후에야 손님 스레드는 음식을 소비할 수 있다.
 * 2. 손님1 스레드 손에 들고 있는 음식은 다른 손님 스레드에 의해 가로채질 수 없다.
 * 
*/
public class ThreadEx1 {

    public static void main(String[] args) {
        Runnable r = new RunnableEx21();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class Account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int money) {
        synchronized (this) {
            if(balance >= money) {
                try {
                    Thread.sleep(1000); // 다른 스레드에게 제어권 pass
                } catch(InterruptedException e) {}
                balance -= money;
            }
        }
    }   // withdraw
}

class RunnableEx21 implements Runnable {
    Account acc = new Account();

    @Override
    public void run() {
        while(acc.getBalance() > 0) {
            // 100, 200, 300 중 한 값을 임의로 선택해서 출금
            int money = (int)(Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance: " + acc.getBalance());
        }
    } // run()    
}   
