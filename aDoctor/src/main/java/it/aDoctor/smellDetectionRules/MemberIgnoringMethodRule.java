package it.aDoctor.smellDetectionRules;

import it.aDoctor.beans.ClassBean;
import it.aDoctor.beans.MethodBean;

public class MemberIgnoringMethodRule {

    public boolean isMemberIgnoringMethod(ClassBean pClass) {

        for (MethodBean methodBean : pClass.getMethods()) {
            if (!methodBean.isStatic()) {
                if (pClass.getInstanceVariables().size() > 0) {
                    if (methodBean.getUsedInstanceVariables().isEmpty()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
