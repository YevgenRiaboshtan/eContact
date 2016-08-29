package org.econtact.data.service;

import org.econtact.data.config.navigation.RootConfig;

import java.util.List;

public interface ConfigService {

    List<RootConfig> getNavigationConfig();
}
