package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/** 바이트 배열 데이터 복사 
 * 
 * 문제..
 * read() 와 write(int b), 즉 한 번에 1byte 만 읽고 쓰기 때문에 효율적이지 못함
 * 
 * 해결
 * 배열 사용
*/
public class IOEx1 {

   public static void main(String[] args) {
      byte[] inSrc = {1,2,3,4,5,6,7,8,9};
      byte[] outSrc = null;

      ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
      ByteArrayOutputStream output = new ByteArrayOutputStream();

      int data = 0;

      while((data = input.read()) != -1) {
         output.write(data);
      }

      outSrc = output.toByteArray();   // 스트림의 내용을 byte 배열로 반환

      System.out.println("Input Source: " + Arrays.toString(inSrc));
      System.out.println("Output Source: " + Arrays.toString(outSrc));
   }
}
