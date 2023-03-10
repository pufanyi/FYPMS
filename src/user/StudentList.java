package user;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private final List<Student> students;

    public StudentList() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student getStudent(String studentID) {
        for (Student student : students) {
            if (student.getUserID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    public boolean removeStudent(String studentID) {
        Student student = getStudent(studentID);
        if (student != null) {
            removeStudent(student);
            return true;
        }
        return false;
    }
}
