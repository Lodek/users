package com.wipro.bartenders.users.api.common.audit;

public enum AuditIgnoredFieldsEnum {
    FILE("file"),

    DOCUMENT("document"),

    FILE_ATTACHMENT("fileAttachment");

    public final String value;


    AuditIgnoredFieldsEnum(String field) {
        this.value = field;
    }
}
