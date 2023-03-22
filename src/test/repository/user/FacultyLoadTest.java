package test.repository.user;

import model.user.Supervisor;
import repository.user.FacultyRepository;

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
