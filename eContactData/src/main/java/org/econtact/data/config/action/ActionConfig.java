package org.econtact.data.config.action;

import org.econtact.data.config.ConfigHelper;
import org.econtact.data.config.navigation.ParameterConfig;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = ActionConfig.NAME, namespace = ConfigHelper.NAVIGATION_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionConfig implements Serializable {

    public static final String NAME = "action";

    @XmlAttribute(name = "category", required = false)
    private String category;

    @XmlAttribute(name = "label", required = true)
    private String label;

    @XmlAttribute(name = "tooltip")
    private String tooltip;

    @XmlAttribute(name = "image", required = false)
    private String image;

    @XmlAttribute(name = "rendered", required = false)
    private String rendered;

    @XmlAttribute(name = "outcome", required = false)
    private String outcome;

    @XmlAttribute(name = "dirty-sensitive", required = false)
    private boolean dirtySensitive = true;

    @XmlAttribute(name = "child-factory", required = false)
    private String childFactory;

    @XmlElementWrapper(name = ParameterConfig.WRAPPER, namespace = ConfigHelper.NAVIGATION_NAMESPACE)
    @XmlElement(name = ParameterConfig.NAME, required = false, namespace = ConfigHelper.NAVIGATION_NAMESPACE)
    private List<ParameterConfig> parameters;

    @XmlTransient
    private Map<String, String> parameterMap;

    @XmlElement(name = ActionConfig.NAME, required = false)
    private List<ActionConfig> actions;

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        if (parameters != null && !parameters.isEmpty()) {
            parameterMap = new HashMap<>();
            for (ParameterConfig parameter : parameters) {
                parameterMap.put(parameter.getName(), parameter.getValue());
            }
        }
        parameters = null;
    }

    public ActionCategory getCategory() {
        return category == null ? ActionCategory.UNDEFINED : ActionCategory.valueOf(category.toUpperCase());
    }

    public String getLabel() {
        return label;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getImage() {
        return image;
    }

    public String getRendered() {
        return rendered == null ? Boolean.TRUE.toString() : rendered;
    }

    public String getOutcome() {
        return outcome;
    }

    public boolean isDirtySensitive() {
        return dirtySensitive;
    }

    public String getChildFactory() {
        return childFactory;
    }

    public Map<String, String> getParameters() {
        return parameterMap == null ? Collections.EMPTY_MAP : parameterMap;
    }

    public List<ActionConfig> getActions() {
        return actions == null ? Collections.EMPTY_LIST : actions;
    }

}
