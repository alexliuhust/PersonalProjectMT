package io.alexspringboot.ppmtool.exceptions;

/**
 * ProjectNotFoundResponse:
 * It determines how the exception will look like as json format:
 * e.g.
 * {
 *     "BusinessNotFound": "Business with ID 'A0003' NOT found"
 * }
 */
public class ProjectNotFoundResponse {

    private String businessNotFound;

    public ProjectNotFoundResponse(String message) {
        this.businessNotFound = message;
    }

    public String getBusinessNotFound() {
        return businessNotFound;
    }

    public void setBusinessNotFound(String message) {
        this.businessNotFound = message;
    }
}
