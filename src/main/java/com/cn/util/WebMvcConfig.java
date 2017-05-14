package com.cn.util;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		try {
			Properties props = PropertiesLoaderUtils.loadAllProperties("ViewControllerRegistry.properties");
			Enumeration<?> e = props.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				registry.addViewController(key).setViewName(props.getProperty(key));
			}
			registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		} catch (IOException e) {
			System.out.println("Error load ViewControllerRegistry.properties !!! Check the file.");
		}
	}

}
