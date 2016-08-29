package org.econtact.data.config.navigation;

import org.econtact.data.config.ConfigHelper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = ParameterConfig.NAME, namespace = ConfigHelper.NAVIGATION_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class ParameterConfig {
    public static final String WRAPPER = "parameters";
    public static final String NAME = "parameter";

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlAttribute(name = "value", required = true)
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
