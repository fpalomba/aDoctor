package it.aDoctor.smellDetectionRules;

import it.aDoctor.beans.ClassBean;
import it.aDoctor.beans.MethodBean;

public class InefficientSQLQueryRule {

	public boolean isInefficientSQLQuery(ClassBean pClass) {

		for (String importString : pClass.getImports()) {
			if (importString.contains("java.sql")) {
				for (MethodBean method : pClass.getMethods()) {
					if (method.getTextContent().contains("Connection ")
							|| method.getTextContent().contains("Statement ")
							|| method.getTextContent().contains("ResultSet ")) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
