
import java.util.Scanner;

public class ScannerTest {
   public static void main(String[] args) {
      /** Scanner next(), nextInt(), nextLine()
       * next() : 개행문자(\n), 띄어쓰기를 무시하고 입력 받음. 즉, 문자를 치고 엔터나 스페이스를 누를경우 그 이전까지만 입력 받음.
       * nextInt() : 마찬가지로 개행문자, 띄어쓰기 전까지의 정수만 입력 받음.
       * nextLine() : 한 줄 단위로 입력 받음. 즉, 개행문자(엔터)가 한 줄의 기준이 됨!
       */
      Scanner in = new Scanner(System.in);
      String name = in.next();
      in.nextLine();
      String address = in.nextLine();
      int age = in.nextInt();
      int child = in.nextInt();

      System.out.println("이름 : " + name);
      System.out.println("주소 : " + address);
      System.out.println("나이 : " + age);
      System.out.println("자녀 수 : " + child);
   }
}
