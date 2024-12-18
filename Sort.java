public class Sort{
    public void bubbleSort(Queue queue) {
            if (queue.isEmpty()) {
                System.out.println("Antrian kosong, tidak ada yang bisa diurutkan.");
                return;
            }

            boolean swapped;
            do {
                swapped = false;
                Node current = queue.front;

                while (current != null && current.next != null) {
                    if (current.barang.compareTo(current.next.barang) > 0) {
                        String tempBarang = current.barang;
                        String tempKurir = current.kurir;

                        current.barang = current.next.barang;
                        current.kurir = current.next.kurir;

                        current.next.barang = tempBarang;
                        current.next.kurir = tempKurir;

                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped);
    }
}
