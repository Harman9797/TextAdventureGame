package mains;

public class UserInput {

	private String verb;
	private String object;
	
	public UserInput() {
		
	}

	public UserInput(String verb, String object) {
		super();
		this.verb = verb;
		this.object = object;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "UserInput [verb=" + verb + ", object=" + object + "]";
	}
	
	
}
