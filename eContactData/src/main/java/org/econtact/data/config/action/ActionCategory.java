package org.econtact.data.config.action;

public enum ActionCategory {
    REFRESH(null),
    CREATE("@#ins"), EDIT("@#enter"), DELETE("@#del"),
    SAVE("@s"),
    WORKFLOW(null),
    NAVIGATE(null),
    UNDEFINED(null),
    CLOSE("#escape");

    private String ctrlKeys;

    ActionCategory(String ctrlKeys) {
        this.ctrlKeys = ctrlKeys;
    }

    public String getCtrlKeys() {
        return ctrlKeys;
    }
}
