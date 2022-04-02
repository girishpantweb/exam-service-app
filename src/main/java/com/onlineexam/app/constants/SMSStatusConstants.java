package com.onlineexam.app.constants;

public enum SMSStatusConstants {
	FAILED(0, "failed"), SENT(1, "sent");

	private int statusId;
	private String statusDescription;

	private SMSStatusConstants(int statusId, String statusDescription) {
		this.statusId = statusId;
		this.statusDescription = statusDescription;
	}

	public int getStatusId() {
		return statusId;
	}

	void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public int getStatusIdFromDescription(String statusDescription) {
		int statusId = 0;
		for (SMSStatusConstants sc : SMSStatusConstants.values()) {
			if (sc.getStatusDescription().equalsIgnoreCase(statusDescription)) {
				statusId = sc.getStatusId();
				break;
			}
		}
		return statusId;
	}
}