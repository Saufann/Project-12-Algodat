public class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(String barang) {
        Node newNode = new Node(null, barang);
        if (top != null) {
            newNode.prev = top;
        }
        top = newNode;
        System.out.println("Barang push: " + barang);
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Barang kosong.");
            return null;
        }
        String poppedBarang = top.barang;
        top = top.prev;
        System.out.println("Barang pop: " + poppedBarang);
        return poppedBarang;
    }

    public String peek() {
        if (isEmpty()) {
            System.out.println("Barang kosong.");
            return null;
        }
        return top.barang;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Barang kosong.");
            return;
        }
        Node current = top;
        System.out.println("List Barang");
        while (current != null) {
            System.out.println(current.barang);
            current = current.prev;
        }
    }
}