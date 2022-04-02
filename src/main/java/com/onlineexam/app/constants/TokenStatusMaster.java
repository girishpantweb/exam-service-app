package com.onlineexam.app.constants;

public enum TokenStatusMaster {

	DEACTIVE(0, "Deactive"), ACTIVE(1, "Active");

	private int statusId;
	private String statusDescription;

	private TokenStatusMaster(int statusId, String statusDescription) {
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
}
