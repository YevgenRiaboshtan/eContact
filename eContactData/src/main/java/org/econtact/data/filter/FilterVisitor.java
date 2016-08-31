package org.econtact.data.filter;

@FunctionalInterface
public interface FilterVisitor<T extends Filter> {

    void processFilter(T filter);
}
