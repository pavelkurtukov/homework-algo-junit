import mystack.MyStack;

public class Stack {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();

        // Демонстрация
        myStack.print();
        myStack.push(0);
        System.out.println("Добавляем 0");
        myStack.print();
        myStack.push(1);
        System.out.println("Добавляем 1");
        myStack.print();
        myStack.push(2);
        System.out.println("Добавляем 2");
        myStack.print();
        myStack.push(3);
        System.out.println("Добавляем 3");
        myStack.print();
        myStack.push(4);
        System.out.println("Добавляем 4");
        myStack.print();
        myStack.push(5);
        System.out.println("Добавляем 5");
        myStack.print();
        myStack.pop();
        System.out.println("Снимаем 5");
        myStack.print();
        myStack.pop();
        System.out.println("Снимаем 4");
        myStack.print();
        myStack = myStack.reverse();
        System.out.println("Ревёрс!");
        myStack.print();
        myStack.pop();
        System.out.println("Снимаем 0");
        myStack.print();
        myStack.pop();
        System.out.println("Снимаем 1");
        myStack.print();
        myStack = myStack.reverse();
        System.out.println("Ревёрс!");
        myStack.print();
        myStack.pop();
        System.out.println("Снимаем 3");
        myStack.print();
        myStack.pop();
        System.out.println("Снимаем 2");
        myStack.print();

        System.out.println();

        MyStack<String> myStack2 = new MyStack<>();
        myStack2.push("Привет");
        myStack2.print();
        myStack2.push("Медвед");
        myStack2.print();
        myStack2.push("Босяка");
        myStack2.print();
        myStack2.pop();
        myStack2.print();
        myStack2 = myStack2.reverse();
        myStack2.print();
    }
}
