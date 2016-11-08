package it.unisa.aDoctor.smellDetectionRules;

import it.unisa.aDoctor.beans.ClassBean;

public class LeakingInnerClassRule {

    public boolean isLeakingInnerClass(ClassBean pClass) {
        for (ClassBean inner : pClass.getInnerClasses()) {
            if (!inner.isStatic()) {
                return true;
            }
        }
        return false;
    }

}
