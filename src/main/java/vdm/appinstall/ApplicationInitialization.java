package vdm.appinstall;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import vdm.appinstall.configs.AppConfig;
import vdm.appinstall.configs.helpersConfig.SessionTimeout;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitialization implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext.addListener(new SessionTimeout(1*60));


        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();

        springContext.register(AppConfig.class);


        servletContext.addListener(new ContextLoaderListener(springContext));


        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "SpringDispatcher", new DispatcherServlet(springContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
