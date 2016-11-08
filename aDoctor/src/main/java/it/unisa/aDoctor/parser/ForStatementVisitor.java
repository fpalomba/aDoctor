package it.unisa.aDoctor.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ForStatement;

public class ForStatementVisitor extends ASTVisitor {

    private final List<ForStatement> forStatements = new ArrayList<>();

    @Override
    public boolean visit(ForStatement node) {
        forStatements.add(node);

        return super.visit(node);
    }

    /**
     * This method allows to get all the Blocks for the Class on which it is;
     *
     * @return a List of all the for statements;
     */
    public Collection<ForStatement> getForStatements() {
        return forStatements;
    }
}
