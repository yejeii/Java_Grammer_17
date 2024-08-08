package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/** 멀티채팅서버 프로그램
 * 
 * 서버에 접속한 클라이언트를 HashMap 에 저장해서 관리하고 있음
 * 클라이언트가 멀티채팅서버에 접속하면 HashMap 에 저장되고 접속을 해제하면 HashMap 에서 제거함
 * 클라이언트가 데이터를 입력하면, 멀티채팅서버는 HashMap에 저장된 모든 클라이언트에게 데이터를 전송함
 * 
 * 멀티채팅서버의 ServerReceiver 스레드는 클라이언트가 추가될 때마다 생성되며
 * 클라이언트의 입력을 서버에 접속된 모든 클라이언트에게 전송하는 일을 수행
 * 
 * 
 */
public class TcpIpMultichatServer {

   HashMap clients;

   public TcpIpMultichatServer() {
      clients = new HashMap<>();
      Collections.synchronizedMap(clients);
   }

   public void start() {
      ServerSocket serverSocket = null;
      Socket socket = null;

      try {
         serverSocket = new ServerSocket(7777);
         System.out.println("서버가 시작되었습니다.");

         while (true) { 
            socket = serverSocket.accept();
            System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속하였습니다.");
            ServerReceiver thread = new ServerReceiver(socket);
            thread.start();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }  // start

   void sendToAll(String msg) {
      Iterator it = clients.keySet().iterator();

      while (it.hasNext()) {
         try {
            DataOutputStream out = (DataOutputStream) clients.get(it.next());
            out.writeUTF(msg);
         } catch (Exception e) {
            // TODO: handle exception
         }          
      } // while
   } // sendToAll

   public static void main(String[] args) {
      new TcpIpMultichatServer().start();
   }

   class ServerReceiver extends Thread {
      Socket socket;
      DataInputStream in;
      DataOutputStream out;

      ServerReceiver(Socket socket) {
         this.socket = socket;
         try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
         } catch (Exception e) {
         }
      }

      // 클라이언트가 새로 추가될 때 클라이언트 이름을 key 로 하여 클라이언트의 출력스트림을 HashMap 인 clients 에 저장하여
      // 다른 클라이언트가 입력한 데이터를 전송하는데 사용하고 있음
      // 만약 클라이언트가 종료되어 클라이언트의 입력스트림이 null 이 되면 while 문을 빠져나가서 
      // clients 의 목록에서 해당 클라이언트를 제거함
      public void run() {
         String name = "";

         try {
            name = in.readUTF();
            sendToAll("#"+name+"님이 들어오셨습니다.");

            clients.put(name, out);
            System.out.println("현재 서버접속자 수는 " + clients.size() + "입니다.");

            while(in != null) 
               sendToAll(in.readUTF());
         } catch (IOException e) {
         } finally {
            sendToAll("#" + name + "님이 나가셨습니다.");
            clients.remove(name);
            System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속을 종료하였습니다.");
            System.out.println("현재 서버접속사 수는 " + clients.size() + "입니다.");
         }  // try
      } // run
   } // ReceiverThread
} // class
