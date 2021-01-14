package io.alexspringboot.ppmtool.exceptions;

/**
 * ProjectIdExceptionResponse:
 * It determines how the exception will look like as json format:
 * e.g.
 * {
 *     "Identifier": "Business ID 'A0003' does NOT exist"
 * }
 */
public class ProjectIdExceptionResponse {

    private String businessIdentifier;

    public ProjectIdExceptionResponse(String exceptionMassage) {
        this.businessIdentifier = exceptionMassage;
    }

    public String getIdentifier() {
        return businessIdentifier;
    }

    public void setIdentifier(String exceptionMassage) {
        this.businessIdentifier = exceptionMassage;
    }
}
