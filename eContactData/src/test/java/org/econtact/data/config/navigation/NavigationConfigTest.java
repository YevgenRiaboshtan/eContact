package org.econtact.data.config.navigation;

import org.econtact.data.config.ConfigHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class NavigationConfigTest {
    private static final String CORRECT_BASE_RESOURCE = "correctBaseNavigationConfig.xml";
    private static final String CORRECT_FULL_RESOURCE = "correctFullNavigationConfig.xml";
    private static final String INCORRECT_NAVIGATION_RESOURCE = "incorrectNavigationConfig.xml";
    private static final String INCORRECT_ROOT_RESOURCE = "incorrectRootNavigationConfig.xml";
    private static final String INCORRECT_ACTION_RESOURCE = "incorrectActionNavigationConfig.xml";
    private static Unmarshaller unmarshaller;

    @BeforeClass
    public static void setUp() throws Exception {
        final URL schemaUrl = Thread.currentThread().getContextClassLoader()
                .getResource(ConfigHelper.NAVIGATION_SCHEMA_RESOURCE);
        final JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{NavigationConfig.class});
        final Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(schemaUrl);
        unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);
    }

    @Test
    public void testCorrectBaseNavigationConfig() throws Exception {
        final NavigationConfig navigationConfig = unmarshalNavigationConfig(CORRECT_BASE_RESOURCE);
        assertEquals(1, navigationConfig.getRoots().size());
        assertEquals(1, navigationConfig.getRoots().get(0).getActions().size());
    }

    @Test
    public void testCorrectFullNavigationConfig() throws Exception {
        final NavigationConfig navigationConfig = unmarshalNavigationConfig(CORRECT_FULL_RESOURCE);
        final RootConfig root1 = navigationConfig.getRoots().get(0);
        final RootConfig root2 = navigationConfig.getRoots().get(1);
        assertEquals(2, navigationConfig.getRoots().size());
        assertEquals(2, root1.getActions().size());
        assertEquals(1, root2.getActions().size());
        assertEquals(2, root1.getActions().get(0).getParameters().size());
    }

    @Test(expected = UnmarshalException.class)
    public void testIncorrectNavigationConfig() throws Exception {
        final NavigationConfig navigationConfig = unmarshalNavigationConfig(INCORRECT_NAVIGATION_RESOURCE);
    }

    @Test(expected = UnmarshalException.class)
    public void testIncorrectRootNavigationConfig() throws Exception {
        final NavigationConfig navigationConfig = unmarshalNavigationConfig(INCORRECT_ROOT_RESOURCE);
    }

    @Test(expected = UnmarshalException.class)
    public void testIncorrectActionNavigationConfig() throws Exception {
        final NavigationConfig navigationConfig = unmarshalNavigationConfig(INCORRECT_ACTION_RESOURCE);
    }

    private NavigationConfig unmarshalNavigationConfig(String resourceName) throws Exception {
        final URL resourceUrl = getClass().getResource(resourceName);
        final NavigationConfig navigationConfig = (NavigationConfig) unmarshaller.unmarshal(resourceUrl);
        return navigationConfig;
    }
}
