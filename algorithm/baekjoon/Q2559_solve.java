import java.util.Scanner;

public class Q2559_solve {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      int N = in.nextInt();
      int K = in.nextInt();
      int temperatureArr[] = new int[N];

      for(int i=0; i<N; i++) {
         temperatureArr[i] = in.nextInt();
      }

      int biggestTemperature = 0;

      // 가장 첫번째 ~ K번쨰까지의 합 구하기
      for(int i=0; i<K; i++) {
         biggestTemperature += temperatureArr[i];
      }

      // 슬라이딩 윈도우 전개
      int sum = biggestTemperature;
      for(int i=0, j=K; j<N; i++, j++) {
         sum += temperatureArr[j]-temperatureArr[i];
         if(sum > biggestTemperature) biggestTemperature = sum;
      }
      System.out.println(biggestTemperature);
   }
}
