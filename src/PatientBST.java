public class PatientBST {

    private class Node {
        Patient patient;
        Node left, right;
        Node(Patient patient) { this.patient = patient; }
    }

    private Node root;

    // Insert a new patient using Patient ID as the key
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) return new Node(patient);
        if (patient.getId() < node.patient.getId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getId() > node.patient.getId()) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("Patient ID " + patient.getId() + " already exists. Insert skipped.");
        }
        return node;
    }

    // Search for a patient by Patient ID
    public Patient search(int id) {
        return searchRec(root, id);
    }

    private Patient searchRec(Node node, int id) {
        if (node == null) return null;
        if (id == node.patient.getId()) return node.patient;
        return id < node.patient.getId() ? searchRec(node.left, id) : searchRec(node.right, id);
    }

    // Delete a patient by Patient ID
    public boolean delete(int id) {
        if (search(id) == null) return false;
        root = deleteRec(root, id);
        return true;
    }

    private Node deleteRec(Node node, int id) {
        if (node == null) return null;

        if (id < node.patient.getId()) {
            node.left = deleteRec(node.left, id);
        } else if (id > node.patient.getId()) {
            node.right = deleteRec(node.right, id);
        } else {
            // Node with only one child or no child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Node with two children: get the in-order successor (smallest in right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // In-order traversal: displays patients in ascending order of Patient ID
    public void displayInOrder() {
        if (root == null) {
            System.out.println("  No patients registered yet.");
            return;
        }
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println("  " + node.patient);
        inOrderRec(node.right);
    }
}
