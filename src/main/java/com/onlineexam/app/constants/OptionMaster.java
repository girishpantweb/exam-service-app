package com.onlineexam.app.constants;

public enum OptionMaster {

	OPTION1(1, "Option1"), OPTION2(2, "Option2"), OPTION3(3, "Option3"), OPTION4(4, "Option4");

	private int id;
	private String value;

	private OptionMaster(int id, String value) {
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
