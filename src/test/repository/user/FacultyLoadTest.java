package test.repository.user;

import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;

public class FacultyLoadTest {
    public static void main(String[] args) {
        for (Supervisor supervisor : FacultyRepository.getInstance()) {
            System.out.println(supervisor.toMap());
        }
    }
}
