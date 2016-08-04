package it.aDoctor.beans;

public class InstanceVariableBean {
	
	private String visibility;
	private String type;
	private String name;
	private String initialization;
	
	public InstanceVariableBean() {
		visibility = null;
		type = null;
		name = null;
		initialization = null;
	}
	
	public String getVisibility() {
		return visibility;
	}
	
	public void setVisibility(String pVisibility) {
		visibility = pVisibility;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String pType) {
		type = pType;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String pName) {
		name = pName;
	}
	
	public String getInitialization() {
		return initialization;
	}
	
	public void setInitialization(String pInitialization) {
		initialization = pInitialization;
	}
	
	public String toString() {
		return "(" + visibility + "|" + type + "|" + name + "|" + initialization + ")";
	}
	
	public boolean equals(Object arg){
		return this.getName().equals(((InstanceVariableBean)arg).getName());
	}
	
}
