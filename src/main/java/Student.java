public class Student {

    private String name;
    private String surname;
    private String email;
    private String group;

    public Student() {
    }

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Student(String name, String surname, String email, String group) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String fullName() {
        return String.format("%s %s", this.name, this.surname);
    }

    @Override
    public String toString() {
        return "\n" + "{" +
                "\n name=" + name +
                ",\n surname=" + surname +
                ",\n email=" + email+
                '}' + "\n";
    }

}