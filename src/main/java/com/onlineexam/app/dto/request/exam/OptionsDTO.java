package com.onlineexam.app.dto.request.exam;

public class OptionsDTO {

	private int key;
	private String option;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OptionsDTO [key=");
		builder.append(key);
		builder.append(", option=");
		builder.append(option);
		builder.append("]");
		return builder.toString();
	}

}
