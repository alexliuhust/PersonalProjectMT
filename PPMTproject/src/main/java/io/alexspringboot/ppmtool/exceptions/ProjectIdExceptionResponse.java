package io.alexspringboot.ppmtool.exceptions;

/**
 * ProjectIdExceptionResponse:
 * It determines how the exception will look like as json format:
 * e.g.
 * {
 *     "projectIdentifier": "Project ID 'A0003' does NOT exist"
 * }
 */
public class ProjectIdExceptionResponse {

    private String projectIdentifier;

    public ProjectIdExceptionResponse(String projectIdExceptionMassage) {
        this.projectIdentifier = projectIdExceptionMassage;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdExceptionMassage) {
        this.projectIdentifier = projectIdExceptionMassage;
    }
}
