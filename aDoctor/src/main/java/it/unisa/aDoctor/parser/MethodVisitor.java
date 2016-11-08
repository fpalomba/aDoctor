package it.unisa.aDoctor.parser;

import java.util.Collection;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class MethodVisitor extends ASTVisitor {

    private final Collection<MethodDeclaration> methodNodes;
    private boolean firstTime;

    public MethodVisitor(Collection<MethodDeclaration> pMethodNodes) {
        methodNodes = pMethodNodes;
        firstTime = true;
    }

    @Override
    public boolean visit(MethodDeclaration pMethodNode) {
        methodNodes.add(pMethodNode);
        return true;
    }

    @Override
    public boolean visit(TypeDeclaration pClassNode) {
        if (firstTime) {
            firstTime = false;
            return true;
        }
        return firstTime;
    }

}
