package it.aDoctor.smellDetectionRules;

import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;

import it.aDoctor.beans.ClassBean;
import it.aDoctor.parser.CodeParser;
import it.aDoctor.parser.ForStatementVisitor;

public class SlowLoopRule {

	public boolean isSlowLoop(ClassBean pClassBean) throws IOException {		
		CodeParser parser = new CodeParser();
		CompilationUnit compilationUnit = parser.createParser(pClassBean.getTextContent());
		ForStatementVisitor forVisitor = new ForStatementVisitor();
		
		compilationUnit.accept(forVisitor);
		
		if(! forVisitor.getForStatements().isEmpty())
			return true;
		
		return false;
	}
}
