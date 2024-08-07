package thread;

/** 데몬 쓰레드
 * 
 * 다른 일반 쓰레드(Not Demon)의 작업을 돕는 보조적인 역할을 수행하는 쓰레드
 * -> 일반 쓰레드가 모두 종료되면 데몬 쓰레드는 강제적으로 자동 종료됨
 * 
 * 데몬 쓰레드 ex. 워드프로세서의 자동저장, 화면자동갱신, 가비지 컬렉터 등
 * 
 * 데몬 쓰레드는 무한루프와 조건문을 이용해 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 작성함
 * 일반 쓰레드와 작성방법과 실행방법 동일. 다만, 쓰레드를 생성 후 실행 전 setDaemon(true) 호출
 * 
 * 데몬 쓰레드가 생성한 쓰레드는 자동으로 데몬 쓰레드가 됨
 */
public class ThreadEx10 implements Runnable{

    static boolean autoSave = false;

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadEx10());
        t.setDaemon(true);  // 이 부분이 없으면 종료되지 않음
        t.start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
           } catch(InterruptedException e) {}
           System.out.println(i);

           if(i==5) autoSave = true;
        }

        System.out.println("프로그램 종료");
    }

    @Override
    public void run() {
        while (true) { 
            try {
                Thread.sleep(3 * 1000); // 3초
            } catch(InterruptedException e) {} 
            
            if(autoSave) autoSave();
        }

    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }

}
