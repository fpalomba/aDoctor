package it.unisa.aDoctor.smellDetectionRules;

import it.unisa.aDoctor.beans.ClassBean;
import it.unisa.aDoctor.beans.MethodBean;

public class RigidAlarmManagerRule {

    public boolean isRigidAlarmManager(ClassBean pClass) {
        for (MethodBean method : pClass.getMethods()) {
            if (method.getTextContent().contains("AlarmManager")) {
                for (MethodBean call : method.getMethodCalls()) {
                    if (call.getName().equals("setRepeating")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
