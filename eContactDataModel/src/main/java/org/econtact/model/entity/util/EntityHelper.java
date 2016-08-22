package org.econtact.model.entity.util;

import java.util.concurrent.atomic.AtomicLong;

public final class EntityHelper {

    public static final String ECONTACT_SCHEMA = "dev";

    private static AtomicLong uidGenerator = new AtomicLong();

    private EntityHelper() {
    }

    public static Long getUid() {
        return uidGenerator.incrementAndGet();
    }
}
