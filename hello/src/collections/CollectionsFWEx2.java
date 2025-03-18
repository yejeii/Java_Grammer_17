package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ==================== Collections class 활용 ====================
 * 
 * Collection(자료 묶음)을 쉽게 사용하기 위해 다양한 기능의 메서드가 정의되어 있는 클래스
 * 
 * - 현재의 Collection의 인스턴스들의 용도는 데이터 저장용
 * - 현재의 실행 환경은 싱글쓰레드
 *   프로세스 하나가 데이터 저장소를 전용으로 사용하고 있음 -> 문제 X
 * 
 *   프로세스 2개 이상 또는 멀티쓰레드가 같은 데이터 저장소를 사용하게 되면??
 *   -> JAVA에서 제공하는 동기화를 맞춘 자료구조(Collection)를 사용하면 됨
 *   
 * 
 */
public class CollectionsFWEx2 {

	public static void main(String[] args) {
		
		/*
		 * 동기화 Collection 메서드
		 * Returns a synchronized (thread-safe) list backed by the specified list
		 * 메서드 명 앞에 synchronized 로 prefix가 시작되는 메서드 사용
		 * 개발자는 멀티 쓰레드 환경, 다중 프로세스 환경인 경우가 언제인지만 알면 됨.
		 * 
		 * 멀티 쓰레드 환경 : 채팅 서버( 멀티 쓰레드 대상 : Socket )
		 * 						( Collections.synchronizedList의 관리 대상 : 주고 받는 메시지 )
		 * 		TCP/IP, Socket(IP, PORT), 
		 */
//		Collections.synchronizedList(null);
		
		
		List list = new ArrayList<>();
		System.out.println(list);
		
		// Collections 메서드 사용
		Collections.addAll(list, 1,2,3,4,5);
		System.out.println(list);
		
		Collections.rotate(list, 2);	// 오른쪽으로 2칸 이동
		System.out.println(list);
		
		Collections.swap(list, 0, 2);	// 해당 위치의 값 교환
		System.out.println(list);
		
		Collections.shuffle(list);	// 섞기
		System.out.println(list);
		
		Collections.sort(list);		// 정렬
		System.out.println(list);
		
//		Collections.reverse(list);	// 역정렬
		System.out.println(list);
		
		Collections.sort(list, Collections.reverseOrder());	// reverse(list)와 동일
		System.out.println(list);
		
		int idx = Collections.binarySearch(list, 3);	// 해당 데이터의 위치값
		System.out.println("index : " + idx);
		
		List newList = Collections.nCopies(list.size(), 2);	// 새로운 배열 생성 후 모두 2로 초기화
		System.out.println(newList);
		System.out.println(newList.getClass());
		
		System.out.println("max : " + Collections.max(list));
		System.out.println("min : " + Collections.min(list));
		
//		Collections.fill(newList, 9); 	
		/* 
		 * Error : java.lang.UnsupportedOperationException
		 * 해당 메서드가 구현이 안되었거나 메서드가 호출되지 않아서 터진 예외. 
		 * -> Collections 클래스에서 AbstractList 추상 클래스의 set 메서드를 구현 안해서 생긴 문제
		 * 
		 * 해결>
		 * 메서드를 직접 구현하거나 다른 메서드로 대체할 수 있는지 확인
		 */
		
//		Collections.fill(list, 9); 	// 요소를 모두 9로 변경
//		System.out.println(list);
		
		Collections.replaceAll(list, 2, 1);
		System.out.println(list);
		
		
		
	}
}
