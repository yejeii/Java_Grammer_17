package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 서버로부터 서버시간을 전송받아 출력하는 간단한 UDP 소켓 클라이언트와 서버 프로그램
 * 
 * 클라가 DatagramPacket 을 생성해 DatagramSocket 으로 서버에 전송하면,
 * 서버는 전송받은 DatagramPacket 의 getAddress(), getPort() 를 호출해서 클라의 정보를 얻어
 * 서버시간을 DatagramPacket 에 담아서 전송함
 */
public class UdpServer {

   public void start() throws IOException {
      // 포트 7777번을 사용하는 소켓 생성
      DatagramSocket socket = new DatagramSocket(7777);
      DatagramPacket inPacket, outPacket;

      byte[] inMsg = new byte[10];
      byte[] outMsg;

      while (true) { 
         // 데이터를 수신하기 위한 패킷을 생성
         inPacket = new DatagramPacket(inMsg, inMsg.length);

         // 패킷을 통해 데이터를 수신(receive)
         socket.receive(inPacket);

         // 수신한 패킷으로부터 client의 IP 주소와 Port 를 얻는다
         InetAddress address = inPacket.getAddress();
         int port = inPacket.getPort();

         // 서버의 현재시간을 시분초 형태([hh:mm:ss])로 반환
         SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
         String time = f.format(new Date());
         outMsg = time.getBytes();  // time 을 byte 배열로 변환

         // 패킷을 생성해서 client 에 전송(send)
         outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
         socket.send(outPacket);
      }
   } // start()

   public static void main(String[] args) {
      try {
         // UDP 서버를 실행시킨다
         new UdpServer().start();
      } catch (IOException e) {
         // TODO: handle exception
      }
   }
}
