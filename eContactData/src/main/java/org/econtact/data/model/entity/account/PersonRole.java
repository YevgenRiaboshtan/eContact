package org.econtact.data.model.entity.account;

public enum PersonRole {
    ROLE_SYS_ADMIN {
        @Override
        public String getValue() {
            return "ROLE_SYS_ADMIN";
        }
    },
    ROLE_OWNER {
        @Override
        public String getValue() {
            return "ROLE_OWNER";
        }
    },
    ROLE_MANAGER {
        @Override
        public String getValue() {
            return "ROLE_MANAGER";
        }
    },
    ROLE_REGISTRATOR {
        @Override
        public String getValue() {
            return "ROLE_REGISTRATION";
        }
    };

    public abstract String getValue();
}
