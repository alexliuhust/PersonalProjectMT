package io.alexspringboot.ppmtool.exceptions;

/**
 * ProjectNotFoundResponse:
 * It determines how the exception will look like as json format:
 * e.g.
 * {
 *     "ProjectNotFound": "Project with ID 'A0003' NOT found"
 * }
 */
public class ProjectNotFoundResponse {

    private String projectNotFound;

    public ProjectNotFoundResponse(String projectNotFoundMessage) {
        this.projectNotFound = projectNotFoundMessage;
    }

    public String getProjectNotFound() {
        return projectNotFound;
    }

    public void setProjectNotFound(String projectNotFoundMessage) {
        this.projectNotFound = projectNotFoundMessage;
    }
}
