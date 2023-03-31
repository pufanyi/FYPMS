package main.model.request;

import main.model.Model;

public interface Request extends Model {
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

    void display();
}
