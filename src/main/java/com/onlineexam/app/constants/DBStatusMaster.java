package com.onlineexam.app.constants;

public enum DBStatusMaster {

	DB_ACTIVE(1, "Active"), DB_DEACTIVE(0, "Deactive");

	private int statusId;
	private String statusDescription;

	private DBStatusMaster(int statusId, String statusDescription) {
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
