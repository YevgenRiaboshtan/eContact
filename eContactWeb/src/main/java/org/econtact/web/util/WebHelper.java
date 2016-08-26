package org.econtact.web.util;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

public final class WebHelper {

    private WebHelper(){}

    /**
     * Returns instance of ejb bean. <span style="color:red">for wildfly only.</span>
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T getEjbBean(Class<T> beanClass) {
        final BeanManager beanManager = CDI.current().getBeanManager();
        final Bean bean = beanManager.resolve(beanManager.getBeans(beanClass));
        CreationalContext cCtx = beanManager.createCreationalContext(bean);
        T result = (T) beanManager.getReference(bean, beanClass, cCtx);
        return result;
    }
}
