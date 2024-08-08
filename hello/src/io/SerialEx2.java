package io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/** SerialEx1 역직렬화
 * 
 * 객체를 역직렬화할 때는 직렬화할 때의 순서와 일치해야 함
 * -> 객체를 개별적으로 직렬화하는 것보단 컬렉션에 저장해서 직렬화하는 것이 좋음
 */
public class SerialEx2 {

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();

            String fileName = "UserInfo.ser";
            
            FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);

            ObjectInputStream in = new ObjectInputStream(bis);

            // 객체를 읽을 때는 출력한 순서와 일치하게
            ArrayList<UserInfo2> list = (ArrayList) in.readObject();

            long endTime = System.currentTimeMillis();
            System.out.printf("역직렬화 수행시간 : %d %n", endTime-startTime);
            System.out.println(list);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
