package com.ThesisApp.config;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Iterator;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LOGGER.info("Logged in user: {}", authentication.getName());
        Iterator<? extends GrantedAuthority> authoritiesIterator = authentication.getAuthorities().iterator();

        if (authoritiesIterator.hasNext()) {
            String role = authoritiesIterator.next().getAuthority();
            LOGGER.info("Role: {}", role);

            if (role.equals("ROLE_STUDENT")) {
                LOGGER.info("Redirecting to student dashboard......");
                response.sendRedirect("/student_dashboard");
            } else if (role.equals("ROLE_PROFESSOR")) {
                LOGGER.info("Redirecting to professor dashboard.......");
                response.sendRedirect("/professor_dashboard");
            } else {
                LOGGER.info("Redirecting to index.......");
                response.sendRedirect("/auth/index");
            }
        } else {
            LOGGER.info("No role found.... Redirecting to index.......");
            response.sendRedirect("/auth/index");
        }
    }

}

