package com.onlineexam.app.constants;

public enum DifficultyMaster {

	LOW(1, "Low"), MEDIUM(2, "Medium"), HIGH(3, "High");

	private int id;
	private String value;

	private DifficultyMaster(int id, String value) {
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static DifficultyMaster getDicultyFromId(int id) {
		for (DifficultyMaster df : DifficultyMaster.values()) {
			if (df.getId() == id) {
				return df;
			}
		}
		return null;
	}

}
