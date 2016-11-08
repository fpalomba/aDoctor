package it.aDoctor.smellDetectionRules;

import it.aDoctor.beans.ClassBean;

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
