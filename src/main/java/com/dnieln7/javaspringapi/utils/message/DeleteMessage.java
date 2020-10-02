package com.dnieln7.javaspringapi.utils.message;

public class DeleteMessage {
    private Boolean success;
    private String message;
    private Object deletedEntity;

    public DeleteMessage() {
    }

    public DeleteMessage(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public DeleteMessage(Boolean success, String message, Object deletedEntity) {
        this.success = success;
        this.message = message;
        this.deletedEntity = deletedEntity;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDeletedEntity() {
        return deletedEntity;
    }

    public void setDeletedEntity(Object deletedEntity) {
        this.deletedEntity = deletedEntity;
    }

    @Override
    public String toString() {
        return "DeleteMessage{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", deletedEntity=" + deletedEntity +
                '}';
    }
}
