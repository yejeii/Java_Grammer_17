package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class NetworkEx1 {

    public static void main(String[] args) {
        InetAddress ip = null;
        InetAddress[] ipArr = null;

        try {
            ip = InetAddress.getByName("www.naver.com");
            System.out.println("getHostName() : " + ip.getHostName());
            System.out.println("getHostAddress() : " + ip.getHostAddress());    // 호스트의 IP 주소
            System.out.println("toString() : " + ip.toString());    // hostname / literal IP

            byte[] ipAddr = ip.getAddress();    // IP 주소를 byte 배열로 반환
            System.out.println("getAddress() : " + Arrays.toString(ipAddr));

            String result = "";
            for (int i = 0; i < ipAddr.length; i++) {
                result += (ipAddr[i] < 0) ? ipAddr[i] + 256 : ipAddr[i];
                result += ".";
            }
            System.out.println("getAddress()+256 : " + result);
            System.out.println();
        } catch (UnknownHostException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {
            ip = InetAddress.getLocalHost();    // 지역 호스트의 IP 주소 반환
            System.out.println("getHostName() : " + ip.getHostName());  // ga29
            System.out.println("getHostAddress() : " + ip.getHostAddress());    // 호스의 IP 주소 반환
            System.out.println();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            ipArr = InetAddress.getAllByName("www.naver.com");  // 도메인명(호스트)에 지정된 모든 호스트의 IP 주소 배열에 담아 반환

            for (int i = 0; i < ipArr.length; i++) {
                System.out.println("ipArr[" + i + "] : " + ipArr[i]);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
