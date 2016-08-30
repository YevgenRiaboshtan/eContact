package org.econtact.data.config.navigation;

import org.econtact.data.config.ConfigHelper;
import org.econtact.data.config.action.ActionConfig;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "root", namespace = ConfigHelper.NAVIGATION_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class RootConfig implements Serializable {

    private static final long serialVersionUID = 7189447670767294084L;
    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlAttribute(name = "rendered", required = false)
    private String rendered = Boolean.TRUE.toString();

    @XmlElement(name = ActionConfig.NAME)
    private List<ActionConfig> actions;

    public String getName() {
        return name;
    }

    public String getRendered() {
        return rendered;
    }

    public List<ActionConfig> getActions() {
        return actions;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != RootConfig.class) {
            return false;
        }
        final RootConfig other = (RootConfig) obj;
        return name.equals(other.name);
    }
}
