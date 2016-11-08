package it.unisa.aDoctor.parser;

import java.util.Collection;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class InvocationVisitor extends ASTVisitor {

    private final Collection<String> invocations;

    public InvocationVisitor(Collection<String> pInvocations) {
        invocations = pInvocations;
    }

    @Override
    public boolean visit(MethodInvocation pInvocationNode) {
        invocations.add(pInvocationNode.getName().toString());
        return true;
    }

    @Override
    public boolean visit(TypeDeclaration pClassNode) {
        return false;
    }

    @SuppressWarnings("unused")
    private boolean isLocalInvocation(MethodInvocation pInvocationNode) {

        // Get the important data of the invocation
        String invocation = pInvocationNode.toString();
        String invocationName = pInvocationNode.getName().toString();
        int index = invocation.indexOf(invocationName);

        // The invocation has not a qualifier
        if (index == 0) {
            return true;
        } // The invocation has this as qualifier
        else return index >= 5 && invocation.substring(index - 5, index - 1).equals("this"); // The invocation has some other qualifier

    }

}
