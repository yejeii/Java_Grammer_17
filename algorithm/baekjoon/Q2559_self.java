import java.util.Scanner;

public class Q2559_self {
   public static void main(String args[]) {
      Scanner in = new Scanner(System.in);
      int N = in.nextInt();
      int K = in.nextInt();
      int latestStartIndex = N-K;
      int temperatureArr[] = new int[N];
      int biggestTemperature = 0;

      for(int i=0; i<N; i++) {
         temperatureArr[i] = in.nextInt();
      }

      for(int i=0; i<=latestStartIndex; i++) {
         int sum = 0;
         for(int j=i; j<K+i; j++) {
            sum += temperatureArr[j];
         }
         if(sum > biggestTemperature) biggestTemperature = sum;
      }

      System.out.println(biggestTemperature);

   }
}
