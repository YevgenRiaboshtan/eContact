package org.econtact.web.security;

import org.econtact.data.service.AuthenticationService;
import org.econtact.web.util.WebHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        WebHelper.getEjbBean(AuthenticationService.class).connectUser(
                userDetails.getPerson(),
                request.getSession().getId(),
                request.getRemoteHost(),
                request.getHeader("user-agent"));
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
