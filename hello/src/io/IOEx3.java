package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/** IOEx2 개선 - write(파라미터) 사용 
 * 
 * temp 에 저장된 모든 내용을 출력하는 대신 값을 읽어온 만큼만 출력
 * 즉, temp 의 길이와 read(temp)의 반환값은 다를 수 있다는 점
*/
public class IOEx3 {

   public static void main(String[] args) {
      byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
      byte[] outSrc = null;
      byte[] temp = new byte[4];

      ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
      ByteArrayOutputStream output = new ByteArrayOutputStream();

      System.out.println("Input Source: " + Arrays.toString(inSrc));
      
      while(input.available() > 0) {
         try {
            int len = input.read(temp);      // 읽어온 데이터를 배열 temp 에 담고 읽어온 데이터 개수 반환
            output.write(temp, 0, len);  // 배열 temp 에 저장된 내용 중 0번째부터 len 개만큼 읽어서 출력소스에 씀
            
            outSrc = output.toByteArray();
            PrintArrays(temp, outSrc);
         } catch (IOException e) {}
      }
      
   }

   static void PrintArrays(byte[] temp, byte[] outSrc) {
      System.out.println("temp : " + Arrays.toString(temp));
      System.out.println("OutSrc Source : " + Arrays.toString(outSrc));
   }
}
