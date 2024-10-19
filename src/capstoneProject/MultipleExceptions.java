package capstoneProject;

import java.util.ArrayList;
import java.util.List;

public class MultipleExceptions extends Exception {
	private List<Exception> exceptions;
	
	public MultipleExceptions() {
		super("Multiple exceptions happened");
		this.exceptions = new ArrayList();
	}
	
	public MultipleExceptions(String text) {
		super(text);
		this.exceptions = new ArrayList();
	}
	
	public void addException(Exception e) {
		exceptions.add(e);
	}
	
	public List<Exception> getExceptions(){
		return exceptions;
	}
}
