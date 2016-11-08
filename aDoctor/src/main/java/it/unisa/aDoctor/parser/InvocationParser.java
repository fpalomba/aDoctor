package it.unisa.aDoctor.parser;

import it.unisa.aDoctor.beans.MethodBean;

public class InvocationParser {

    public static MethodBean parse(String pInvocationName) {
        MethodBean methodBean = new MethodBean();
        methodBean.setName(pInvocationName);
        return methodBean;
    }

}
