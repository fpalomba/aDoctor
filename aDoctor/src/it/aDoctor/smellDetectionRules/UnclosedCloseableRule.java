package it.aDoctor.smellDetectionRules;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import it.aDoctor.beans.*;

public class UnclosedCloseableRule {

	private List<String> closeableClassList;

	public UnclosedCloseableRule() throws IOException {
		
		// Recupero la lista delle classi che implementano Closeable
		closeableClassList = new ArrayList<String>();
		File classList = FileUtils.getFile(System.getProperty("user.dir")+"/src/files/unclosedCloseableClasses.txt");
		LineIterator iter = FileUtils.lineIterator(classList);
		while (iter.hasNext()) {
			closeableClassList.add(iter.next());
		}
		iter.close();
	}
	
	/**
	 * Questo metodo ritorna true se dentro la classe c'è un closeable non chiuso
	 * @param pClass
	 * @return
	 */
	public boolean isUnclosedCloseable(ClassBean pClass) {
		if (hasCloseableObject(pClass)) {
			Pattern regex = Pattern.compile("(.*)close(\\s*)()", Pattern.MULTILINE);
			Matcher regexMatcher = regex.matcher(pClass.getTextContent());
			if (!regexMatcher.find()) {
			    return true;
			}	
		}
		return false;
	}
	
	/**
	 * Questo metodo ritorna true se invidivua un oggetto Closeable dentro la classe
	 * @param pClass
	 * @return boolean
	 */
	public boolean hasCloseableObject(ClassBean pClass) {		
		for (MethodBean method : pClass.getMethods()) {
			for (String closeableObject : closeableClassList) {
				if (method.getTextContent().contains(closeableObject+" ")) {
					//System.out.println(pClass.getName() + " -> " + closeableObject);
					return true;
				}				
			}			
		}		
		return false;
	}
	
	

}
