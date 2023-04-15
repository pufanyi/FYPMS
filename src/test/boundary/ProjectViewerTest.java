package test.boundary;

import main.boundary.modelviewer.ProjectViewer;
import main.utils.exception.PageBackException;

/**

 This class is used to test the functionality of the ProjectViewer class,
 which displays all projects stored in the system.
 */
public class ProjectViewerTest {
    /**
     * The main method is used to test the viewAllProject method from the ProjectViewer class.
     * It calls the viewAllProject method to display all projects stored in the system.
     * @throws PageBackException if the user chooses to go back to the previous page
     */
    public static void main(String[] args) throws PageBackException {
        ProjectViewer.viewAllProject();
    }
}
