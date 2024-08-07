package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/** IOEx1 개선 - 배열 사용
 * 
 * byte[] 배열을 사용해 한 번에 배열 크기만큼 읽고 쓸 수 있게 됨
 * 배열을 이용한 입출력은 작업 효율을 증가시키므로 가능하면 입출력 대상에 따라 알맞은 크기의 배열을 사용하는 것이 좋음
 * 
 * 문제..
 * 마지막에 읽은 배열의 9번째와 10번째 요소값이 8과 9만 출력되어야 하는데 temp 에 남아있던 6, 7 까지 출력함
 * -> 성능을 위해 temp 에 담긴 내용을 지우고 쓰는게 아닌, 기존 내용 위에 덮어 쓰기 때문
 * 
 * 해결
 * 읽어온 만큼만 출력하도록 해야함
 */
public class IOEx2 {
   
   public static void main(String[] args) {
      byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
      byte[] outSrc = null;
      byte[] temp = new byte[4];

      ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
      ByteArrayOutputStream output = new ByteArrayOutputStream();

      System.out.println("Input Source: " + Arrays.toString(inSrc));
      
      while(input.available() > 0) {
         try {
            input.read(temp);    // 읽어온 데이터를 배열 temp 에 담음
            output.write(temp);  // 배열 temp 에 저장된 내용을 읽어서 출력소스에 씀
            // System.out.println("temp : " + Arrays.toString(temp));
            
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
