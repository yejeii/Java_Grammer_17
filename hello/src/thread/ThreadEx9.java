package thread;

/** 쓰레드 그룹
 * 
 * 서로 관련된 쓰레드를 그룹으로 다루기 위한 것
 * 폴더에 관련 파일을 넣어서 관리하는 것처럼 쓰레드 그룹을 생성해 쓰레드를 그룹으로 묶어서 관리할 수 있다.
 * 
 * 쓰레드 그룹은 보안상의 이유로 도입된 개념으로, 자신이 속한 쓰레드 그룹이나 하위 쓰레드 그룹은 변경할 수 있지만,
 * 다른 쓰레드 그룹의 쓰레드를 변경할 수는 없다.
 * 
 * 모든 쓰레드는 반드시 쓰레드 그룹에 포함되어 있어야 하므로, 쓰레드 그룹을 지정하는 생성자를 사용하지 않은 쓰레드는 기본적으로
 * 자신을 생성한 쓰레드와 같은 쓰레드 그룹에 속하게 된다.
 * 
 * 자바 어플리케이션이 실행되면, JVM 은 main 과 system 이라는 쓰레드 그룹을 만들고,
 * JVM 운영에 필요한 쓰레드들을 생성해 이 쓰레드 그룹에 포함시킨다.
 * Ex. main() 을 수행하는 main 이라는 쓰레드는 main 쓰레드 그룹에 속한다
 *     가비지컬렉션을 수행하는 Finalizer 쓰레드는 system 쓰레드 그룹에 속한다.
 *     우리가 생성하는 모든 쓰레드 그룹은 main 쓰레드 그룹의 하위 쓰레드 그룹이 된다.
 *     쓰레드 그룹을 지정하지 않고 생성한 쓰레드 역시 자동으로 main 쓰레드 그룹에 속한다.
 */
public class ThreadEx9 {

    public static void main(String[] args) {
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        ThreadGroup grp1 = new ThreadGroup("Group1");
        ThreadGroup grp2 = new ThreadGroup("Group2");

        ThreadGroup subGrp1 = new ThreadGroup(grp1, "SubGroup1");

        grp1.setMaxPriority(3); // grp1 의 최대우선순위 : 3

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        };

        new Thread(grp1, r, "th1").start();
        new Thread(subGrp1, r, "th2").start();
        new Thread(grp2, r, "th3").start();

        System.out.println(">> List of ThreadGroup : " + main.getName() 
                    + ", Active ThreadGroup: " + main.activeGroupCount()
                    + ", Active Thread: " + main.activeCount());
        
        main.list();
    }
}
