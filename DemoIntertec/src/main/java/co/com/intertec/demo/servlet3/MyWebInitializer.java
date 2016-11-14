package co.com.intertec.demo.servlet3;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import co.com.intertec.demo.config.SpringWebConfig;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { SpringWebConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

}
