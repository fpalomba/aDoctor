package it.unisa.aDoctor.parser;

import it.unisa.aDoctor.beans.PackageBean;
import it.unisa.aDoctor.beans.ClassBean;
import java.util.ArrayList;


import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class PackageParser {

    public static PackageBean parse(IPackageFragment pPackage) throws JavaModelException {
        PackageBean packageBean = new PackageBean();
        CodeParser codeParser = new CodeParser();
        String textualContent = "";

        ArrayList<ClassBean> classes = new ArrayList<>();

        packageBean.setName(pPackage.getElementName());

        for (ICompilationUnit cu : pPackage.getCompilationUnits()) {

            textualContent += cu.getSource();

            CompilationUnit parsed = codeParser.createParser(cu.getSource());
            TypeDeclaration typeDeclaration = (TypeDeclaration) parsed.types().get(0);

            ArrayList<String> imported = new ArrayList<>();

            for (IImportDeclaration importedResource : cu.getImports()) {
                imported.add(importedResource.getElementName());
            }

            classes.add(ClassParser.parse(typeDeclaration, packageBean.getName(), imported));
        }

        packageBean.setTextContent(textualContent);

        return packageBean;
    }
}
