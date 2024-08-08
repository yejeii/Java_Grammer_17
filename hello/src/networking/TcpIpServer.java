package networking;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer {

   public static void main(String[] args) {
      
      ServerSocket serverSocket = null;

      try {
         // 서버소켓을 생성해 7777번 포트와 결합(bind)
         serverSocket = new ServerSocket(7777);
         System.out.println(getTime() + "서버가 준비되었습니다.");   
      } catch (IOException e) {
         e.printStackTrace();
      }

      while (true) { 
          try {
            System.out.println(getTime() + "연결요청을 기다립니다..");

            // 요청대기시간을 5초로 설정
            // 5초동안 접속요청이 없으면 accept()에서 SocketTimeoutException 발생
            serverSocket.setSoTimeout(5*1000);

            // 서버소켓은 클라이언트의 연결요청이 올 때까지 실행을 멈추고 계속 기다린다.
            // 클라이언트의 연결요청이 오면 클라이언트 소켓과 통신할 새로운 소켓을 생성한다.
            Socket socket = serverSocket.accept();
            System.out.println(getTime() + socket.getInetAddress() + "로부터 연결요청이 들어왔습니다.");
            System.out.println("getPort() : " + socket.getPort());            // 클라이언트 소켓(원격소켓)이 사용하는 포트
            System.out.println("getLocalPort() : " + socket.getLocalPort());  // 소켓 자신이 사용하는 포트

            // 소켓의 출력스트림을 얻는다.
            OutputStream out = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);

            // 클라이언트 프로그램 소켓(원격소켓, remote socket)에 데이터를 보낸다.
            dos.writeUTF("[Notice] Test Message1 from Server");
            System.out.println(getTime() + "데이터를 전송했습니다.");

            // 스트림과 소켓을 닫는다
            dos.close();
            socket.close();
         } catch (SocketTimeoutException e) {
            System.out.println("지정된 시간동안 접속이 없어 서버를 종료합니다.");
            System.exit(0);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   // 현재시간을 문자열로 반환하는 함수
   private static String getTime() {
      SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
      return f.format(new Date());
   }
}
