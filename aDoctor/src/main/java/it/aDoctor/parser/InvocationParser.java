package it.aDoctor.parser;

import it.aDoctor.beans.*;

public class InvocationParser {
	
	public static MethodBean parse(String pInvocationName) {
		MethodBean methodBean = new MethodBean();
		methodBean.setName(pInvocationName);
		return methodBean;
	}

}
