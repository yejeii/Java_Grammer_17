package comparable_and_comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* 1. Comparable 을 이용한 정렬 */
		
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(1005, "Sean", 98000.00));
		empList.add(new Employee(1000, "Amy", 30000.00));
		empList.add(new Employee(1299, "Martin", 99000.00));
		empList.add(new Employee(1015, "Barry", 70000.00));
		empList.add(new Employee(1105, "Sam", 98000.00));
		empList.add(new Employee(1115, "Harry", 99000.00));
		
		System.out.println("Before Sorting empList::::");
		empList.forEach(System.out::println);
		
		System.out.println();
		System.out.println("After Sorting empList based on ID::::");
		
		Collections.sort(empList);
		empList.forEach(System.out::println);
		
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println();
		
		/* 2. Comparator 를 이용한 정렬 */

		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person(1005, "Sean"));
		personList.add(new Person(1000, "Amy"));
		personList.add(new Person(1299, "Martin"));
		personList.add(new Person(1015, "Barry"));
		personList.add(new Person(1102, "Sam"));
		personList.add(new Person(1115, "Harry"));
		
		System.out.println("Before Sorting personList::::");
		personList.forEach(System.out::println);
		
		System.out.println();
		System.out.println("After Sorting personList based on ID::::");
		
		Collections.sort(personList, new SortPersonById());
		personList.forEach(System.out::println);
		
		System.out.println();
		
		/* 3. Anonymous class 를 이용한 정렬 : 일회성 */
		
		System.out.println("After Sorting personList based on Name::::");
		
		Collections.sort(personList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		personList.forEach(System.out::println);
	}

}
