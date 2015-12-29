package com.xiaoxing.tuling;

public class ListData {

	public static final int SEND=1;
	public static final int RECEIVER=2;
	private int flag;
	private String content;
	public ListData(String content){
		
		this.content=content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}
}
