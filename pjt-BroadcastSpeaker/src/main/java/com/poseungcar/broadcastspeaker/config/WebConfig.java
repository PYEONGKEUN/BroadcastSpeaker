package com.poseungcar.broadcastspeaker.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	private static int MAX_FILE_ZIZE = 10 * 1024 * 1024;
	
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);	
		
		return new Filter[] {filter};
	}
	
	@Override
	   protected void customizeRegistration(Dynamic registration) {
	      MultipartConfigElement multipartConfig = new MultipartConfigElement("/tmp/upload", MAX_FILE_ZIZE, MAX_FILE_ZIZE, 0);
	      registration.setMultipartConfig(multipartConfig);
	   }

	
}
