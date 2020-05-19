package com.toumb.movielibrarymongo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.toumb.movielibrarymongo.MovielibrarymongoApplication;

public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return null;
	}
	// dispatcher configuration
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {MovielibrarymongoApplication.class};
	}

	// servlet-mapping configuration
	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}

}
