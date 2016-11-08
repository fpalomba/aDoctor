package it.unisa.aDoctor.smellDetectionRules;

import it.unisa.aDoctor.beans.ClassBean;

public class LeakingThreadRule {

    public boolean isLeakingThread(ClassBean pClass) {

        if (pClass.getTextContent().contains("run()")) {
            if (!pClass.getTextContent().contains("stop()")) {
                return true;
            }
        }

        return false;
    }
}
