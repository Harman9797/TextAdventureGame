package mains;

public class UserResponse {

	boolean showNPCDetails;
	String response;
	
	public UserResponse() {
		
	}

	public UserResponse(boolean showNPCDetails, String response) {
		super();
		this.showNPCDetails = showNPCDetails;
		this.response = response;
	}

	public boolean isShowNPCDetails() {
		return showNPCDetails;
	}

	public void setShowNPCDetails(boolean showNPCDetails) {
		this.showNPCDetails = showNPCDetails;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "UserResponse [showNPCDetails=" + showNPCDetails + ", response=" + response + "]";
	}
	
	
}
