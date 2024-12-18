public class Search {
    public String binarySearch(Queue queue, String targetBarang) {
        if (queue.isEmpty()) {
            return "Antrean kosong. Tidak dapat melakukan pencarian.";
        }

        Node left = queue.front;
        Node right = queue.rear;

        while (left != null && right != null && left != right.next) {
            Node mid = getMiddle(left, right);

            if (mid.barang.equals(targetBarang)) {
                return "Barang ditemukan: " + mid.barang + ", Kurir: " + mid.kurir;
            }

            if (mid.barang.compareTo(targetBarang) < 0) {
                left = mid.next;
            } else {
                right = mid.prev;
            }
        }

        return "Barang tidak ditemukan.";
    }

    private Node getMiddle(Node start, Node end) {
        Node slow = start;
        Node fast = start;

        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}