package mystack;

public class MyStack<T> {
    private MyStackElement<T> top; // Ссылка на верхний элемент стека

    public void push(T element) {
        // Если в стеке пусто - добавляем новый элемент с пустой ссылкой
        if (top == null) {
            top = new MyStackElement<>(element, null);
        }
        // Если в стеке есть элемент - добавляем новый наверх с ссылкой на предыдущий элемент
        else {
            top = new MyStackElement<>(element, top);
        }
    }

    public T pop() {
        T elementValue = top.getValue();
        top = top.getPrev();
        return elementValue;
    }

    public MyStack<T> reverse() {
        MyStack<T> newStack= new MyStack<>();
        if (top != null) {
            MyStackElement<T> nextElement = top;
            while (nextElement != null) {
                newStack.push(nextElement.getValue());
                nextElement = nextElement.getPrev();
            }
        } else {
            newStack.top = null;
        }
        return newStack;
    }

    public void print() {
        if (top == null) {
            System.out.println("ПУСТО");
            return;
        }

        StringBuilder sb = new StringBuilder();
        MyStackElement<T> currentElement = top;
        while (currentElement != null){
            if (!currentElement.equals(top)) {
                sb.append(" -> ");
            }
            sb.append(currentElement);
            currentElement = currentElement.getPrev();
        }
        System.out.println(sb);
    }
}
