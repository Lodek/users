package com.wipro.bartenders.users.api.common.audit;

public enum AuditIgnoredFieldsEnum {
    FILE("file"),

    DOCUMENT("document"),

    FILE_ATTACHMENT("fileAttachment");

    private final String field;


    AuditIgnoredFieldsEnum(String field) {
        this.field = field;
    }

    public String field(){
        return this.field;
    }
}
