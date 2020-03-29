/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author rancha
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String TMP_FOLDER = "C:\\Users\\rancha\\Documents\\NetBeansProjects\\MusicAlbumShop\\src\\main\\webapp\\resources\\images";
    private static final int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext rootContext
                = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfig.class);
        rootContext.register(SecurityConfig.class);
        rootContext.register(MailConfig.class);
        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext dispatcherWebContext
                = new AnnotationConfigWebApplicationContext();
        dispatcherWebContext.register(WebConfig.class);

        ServletRegistration.Dynamic dispatcher
                = container.addServlet("mvc", new DispatcherServlet(dispatcherWebContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);

        dispatcher.setMultipartConfig(multipartConfigElement);
    }
}
