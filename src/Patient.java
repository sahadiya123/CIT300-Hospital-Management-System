public class Patient {
    private int id;
    private String name;
    private int age;
    private String contact;
    private String condition;
    private VisitLinkedList visitHistory;

    public Patient(int id, String name, int age, String contact, String condition) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.condition = condition;
        this.visitHistory = new VisitLinkedList();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getContact() { return contact; }
    public String getCondition() { return condition; }
    public VisitLinkedList getVisitHistory() { return visitHistory; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setContact(String contact) { this.contact = contact; }
    public void setCondition(String condition) { this.condition = condition; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age +
               " | Contact: " + contact + " | Condition: " + condition;
    }
}
