public class VisitLinkedList {

    private class Node {
        Visit visit;
        Node next;
        Node(Visit visit) { this.visit = visit; }
    }

    private Node head;

    // Add a new visit to the end of the list
    public void addVisit(Visit visit) {
        Node newNode = new Node(visit);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) current = current.next;
        current.next = newNode;
    }

    // Remove a visit by Visit ID
    public boolean removeVisit(int visitId) {
        if (head == null) return false;

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Search for a visit by Visit ID
    public Visit searchVisit(int visitId) {
        Node current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) return current.visit;
            current = current.next;
        }
        return null;
    }

    // Display the full visit history
    public void displayVisits() {
        if (head == null) {
            System.out.println("  No visit history available.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println("  " + current.visit);
            current = current.next;
        }
    }
}
