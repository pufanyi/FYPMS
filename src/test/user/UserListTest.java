package test.user;

import org.junit.jupiter.api.DisplayName;
import user.singleuser.Student;
import user.userlist.UserList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserListTest {

    @Test
    @DisplayName("Test add user")
    public void addUserTest() {
        UserList<Student> userList = new UserList<Student>();
        Student student1 = new Student("A1234567A", "Lucas", "Lucas@e.ntu.edu.sg");
        Student student2 = new Student("12345", "pufanyi","pufanyi@e.ntu.edu.sg");
        Student student3 = new Student("78687", "jinqingyang", "jinqingyang@e.ntu.edu.sg");
        userList.addUser(student1);
        userList.addUser(student2);
        userList.addUser(student3);
        assertSame(student1, userList.getUser("A1234567A"));
        assertNotSame(student1, userList.getUser("12345"));
    }

    @Test
    @DisplayName("Test remove user")
    public void removeUser(){
        UserList<Student> userList = new UserList<Student>();
        Student student1 = new Student("A1234567A", "Lucas", "Lucas@e.ntu.edu.sg");
        Student student2 = new Student("12345", "pufanyi","pufanyi@e.ntu.edu.sg");
        Student student3 = new Student("78687", "jinqingyang", "jinqingyang@e.ntu.edu.sg");
        userList.addUser(student1);
        userList.addUser(student2);
        userList.addUser(student3);
        assertTrue(userList.containsUser("A1234567A"));
        assertTrue(userList.containsUser(student2));
        userList.removeUser(student1);
        userList.removeUser(student2);
        assertFalse(userList.containsUser(student1));
    }
}