class Queue {
    private Node front;
    private Node rear;

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(String kurir, String barang) {
        Node newNode = new Node(kurir, barang);
        if (rear == null) { // Queue is empty
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        System.out.println("Barang " + barang + " telah ditambahkan ke antrean.");
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Antrean kosong. Tidak ada barang yang diproses.");
            return;
        }
        Node dequeuedNode = front;
        System.out.println("Barang " + dequeuedNode.barang +  " telah sampai tujuan.");
        front = front.next;
        if (front == null) { // Queue becomes empty after dequeue
            rear = null;
        } else {
            front.prev = null;
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Antrean barang kosong. ");
            return;
        }
        System.out.println("Barang dalam antrean:");
        Node current = front;
        while (current != null) {
            System.out.println("Barang: " + current.barang + ", Kurir: " + current.kurir);
            current = current.next;
        }
    }
}