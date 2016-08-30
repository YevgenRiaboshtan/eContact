package org.econtact.data.config.navigation;

import org.econtact.data.config.ConfigHelper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "navigation", namespace = ConfigHelper.NAVIGATION_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class NavigationConfig implements Serializable {
    private static final long serialVersionUID = 7016427043577698163L;
    @XmlElement(name = "root")
    private List<RootConfig> roots;

    public List<RootConfig> getRoots() {
        return roots;
    }

    public void setRoots(List<RootConfig> roots) {
        this.roots = roots;
    }
}
