package mystack;

class MyStackElement<T> {
    private T value; // Значение текущего элемента
    private MyStackElement<T> prev; // Ссылка на предыдущий элемент

    MyStackElement(T value, MyStackElement<T> prev) {
        this.value = value;
        this.prev = prev;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    MyStackElement<T> getPrev() {
        return prev;
    }

    void setPrev(MyStackElement<T> prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
