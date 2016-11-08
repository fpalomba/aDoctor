package it.unisa.aDoctor.smellDetectionRules;

import it.unisa.aDoctor.beans.ClassBean;
import it.unisa.aDoctor.beans.MethodBean;

public class InefficientDataFormatAndParserRule {

    public boolean isInefficientDataFormatAndParser(ClassBean pClass) {

        for (String importedResource : pClass.getImports()) {
            if (importedResource.equals("javax.xml.parsers.DocumentBuilder")) {
                return true;
            }
        }

        for (MethodBean methodBean : pClass.getMethods()) {
            if (methodBean.getTextContent().contains("DocumentBuilderFactory")
                    || methodBean.getTextContent().contains("DocumentBuilder")
                    || methodBean.getTextContent().contains("NodeList")) {
                return true;
            }
        }

        return false;

    }
}
