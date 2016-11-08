package it.unisa.aDoctor.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class InnerClassVisitor extends ASTVisitor {

    private final Collection<TypeDeclaration> innerClasses;

    public InnerClassVisitor() {
        this.innerClasses = new ArrayList<>();
    }

    @Override
    public boolean visit(TypeDeclaration typeDeclarationStatement) {

        if (!typeDeclarationStatement.isPackageMemberTypeDeclaration()) {
            innerClasses.add(typeDeclarationStatement);
        }

        return true;
    }

    public Collection<TypeDeclaration> getInnerClasses() {
        return this.innerClasses;
    }
}
