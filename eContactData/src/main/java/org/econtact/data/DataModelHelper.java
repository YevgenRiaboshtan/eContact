package org.econtact.data;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public final class DataModelHelper {

    public static final String ECONTACT_SCHEMA = "";
    public static final BigDecimal ACTUAL_SIGN = BigDecimal.ZERO;

    private static final AtomicLong uidGenerator = new AtomicLong();

    private DataModelHelper() {
    }

    public static Long getUid() {
        return uidGenerator.incrementAndGet();
    }
}
