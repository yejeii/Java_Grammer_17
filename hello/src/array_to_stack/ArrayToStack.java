package array_to_stack;

public class ArrayToStack {

   public static void main(String args[]) {
      ArrayStack newStack = new ArrayStack(5);
      newStack.push(4);
      newStack.push(6);
      newStack.push(7);
      newStack.push(2);
      newStack.push(5);

      newStack.pop();
      newStack.peek();
      newStack.peek();
      newStack.pop();
   }
}

class ArrayStack {
   int top;      //인덱스
   int size;     //스택 배열의 크기
   Object [] stack;

   public ArrayStack(int size) {
      this.size = size;
      stack = new Object[size];
      top = -1;   // pop 시 삭제할 데이터 위치와 다음으로 가리킬 마지막 위치를 편하게 잡아주기 위해 -1로 설정
   }

   public void push(Object item) {
      stack[++top] = item;
      System.out.println("stack[" + top + "] " + stack[top] + " Push!");
   }
   public Object pop() {
      System.out.println("stack[" + top + "] " + stack[top] + " Pop!");
      Object pop = stack[top];
      stack[top--] = null;
      return pop;
   }
   public void peek() {
      System.out.println("stack[" + top + "] " + stack[top] + " Peek!");
   }
}