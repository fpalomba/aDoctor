package it.aDoctor.smellDetectionRules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.aDoctor.beans.ClassBean;
import it.aDoctor.beans.MethodBean;

public class DurableWakeLockRule {

	public boolean isDurableWakeLock(ClassBean pClass) {

		Pattern regex = Pattern.compile("(.*)acquire(\\s*)()", Pattern.MULTILINE);
		for (MethodBean method : pClass.getMethods()) {
			Matcher regexMatcher = regex.matcher(method.getTextContent());
			if (regexMatcher.find()) {
			    return true;
			}
		}				
		return false;
		
	}

}
