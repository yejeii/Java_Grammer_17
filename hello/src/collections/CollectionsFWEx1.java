package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 컬렉션 프레임워크(Collection Framework)
 * 
 * 데이터 집합을 저장하는 클래스들을 표준화한 설계 : 표준화된 개발을 위함
 * 
 * 컬렉션 : 데이터 집합, 그룹
 * 프레임워크 : 표준화된 프로그래밍 방식
 * 
 * 핵심 인터페이스
 * - Collection : List, Set의 기반
 * 				  컬렉션 클래스에 저장된 데이터를 읽고, 추가, 삭제 하는 등 컬렉션(데이터 집합)을 다루는데 가장 기본적인 메서드 정의
 * 
 * - List : 순서가 있고, 중복을 허용하는 데이터 집합
 * 			단점> 크기가 고정됨 -> 저장 공간의 확장성이 없다. -> ArrayList 등장
 * 
 * 		ArrayList, LinkedList, Stack, Vector 등
 * 
 * 		LinkedList : 자료의 주소값으로 서로 연결되어 있는 구조
 * 			
 * 			class Node {
 * 				Node next;	// 다음 Node를 가르키는 정보 : 단방향 Linked List
 * 				Object obj;
 * 			//  Node prev; : 양방향 Linked List
 *			}
 *
 *		ArrayList와 LinkedList의 차이
 *			- 접근시간(읽기) : ArrayList가 월등히 빠름
 *			- 삭제 : LinkedList가 빠름
 *
 *		Stack 
 *			- LIFO
 *			- 단방향 구조(위 -> 밑)
 *		
 *		Queue
 *			- FIFO
 *			- 단방향 구조(위 -> 밑)
 *
 *		Deque(Double Ended Queue)
 *			- 양방향 구조(위 아래로 가능)
 *
 *		Iterator
 *			- 컬렉션 프레임워크에서 컬렉션에 저장된 데이터를 읽어오는 방법 표준화(인터페이스)
 *
 *			- 종류 : Iterator, Enumeration, ListIterator
 *			- 차이점 : Enumeration은 Interator의 구버전
 *					 ListIterator는 Iterator를 상속받아 기능을 추가적으로 구현
 *
 *					 따라서, Enumeration은 legacy 코드의 유지를 위해 사용되고 있음
 *					 신규 개발에서는 ListIterator or Iterator를 사용
 *			
 *			- 메서드 : hastNext(), next(), remove()
 *					 -> for문을 사용하지 않아도 탐색 가능
 *					 -> 표준화된 개발 가능. 재사용성. 코드의 일관성. 코드 품질
 *
 *		Comparator, Comparable
 *			- 컬렉션의 정렬과 관련된 기능
 *			- Comparable : 기본 정렬기준을 구현하는데 사용
 *				int compare(obj1, obj2) : 두 객체를 비교
 *	
 *			- Comparator : 기본 정렬기준 외에 다른 기준으로 정렬하고자 할 때 사용
 *				int compareTo(obj) : 주어진 객체를 자신과 비교
 *			
 * 
 * - Set : 순서 유지 X, 중복 허용 X
 * 		HashSet, TreeSet 등
 * 
 * 		HashSet : 저장 순서를 유지하려고 하면, LinkedHashSet을 사용
 * 
 * 		TreeSet : 이진 검색 트리
 * 				  정렬, 검색, 범위 검색에 높은 성능을 보임
 * 				  중복된 데이터의 저장 허용 X
 * 				  각 노드의 최대 2개의 자식 노드를 연결할 수 있음
 * 				  Root라고 하는 하나의 노드로부터 시작해서 확장됨
 * 				  노드 간의 관계(부모-자식)
 * 
 * 				  class TreeNode {
 * 					TreeNode left;
 * 					Object element;
 * 					TreeNode right;
 * 				  }
 * 
 * 				  단점 : 노드의 추가 및 삭제에 시간 소요..
 * 
 * 
 * - Map : key-value 상으로 이루어진 데이터 집합
 * 		   순서 유지 X, 키 중복 X, 값 중복 O
 * 		HashMap, TreeMap, HashTable, Properties 등
 * 		
 * 		HashMap :
 * 			데이터 저장 형태가 선형구조로 저장
 * 			데이터 저장 시, 키(Key) 값으로 해시함수를 실행한 결과를 통해 저장위치 결정
 * 			-> 특정 데이터의 저장위치를 해시함수를 통해 바로 알 수 있기 때문에 데이터의 추가, 삭제, 특히 검색이 빠르다는 장점
 * 			탐색방법 : 순차 탐색 구조 -> TeepMap 에 비해 검색 성능 low
 * 
 * 		TreeMap : 
 * 			데이터 저장 형태가 이진구조(왼쪽, 오른쪽)로 저장
 * 			이진 탐색으로 진행 -> 검색 성능 high
 * 
 * 		ㅊ : key, value가 String
 * 					 Iterator가 Enumeration임
 * 
 * 		
 * 
 */
