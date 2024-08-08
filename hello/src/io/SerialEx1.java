package io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/** 객체 직렬화하여 파일에 저장
 * 
 * 버퍼스트림을 기반으로 하는 직렬화 스트림 생성한 후, 
 * writeObject() 사용하여 객체를 직렬화 스트림에 출력하면 파일에 객체가 직렬화되어 저장됨
 * 
 * 객체를 직렬화하는 것은 간단하지만, 객체에 정의된 모든 인스턴스 변수에 대한 참조를 찾아들어가기 때문에
 * 복잡하고 시간이 걸리는 작업이 될 수 있음
 * ArrayList 와 같은 객체를 직렬화하면 ArrayList 에 저장된 모든 객체들과 각 객체의 
 * 인스턴스 변수가 참조하고 있는 객체들까지 모두 직렬화됨
 * 
 */
public class SerialEx1 {

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();

            String fileName = "UserInfo.ser";
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            ObjectOutputStream out = new ObjectOutputStream(bos);

            ArrayList<UserInfo2> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                UserInfo2 u1 = new UserInfo2(new String("JavaMan")+i, "1234", (12+i)/2*2);
                list.add(u1);
            }
            
            // 객체 직렬화
            out.writeObject(list);
            out.close();

            long endTime = System.currentTimeMillis();
            System.out.println("직렬화가 끝났습니다.");
            System.out.printf("직렬화 수행시간(m) : %d", (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
