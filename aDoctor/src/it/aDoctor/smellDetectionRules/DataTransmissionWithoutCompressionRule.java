package it.aDoctor.smellDetectionRules;

import java.io.IOException;

import it.aDoctor.beans.ClassBean;

public class DataTransmissionWithoutCompressionRule {

	public boolean isDataTransmissionWithoutCompression(ClassBean pClassBean) throws IOException {		
		
		if(pClassBean.getTextContent().contains("File ")) {
			if(! pClassBean.getTextContent().contains("zip")) {
				return true;
			}
		}
		
		return false;
	}
}
