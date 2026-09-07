public class TreatmentStack {

    private class Node {
        Treatment treatment;
        Node next;
        Node(Treatment treatment) { this.treatment = treatment; }
    }

    private Node top;
    private int size = 0;

    // Push - add a completed treatment record
    public void push(Treatment treatment) {
        Node newNode = new Node(treatment);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop - remove the most recently completed treatment record
    public Treatment pop() {
        if (top == null) {
            System.out.println("Treatment history is empty.");
            return null;
        }
        Treatment treatment = top.treatment;
        top = top.next;
        size--;
        return treatment;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    // Display treatment records (most recent first)
    public void displayStack() {
        if (top == null) {
            System.out.println("  No completed treatments recorded yet.");
            return;
        }
        Node current = top;
        while (current != null) {
            System.out.println("  " + current.treatment);
            current = current.next;
        }
    }
}