public class CollectionsFWEx1 {
	public static void main(String[] args) {
		
		/* ---------- ArrayList ------------ */
		ArrayList list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(5);
		list1.add(4);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		
		System.out.println("list1: " + list1);
		
		// 정렬
		Collections.sort(list1);
		System.out.println(list1);
		
		// 다른 데이터 유형 저장
		list1.add(3, "A");
		System.out.println("list1: " + list1);
		
		// 섞기
		Collections.shuffle(list1);
		System.out.println("list1: " + list1);
		
		
		/* LinkedList 와 ArrayList 탐색 시간 비교 
		 * ArrayList가 월등히 빠름 월.등.히 
		 * 
		 * LinkedList : 데이터가 노드 구조로 연결됨 
		 * 				-> 다음 데이터 탐색 시 노드에 저장된 주소를 참조해서 이동
		 * ArrayList : 데이터 구조의 시작과 끝 사이가 모두 데이터인 연결된 데이터 구조를 가짐 
		 * 				-> 다음 데이터 탐색시 참조해서 이동과 같은 탐색 작업이 없음  
		 * 			   배열의 단점(저장 공간 확장성 X)을 개선한 배열구조이므로 저장 공간의 확장이 자유로움
		 * 			   확장성이라는 특징 때문에 기존 배열에 공간이 없으면 새로운 배열 생성 후 기존 배열의 데이터를 복사함
		 * 
		 * 			   >고려사항<
		 * 			   저장된 대상의 데이터가 적으면 OK
		 * 			   대량의 데이터의 경우 ArrayList 객체를 사용할 것인지 고려해야 함
		 * */
		LinkedList ll = new LinkedList<>();
		ArrayList al = new ArrayList(100000);
		
		add(ll);
		add(al);
		
//		System.out.println("Linkedlist 조회 시간 : " + access(ll));
//		System.out.println("ArrayList 조회 시간 : " + access(al));
		
//		System.out.println("Linkedlist 삭제 시간 : " + delete(ll));
//		System.out.println("ArrayList 삭제 시간 : " + delete(al));
		
		/* ---------- Iterator 사용 ----------- */
		
		ArrayList al2 = new ArrayList<>();
		al2.add("1");
		al2.add("2");
		al2.add("3");
		al2.add("4");
		
		Iterator it = al2.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
		}
		
		System.out.println();
		
		/* ---------- Iterator을 개선한 ListIterator 사용 ---------- */
		
		// 양방향 탐색이 가능 
		ListIterator lit = al2.listIterator();
		
		// 순방향 탐색
		while(lit.hasNext()) {
			System.out.println(lit.next());
		}

		System.out.println();
		
		// 역방향 탐색
		while(lit.hasPrevious()) {
			System.out.println(lit.previous());
		}
		
		System.out.println();
		
		/* ----------- Arrays 사용 -------------- */
		
		// 다차원 배열의 비교와 출력
		int[] arr = {0,1,2,3,4};
		int[][] arr2D = {{11, 12}, {21, 22}};
		
		System.out.println("Arrays.toString() : " + Arrays.toString(arr));
		System.out.println("Arrays.deepToString() : " + Arrays.deepToString(arr2D));
		
		// 비교
		String str1 = "String";
		String str2 = "String";
		String str3 = new String("String");
		
		String[][] str2D = new String[][] {{"aaa", "bbb"}, {"AAA", "BBB"}};
		String[][] str2D2 = new String[][] {{"aaa", "bbb"}, {"AAA", "BBB"}};
		
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println("str1.equals(str3) : " + str1.equals(str3));
		
