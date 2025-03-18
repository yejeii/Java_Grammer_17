
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapIterate {
   public static void main(String args[]) {
      /** HashMap 순회 방법 
		 * 1. EntrySet + Iterator
		 * 2. Map.keySet()
		 * */
		HashMap scoreMap = new HashMap<>();
		scoreMap.put("김자바", 90);	// 90 -> Integer(기본형을 객체로 포장)
								         // Integer 도 클래스이므로 Object 상속받음
		scoreMap.put("이자바", 57);	
		scoreMap.put("박자바", 27);	
		scoreMap.put("최자바", 85);	
		scoreMap.put("정자바", 98);	
		scoreMap.put("한자바", 100);

		/* 1. Iterator 사용 : EntrySet에서 Iterator 
       * Entry 에 key, value 가 다 있으니, key 와 value 모두 필요할 땐 EntrySet 사용할 것 */
		Iterator it = scoreMap.entrySet().iterator();
		while (it.hasNext()) {
			// Entry : Map에서 key, value를 함께 사용하기 위한 타입
			Map.Entry<String, Integer> entry = (Entry<String, Integer>) it.next();
			System.out.println("이름 : " + entry.getKey() + ", 점수 : " + entry.getValue());
		}
		
      System.out.println();
		
      /* 2. keySet() 사용 :  key 는 Unquie 하니 Set 에 저장 */
		Set scoreSet = scoreMap.keySet();
      for(Object key: scoreSet) {
		   System.out.println(key + " : " + scoreMap.get(key));
      }
		
      System.out.println();

      /* HashMap에서 value만 출력 */
		Collection values = scoreMap.values();
		it = values.iterator();
		
		int total = 0;
		while(it.hasNext()) {
			total += (int)(it.next());
		}
		
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + (float)total/scoreMap.size());
		System.out.println("최고 점수 : " + Collections.max(values));
		System.out.println("최하 점수 : " + Collections.min(values));
   }
}
