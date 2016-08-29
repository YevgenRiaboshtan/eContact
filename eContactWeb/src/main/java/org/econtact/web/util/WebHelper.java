package org.econtact.web.util;

import org.zkoss.xel.ExpressionFactory;
import org.zkoss.xel.Expressions;
import org.zkoss.xel.util.SimpleXelContext;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.xel.impl.ExecutionResolver;

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

    public static <T> T resolveExecutionExpression(final String expression, final Class<T> resultClass) {
        if (expression == null) {
            return null;
        } else {
            final ExpressionFactory expressionFactory = Expressions.newExpressionFactory();
            final SimpleXelContext xelContext = new SimpleXelContext(new ExecutionResolver(Executions.getCurrent(), null));
            final T result = (T) expressionFactory.evaluate(xelContext, expression, resultClass);
            return result;
        }
    }
}
