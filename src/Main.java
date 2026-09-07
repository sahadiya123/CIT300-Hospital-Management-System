import java.util.Scanner;

public class Main {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextVisitId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: registerPatient(); break;
                case 2: searchPatient(); break;
                case 3: deletePatient(); break;
                case 4: displayAllPatients(); break;
                case 5: addToQueue(); break;
                case 6: treatNextPatient(); break;
                case 7: displayQueue(); break;
                case 8: displayTreatmentHistory(); break;
                case 9: addVisit(); break;
                case 10: removeVisit(); break;
                case 11: searchVisit(); break;
                case 12: displayVisitHistory(); break;
                case 0: running = false; System.out.println("Exiting system. Goodbye!"); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Mini Hospital Emergency Management System =====");
        System.out.println("--- Patient Records (BST) ---");
        System.out.println("1. Register new patient");
        System.out.println("2. Search patient by ID");
        System.out.println("3. Delete patient by ID");
        System.out.println("4. Display all patients (in-order)");
        System.out.println("--- Emergency Queue ---");
        System.out.println("5. Add patient to emergency queue");
        System.out.println("6. Treat next patient (dequeue + record treatment)");
        System.out.println("7. Display waiting queue");
        System.out.println("--- Treatment History (Stack) ---");
        System.out.println("8. Display treatment history");
        System.out.println("--- Patient Visit History (Linked List) ---");
        System.out.println("9. Add visit to patient history");
        System.out.println("10. Remove visit from patient history");
        System.out.println("11. Search visit in patient history");
        System.out.println("12. Display patient visit history");
        System.out.println("0. Exit");
    }

    // ---------- BST operations ----------

    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        if (patient != null) {
            System.out.println("Found: " + patient);
        } else {
            System.out.println("Patient with ID " + id + " not found.");
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        System.out.println(deleted ? "Patient deleted successfully." : "Patient with ID " + id + " not found.");
    }

    private static void displayAllPatients() {
        System.out.println("Patients in ascending order of Patient ID:");
        patientBST.displayInOrder();
    }

    // ---------- Queue operations ----------

    private static void addToQueue() {
        int id = readInt("Enter Patient ID to add to queue: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found. Register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) return;

        System.out.println("Now treating: " + patient);
        System.out.print("Enter treatment details: ");
        String details = scanner.nextLine();
        System.out.print("Enter completion date (e.g. 2026-09-07): ");
        String date = scanner.nextLine();

        Treatment treatment = new Treatment(patient.getId(), patient.getName(), details, date);
        treatmentStack.push(treatment);

        // Also log this as a visit in the patient's history
        Visit visit = new Visit(nextVisitId++, date, "N/A", patient.getCondition(), details);
        patient.getVisitHistory().addVisit(visit);

        System.out.println("Treatment completed and recorded.");
    }

    private static void displayQueue() {
        System.out.println("Patients currently waiting:");
        emergencyQueue.displayQueue();
    }

    // ---------- Stack operations ----------

    private static void displayTreatmentHistory() {
        System.out.println("Treatment history (most recent first):");
        treatmentStack.displayStack();
    }

    // ---------- Linked List operations ----------

    private static void addVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.print("Enter Visit Date: ");
        String date = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctor = scanner.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();

        Visit visit = new Visit(nextVisitId++, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added to patient history.");
    }

    private static void removeVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed." : "Visit ID not found.");
    }

    private static void searchVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit != null ? "Found: " + visit : "Visit ID not found.");
    }

    private static void displayVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    // ---------- Helper ----------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