		System.out.println("Arrays.equals : " + Arrays.equals(str2D, str2D2));
		System.out.println("Arrays.deepEquals : " + Arrays.deepEquals(str2D, str2D2));
		
		/**
		 * String.compareTo() 를 통한 문자열 비교
		 * compareTo() : 비교대상 문자열의 각 문자들을 첫번째자리부터 하나씩 비교하다가
		 * 가장 먼저 만나는 상이한 문자들의 ASCII코드 값 차이를 반환하고 끝낸다.
		 * 이후 문자열을 비교하지 않는다.
		 * */
		System.out.println("String.compareTo() 를 통한 문자열 비교");
		System.out.println("1. 숫자 형태의 문자열 비교");
		String numStr1 = "2568";
		String numStr2 = "856";
		System.out.println(numStr1.compareTo(numStr2));	
		// "2568"의 첫번째 문자("2")와 "856"의 첫번째 문자("8")가 상이하므로
		// 이들의 ASCII코드 차이를 반환(~568, ~56 비교 X).
		
		System.out.println("2. 문자 형태의 문자열 비교");
		String alphaStr1 = "Tied";
		String alphaStr2 = "Tiger";
		System.out.println(alphaStr1.compareTo(alphaStr2));	// 'e'와 'g'의 아스키코드 숫자 차이를 반환환

		
		/* ----------- Comparator, Comparable 사용 ----------- */

		// 기본형 타입
		int i = 10;
		
		// 참조형 타입 
		// Wrapper 클래스 : 기본형 int를 객체로 관리하고 싶은 경우 제공되는 클래스
		Integer ii = new Integer(i);

		ii.compareTo(ii);
		
		// 정렬
		String[] strArr = {"cat", "Dog", "lion", "tiger"};
		
		// 순방향 정렬
		Arrays.sort(strArr);
		System.out.println("strArr 순방향 정렬 : " + Arrays.toString(strArr));
		
		// 역방향 정렬
		// 1. Comparator 인터페이스 구현 클래스 작성
		//    override되는 메서드(compare)를 역방향으로 수정.
		// 2. Arrays에서 제공되는 sort() 중 Comparator 타입의 매개변수를 받는 메서드 선택
		// 3. 구현된 Comparator 클래스르 이용해서 sort() 메소드의 매개변수로 적용해 사용
		Arrays.sort(strArr, new DecendingComparator());
		System.out.println("strArr 역방향 정렬 : " + Arrays.deepToString(strArr));
		
		System.out.println();	
		
		/* ------------------ HashSet ------------------ */
		
		Object[] objArr = {"1", new Integer(1), "2", "2", "3", "3","4" };
		Set set  = new HashSet<>();
		
		for (int j = 0; j < objArr.length; j++) {
			set.add(objArr[j]);
		}
		
		// 중복된 값 저장 X
		System.out.println("HashSet : " + set);	
		System.out.println();
		
		/* ------------------ TreeSet ------------------ */
		
		Set treeSet = new TreeSet<>();
		
		for (int j = 0; treeSet.size() < 6; j++) {
			int num = (int)(Math.random() * 45) + 1;
			treeSet.add(num);
		}
		
		System.out.println("TreeSet : " + treeSet);
		System.out.println();
		
		/* ------------------ HashMap ------------------ */
		
		HashMap hmap = new HashMap<>();
		hmap.put("myId", "1234");
		hmap.put("asdf", "1111");
		
		String id = "myId";
		String pw = "1234";
		
		if(!hmap.containsKey(id)) {
			System.out.println("ID 또는 PW가 잘못되었습니다.");
			return;
		} else {
			if(hmap.get(id).equals(pw)) {
				System.out.println("사용자님 환영합니다.");
			} else {
				System.out.println("ID 또는 PW가 잘못되었습니다.");
			}
		} 
		
		System.out.println();
		
		
		
		/* ------------------ TreeMap ------------------ */		
		
		String[] data = {"A", "K", "A", "K", "D", "K", "A", "Z", "K", "K", "Z", "D"};
		
		TreeMap tMap = new TreeMap<>();
		
		/* 결과(문자열 빈도수 결과)
		 * A : 3
		 * K : 5
		 * D : 2
		 * Z : 2
		 */
		for (int j = 0; j < data.length; j++) {
			if(tMap.containsKey(data[j])) {
				// 배열의 문자가 Map에 key로 존재하는 경우, 빈도수 증가
				Integer val = (Integer) tMap.get(data[j]);
				tMap.put(data[j], val.intValue() + 1);	// If the map previously contained a mapping for the key, the old value is replaced.
			} else {
				// tMap의 키로 등록
				tMap.put(data[j], new Integer(1));	
			}
		}

