package test.repository.user;

import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;

import java.io.IOException;

public class FacultyLoadTest {
    public static void main(String[] args) throws IOException {
        FacultyRepository facultyRepository = new FacultyRepository();
        facultyRepository.load();
        for (Supervisor supervisor : facultyRepository) {
            System.out.println(supervisor);
        }
    }
}
