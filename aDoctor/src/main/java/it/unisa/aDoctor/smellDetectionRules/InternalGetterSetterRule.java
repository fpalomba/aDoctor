package it.unisa.aDoctor.smellDetectionRules;

import it.unisa.aDoctor.beans.ClassBean;
import it.unisa.aDoctor.beans.MethodBean;
import it.unisa.aDoctor.beans.InstanceVariableBean;

public class InternalGetterSetterRule {

    public boolean isInternalGetterSetter(ClassBean pClass) {
        for (MethodBean method : pClass.getMethods()) {
            for (MethodBean call : method.getMethodCalls()) {
                if (isGetterSetterMethod(call, pClass)) {
                    if (isInternalMethod(call, pClass)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Questo metodo ritorna true se il metodo passato in input e' un
     * getter/setter di una delle variabili d'istanza
     *
     * @param pMethod Il metodo da controllare
     * @param pClass La classe a cui appartiene pMethod
     * @return boolean
     */
    private boolean isGetterSetterMethod(MethodBean pMethod, ClassBean pClass) {
        for (InstanceVariableBean var : pClass.getInstanceVariables()) {
            if (pMethod.getName().toLowerCase().equals("get" + var.getName())) {
                return true;
            } else if (pMethod.getName().toLowerCase().equals("set" + var.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Questo metodo ritorna true se il metodo passato in input e' un metodo
     * della classe
     *
     * @param pMethod Il metodo da controllare
     * @param pClass La classe a cui appartiene
     * @return boolean
     */
    private boolean isInternalMethod(MethodBean pMethod, ClassBean pClass) {
        for (MethodBean method : pClass.getMethods()) {
            if (pMethod.getName().equals(method.getName())) {
                return true;
            }
        }
        return false;
    }

}
