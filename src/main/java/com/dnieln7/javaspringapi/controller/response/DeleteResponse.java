package com.dnieln7.javaspringapi.controller.response;

/**
 * Response for the delete operation
 *
 * @author dnieln7
 */
public class DeleteResponse {

    private int success;
    private String description;

    public DeleteResponse() {
    }

    public DeleteResponse(int success, String description) {
        this.success = success;
        this.description = description;
    }

    /**
     * Delete code
     *
     * @return 1 if successful; 0 otherwise
     */
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "There was an error! Code: " + this.success + " Description: " + this.description;
    }
}
