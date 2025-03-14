import java.util.Arrays;
import java.util.Scanner;

public class Q1920_googling {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      StringBuilder sb = new StringBuilder();

      int n = in.nextInt();
      int[] nIntArr = new int[n];
      for (int i = 0; i < nIntArr.length; i++) {
         nIntArr[i] = in.nextInt();
      }
      Arrays.sort(nIntArr);

      int m = in.nextInt();
      int[] mIntArr = new int[m];
      for (int i = 0; i < mIntArr.length; i++) {
         if(binarySearch(nIntArr, in.nextInt()) >= 0) {
            sb.append("1 \n");
         }
         else {
            sb.append("0 \n");
         }
      }

      System.out.println(sb);
   }

   public static int binarySearch(int[] arr, int target) {
      int lowestIndex = 0;
      int highestIndex = arr.length - 1;

      while(lowestIndex <= highestIndex) {
         int mid = (lowestIndex + highestIndex) / 2;
         if(target < arr[mid]) {
            highestIndex = mid - 1;
         }
         else if(target > arr[mid]) {
            lowestIndex = mid + 1;
         }
         else {
            return mid;
         }
      }
      return -1;
   }
}