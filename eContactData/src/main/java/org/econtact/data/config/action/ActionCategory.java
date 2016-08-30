package org.econtact.data.config.action;

public enum ActionCategory {
    NAVIGATE(null),
    UNDEFINED(null);

    private String ctrlKeys;

    ActionCategory(String ctrlKeys) {
        this.ctrlKeys = ctrlKeys;
    }

    public String getCtrlKeys() {
        return ctrlKeys;
    }
}
