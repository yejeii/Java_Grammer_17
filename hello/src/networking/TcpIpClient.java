package networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;

public class TcpIpClient {

   public static void main(String[] args) {
      try {
         String serverIp = "127.0.0.1";
         System.out.println("서버에 연결중입니다. 서버IP : " + serverIp);

         // 소켓 생성하여 연결 요청
         Socket socket = new Socket(serverIp, 7777);

         // 소켓의 입력스트림을 얻는다
         InputStream in = socket.getInputStream();
         DataInputStream din = new DataInputStream(in);

         // 소켓으로부터 받은 데이터 출력
         System.out.println("서버로부터 받은 메시지 : " + din.readUTF());
         System.out.println("연결을 종료합니다.");

         // 스트림과 소켓 닫는다
         din.close();
         socket.close();
         System.out.println("연결이 종료되었습니다.");
      } catch (ConnectException e) {
         e.printStackTrace();
      } catch (IOException ie) {
         ie.printStackTrace();
      } catch (Exception ee) {
         ee.printStackTrace();
      }
   }
}
