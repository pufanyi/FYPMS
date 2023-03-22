package test.repository.user;

import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;

import java.io.IOException;

public class FacultySaveTest {
    private static Supervisor[] supervisors;

    public static void setUp() {
        supervisors = new Supervisor[3];
        supervisors[0] = new Supervisor("A1234567A", "Lucas", "Lucas@e.ntu.edu.sg");
        supervisors[1] = new Supervisor("12345", "pufanyi", "pufanyi@e.ntu.edu.sg");
        supervisors[2] = new Supervisor("78687", "jinqingyang", "jinqingyang@e.ntu.edu.sg");
    }

    public static void main(String[] args) throws IOException {
        setUp();
        FacultyRepository facultyRepository = new FacultyRepository();
        facultyRepository.add(supervisors[0]);
        facultyRepository.add(supervisors[1]);
        facultyRepository.add(supervisors[2]);
    }
}
