package org.econtact.zk.ext.filter;

import org.apache.commons.lang.StringUtils;
import org.econtact.data.filter.Filter;
import org.econtact.data.filter.impl.EqualFilter;
import org.econtact.data.filter.impl.LikeFilter;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Textbox;

import java.io.Serializable;

public class StringFilterBox extends AbstractFilterBox<String, Textbox> {
    private static final String REGEXP = "%?[\\p{L}\\d]+%?";
    private final Textbox textbox;

    public StringFilterBox(String fieldName) {
        super(fieldName);
        textbox = new Textbox();
        textbox.setWidth("100%");
        textbox.setConstraint(new ConstraintImpl());
    }


    @Override
    public Textbox getInputUi() {
        return textbox;
    }

    @Override
    public void setValue(Object value) {
        textbox.setRawValue(value);
        updateState();
    }

    @Override
    public Filter<String> getFilter() {
        final Filter<String> result;
        if (textbox.getValue().isEmpty()) {
            result = null;
        } else {
            final String value = textbox.getValue();
            result = chooseFilterByValue(value);
        }
        return result;
    }

    private Filter<String> chooseFilterByValue(String value) {
        if (value.contains("%")) {
           return new LikeFilter(fieldName, value);
        } else {
            return new EqualFilter<>(fieldName, value);
        }
    }

    @Override
    protected boolean isEmpty() {
        return textbox.getRawText().isEmpty();
    }

    private static class ConstraintImpl implements Constraint, Serializable {

        @Override
        public void validate(Component comp, Object value) throws WrongValueException {
            if (value == null || value.toString().isEmpty()) {
                return;
            } else if (!value.toString().matches(REGEXP)){
                throw new WrongValueException(comp, Labels.getLabel("browser.filter.string.error.msg"));
            }
        }
    }
}
