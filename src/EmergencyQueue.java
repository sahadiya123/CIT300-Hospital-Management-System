public class EmergencyQueue {

    private class Node {
        Patient patient;
        Node next;
        Node(Patient patient) { this.patient = patient; }
    }

    private Node front, rear;
    private int size = 0;

    // Enqueue - add a patient to the waiting queue
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getName() + " added to the waiting queue.");
    }

    // Dequeue - remove the next patient for treatment
    public Patient dequeue() {
        if (front == null) {
            System.out.println("Queue is empty. No patients waiting.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return patient;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    // Display all patients currently waiting
    public void displayQueue() {
        if (front == null) {
            System.out.println("  No patients currently waiting.");
            return;
        }
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }
}
