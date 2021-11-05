import java.util.List;

public class Classroom {
    private Student student;
    private List<Student> students;

    public Classroom() {

    }

    public Classroom(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean addStudent(Student student){
        getStudents().add(student);
        return true;
    }

    public int studentCount(){
        return getStudents().size();
    }

   public boolean deleteStudent (Student student){
        getStudents().remove(student);
        return true;
   }

    @Override
    public String toString() {
        return "CLassroom"  +
                "students="+  students +
                '}';
    }
}
