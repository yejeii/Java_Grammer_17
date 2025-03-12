import java.util.Scanner;

public class Q1920_self {
   public static void main(String args[]) {

      Scanner in = new Scanner(System.in);
      
      int n = in.nextInt();
      int[] nIntArr = new int[n];
      for(int i=0; i<n; i++) {
         nIntArr[i] = in.nextInt();
      }
      
      int m = in.nextInt();
      int[] mIntArr = new int[m];
      for(int i=0; i<m; i++) {
         mIntArr[i] = in.nextInt();
      }

      for (int mm=0; mm<mIntArr.length; mm++) {
         int mValue = mIntArr[mm];
         for (int nn=0; nn<nIntArr.length; nn++) {
            int nValue = nIntArr[nn];
            if(mValue == nValue) {
               System.out.println("1");
               break;
            }
            if(mm==mIntArr.length-1) {
               System.out.println("0");
            }
         }
      }
   }
}