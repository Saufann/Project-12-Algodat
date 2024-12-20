public class NodeQueue {
    String nama;
    Stack stack;
    NodeQueue next;

    public NodeQueue(String nama) {
        this.nama = nama;
        this.stack = new Stack(nama);
        this.next = null;
    }
}
