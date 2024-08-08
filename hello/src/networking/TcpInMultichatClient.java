package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class TcpInMultichatClient {

   public static void main(String[] args) {
      if(args.length != 1) {
         System.out.println("USAGE: java TcpIpMultichatClient 대화명");
         System.exit(0);
      }

      try {
         String serverTp = "127.0.0.1";

         // 소켓을 생성해 연결 요청
         Socket socket = new Socket(serverTp, 7777);

         System.out.println("서버에 연결되었습니다.");
         Thread sender = new Thread(new ClientSender(socket, args[0]));
         Thread receiver = new Thread(new ClientReceiver(socket));

         sender.start();
         receiver.start();
      } catch (ConnectException e) {
         e.printStackTrace();
      } catch (Exception ee) {
         ee.printStackTrace();
      }
   }

   static class ClientSender implements Runnable {

      Socket socket;
      DataOutputStream out;
      String name;

      ClientSender(Socket socket, String name) {
         this.socket = socket;
         try {
            out = new DataOutputStream(socket.getOutputStream());
            this.name = name;
         } catch (Exception e) {
            // TODO: handle exception
         }
      }

      public void run() {
         Scanner scanner = new Scanner(System.in);
         try {
            if(out!=null) {
               out.writeUTF(name);
            }

            while(out!=null) {
               out.writeUTF("["+name+"]"+scanner.nextLine());
            }
         } catch (IOException e) {
            // TODO: handle exception
         }
      } // run
   } // ClientSender

   static class ClientReceiver implements Runnable {

      Socket socket;
      DataInputStream in;

      public ClientReceiver(Socket socket) {
         this.socket = socket;
         try {
            in = new DataInputStream(socket.getInputStream());
         } catch (IOException e) {
         }
      }

      @Override
      public void run() {
         while(in!=null) {
            try {
               System.out.println(in.readUTF());
            } catch (IOException e) {
            }
         }
      } // run
   } // ClientReceiver
}
