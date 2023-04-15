package main.model.request;

import main.model.Displayable;
import main.model.Model;

/**
 * This interface represents a request.
 */
public interface Request extends Model, Displayable {
    /**
     * Get the ID of the request.
     */
    String getID();

    /**
     * Get the ID of the project.
     *
     * @return the ID of the project.
     */
    String getProjectID();

    /**
     * Get the ID of the student.
     *
     * @return the ID of the student.
     */
    String getStudentID();

    /**
     * Get the status of the request.
     *
     * @return the status of the request.
     */
    RequestStatus getStatus();

    /**
     * Set the status of the request.
     *
     * @param status the status of the request.
     */
    void setStatus(RequestStatus status);

    /**
     * Get the type of the request.
     *
     * @return the type of the request.
     */
    RequestType getRequestType();

    /**
     * Get the ID of the supervisor.
     * @return the ID of the supervisor.
     */
    String getSupervisorID();

    default String getSplitter() {
        return "====================================================";
    }
}
