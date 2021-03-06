package controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jobs.JenaOwlReaderConfiguration;
import models.ontologyReading.OntologyReader;
import models.ontologyReading.jena.JenaOwlReaderConfig;
import play.mvc.Controller;

public class OntologyController extends Controller {

	private static OntologyReader ontologyReader;

	protected static OntologyReader getOntologyReader() {
		if(ontologyReader == null){
			
			ontologyReader = OntologyReader.getGlobalInstance();
		}
		
		return ontologyReader;
	}
    
    protected static class HtmlHolder{
    	public play.twirl.api.Html value;
    }
	
	protected static play.twirl.api.Html renderTemplateByName(String templateName, Object... args){
		try {
			
			Class[] parameterTypes = new Class[args.length];
			
			for (int i = 0; i < args.length; i++) {
				parameterTypes[i] = args[i].getClass();
			}
			
			Method method = Class.forName("views.html." + templateName)
					.getMethod("render", parameterTypes);

			play.twirl.api.Html html = (play.twirl.api.Html) method.invoke(null, args);
			return html;
			
		} catch (SecurityException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

	public OntologyController() {
		super();
	}

}