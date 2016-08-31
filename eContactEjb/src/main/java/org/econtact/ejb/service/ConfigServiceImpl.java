package org.econtact.ejb.service;

import org.econtact.data.config.ConfigHelper;
import org.econtact.data.config.navigation.NavigationConfig;
import org.econtact.data.config.navigation.RootConfig;
import org.econtact.data.service.ConfigService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Collections;
import java.util.List;

@Stateless
@Local(ConfigService.class)
public class ConfigServiceImpl implements ConfigService {

    private static final String DEFAULT_NAVIGATION_CONFIG = "META-INF/navigation-config.xml";
    private SoftReference<NavigationConfig> navigationConfigReference;

    @Override
    public List<RootConfig> getNavigationConfig() {
        NavigationConfig navConfig = navigationConfigReference == null ? null : navigationConfigReference.get();
        if (navConfig == null) {
            navConfig = unmarshalNavigationConfig(DEFAULT_NAVIGATION_CONFIG);
            navigationConfigReference = new SoftReference<>(navConfig);
        }
        return navConfig == null ? Collections.EMPTY_LIST : navConfig.getRoots();
    }

    private NavigationConfig unmarshalNavigationConfig(String resourcePath) {
        final URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource(resourcePath);
        final URL schemaUrl = Thread.currentThread().getContextClassLoader().getResource(ConfigHelper.NAVIGATION_SCHEMA_RESOURCE);
        NavigationConfig result = null;
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(NavigationConfig.class);
            final Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(schemaUrl);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);
            result = (NavigationConfig) unmarshaller.unmarshal(resourceUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
