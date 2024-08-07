package thread;

import java.util.ArrayList;

public class ThreadWaitEx1 {
    public static void main(String[] args) throws Exception {
        Table2 table = new Table2();  // 여러 스레드가 공유하는 객체

        new Thread(new Cook2(table), "COOK1").start();
        new Thread(new Customer2(table, "donut"), "CUST1").start();
        new Thread(new Customer2(table, "burger"), "CUST2").start();

        Thread.sleep(5000);  // 5초 후 강제 종료
        System.exit(0); // 프로그램 전체 종료(모든 스레드 종료)
    }
}

class Customer implements Runnable {

    private Table2 table;
    private String food;

    
    public Customer(Table2 table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while (true) { 
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            String name = Thread.currentThread().getName();

            if(eatFood()) 
                System.out.println(name + " ate a " + food);
            else 
                System.out.println(name + " failed to eat. :(");
        } // while
    }

    boolean eatFood() { return table.remove(food); }
}

class Cook implements Runnable {

    private Table2 table;

    public Cook(Table2 table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) { 
            // 임의의 요리를 하나 선택해서 table 에 추가
            int idx = (int)(Math.random()* table.dishNum());
            table.add(table.dishNames[idx]);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }   // while
    }
}

class Table {
    String[] dishNames = { "donut", "donut", "bugger"};
    final int MAX_FOOD = 6;

    private ArrayList<String> dishes = new ArrayList<>();

    public void add(String dish) {
        // 테이블에 음식이 가득찼으면, 테이블에 음식을 추가X
        if(dishes.size() >= MAX_FOOD)
            return;
        dishes.add(dish);
        System.out.println("Dishes: " + dishes.toString());
    }

    public boolean remove(String dishName) {

        // 지정된 요리와 일치하는 요소를 테이블에서 제거
        for (int i = 0; i < dishes.size(); i++) {
            if(dishName.equals(dishes.get(i))) {
                dishes.remove(i);
                return true;
            }
        }
        return false;
    }

    public int dishNum() { return dishNames.length; }
}