		// Iterator 를 활용해서 출력
		Iterator it3 = tMap.entrySet().iterator();
		
		while(it3.hasNext()) {
			Map.Entry<String, Integer> entry = (Entry<String, Integer>) it3.next();
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		/* 빈도수를 내림차순으로 정렬해서 출력
		 * 1. Collections.sort() 
		 *    sort()의 기본 정렬은 오름차순 
		 * 2. Comparator 인터페이스 구현 (오름차순의 반대)
		 * 3. Collections.sort()의 매개변수로 구현한 Comparator 인터페이스 대입
		 * 
		 * 4. sort()의 첫번째 매개변수는 List 타입..
		 *    -> Map을 List화해야 함
		 *    List 의 구현체인 ArrayList 가 되도록 함. => ArrayList 의 복사 생성자.
		 *    
		 *    List : 인터페이스
		 *    -> 복사생성자 활용! -> 구현체가 있어야 복사 생성자 활용 가능
		 *    ArrayList 클래스에 복사 생성자가 있는지 확인
		 *    
		 *    ArrayList(Collection<? extends E> c)
		 *    
		 *    복사생성자의 매개변수가 Collection 임. 바로 사용 못함.
		 *    Collection - List - ArrayList
		 *    					- Set
		 *    
		 *    Map -> Collection(list or set) -> list
		 *    Map 클래스의 entrySet() : Set 리턴
		 */ 
		
		List<Set<Map<String, Integer>>> liMap = new ArrayList<>(tMap.entrySet());
		
		Collections.sort(liMap, new DecendingComparator2());
		
		Iterator li4 = liMap.iterator();
		while(li4.hasNext()) {
			System.out.println(li4.next());
		}
		
		System.out.println();
		
		/* ---------------------- Properties -------------------- */
		
		Properties prop = new Properties();
		
		prop.setProperty("size", "10");
		prop.setProperty("capacity", "20");
		prop.setProperty("timeout", "30");
		
		Enumeration e = prop.propertyNames();
		
		while(e.hasMoreElements()) {
			String element = (String) e.nextElement();
			System.out.printf("%s - %s \n", element, prop.getProperty(element));
		}
		
		
		
	}
	
	/* List에 데이터 저장 */
	public static void add(List list) {
		for (int i = 0; i < 100000; i++) {
			list.add(i+"");
		}
	}
	
	/* List 탐색*/
	public static Long access(List list) {
		long start = System.currentTimeMillis();
		
		// 시작 노드에서 마지막 노드까지 이동하면서 탐색
		for(int i=0; i<100000; i++) list.get(i);
		
		long end = System.currentTimeMillis();
		
		// 탐색(조회) 시간
		return end-start;
	}
	
	/* List 삭제 */
	public static Long delete(List list) {
		long start = System.currentTimeMillis();
		
		list.removeAll(list);
		
		long end = System.currentTimeMillis();
		
		// 삭제 시간
		return end-start;
	}

}

/**
 * 역방향 정렬을 위한 클래스 
 * Comparator 인터페이스 구현 */
class DecendingComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		if ( o1 instanceof Comparable && o2 instanceof Comparable )	{
			Comparable c1 = (Comparable)o1;
			Comparable c2 = (Comparable)o2;
			
			return c1.compareTo(o2) * -1;
		}
		
		return -1;
	}
}

class DecendingComparator2 implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		
		/* Comparable의 compareTo를 이용할 것 
		 * compareTo() : 순방향으로 나오기 때문에 -1만 곱하면 decending이 될 것 */
		
		System.out.println(o1.getClass()); // TreeMap
		
		if(o1 instanceof Map.Entry && o2 instanceof Map.Entry) {
			Map.Entry<String, Integer> e1 = (Map.Entry<String, Integer>)o1;
			Map.Entry<String, Integer> e2 = (Map.Entry<String, Integer>)o2;
			
			Integer val1 = e1.getValue();
			Integer val2 = e2.getValue();
			
			return val1.compareTo(val2) * -1;
			
		}
		return -1;
	}
	
}


