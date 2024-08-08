package io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SuperUserInfo {
    String name;
    String pw;
    
    public SuperUserInfo() {
        this("Unknown", "1111");
    }

    public SuperUserInfo(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }
    
}

/** 직렬화되지 않은 조상으로부터 상속받은 인스턴스 변수에 대한 직렬화 구현
 * 
 * writeObject(), readObject() 를 추가해 조상으로부터 상속받은 name, pw 가 직접 직렬화되도록 함
 * 해당 메서드들은 직렬화 / 역직렬화 작업시 자동으로 호출됨
 */
public class UserInfo2 extends SuperUserInfo implements Serializable{

    int age;

    public UserInfo2() {
        this("Unknown","1111",0);
    }

    public UserInfo2(String name, String pw, int age) {
        super(name, pw);
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo2 [name=" + name + ", pw=" + pw + ", age=" + age + "]";
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.writeUTF(name);
        out.writeUTF(pw);
        out.defaultWriteObject();   // 자동 직렬화
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        pw = in.readUTF();
        in.defaultReadObject(); // 자동 역직렬화
    }
    

    
}
