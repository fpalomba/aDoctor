package it.unisa.aDoctor.parser;

import it.unisa.aDoctor.beans.InstanceVariableBean;
import java.util.Iterator;

import org.eclipse.jdt.core.dom.FieldDeclaration;

public class InstanceVariableParser {

    public static InstanceVariableBean parse(FieldDeclaration pInstanceVariableNode) {

        // Instantiate the bean
        InstanceVariableBean instanceVariableBean = new InstanceVariableBean();

        // Set the visibility
        String visibility = getVisibilityModifier(pInstanceVariableNode);
        if (visibility != null) {
            instanceVariableBean.setVisibility(visibility);
        } else {
            instanceVariableBean.setVisibility("default");
        }

        // Set the type
        instanceVariableBean.setType(pInstanceVariableNode.getType().toString());

        // Set the name
        String[] fragments = pInstanceVariableNode.fragments().get(0).toString().split("=");
        instanceVariableBean.setName(fragments[0]);

        // Set the initialization
        if (fragments.length == 1) {
            instanceVariableBean.setInitialization(null);
        } else {
            instanceVariableBean.setInitialization(fragments[1]);
        }

        // Return the bean
        return instanceVariableBean;

    }

    @SuppressWarnings("unchecked")
    private static String getVisibilityModifier(FieldDeclaration pInstanceVariableNode) {

        Iterator<Object> it = pInstanceVariableNode.modifiers().iterator();

        // Find the visibility in the modifiers
        while (it.hasNext()) {
            String modifier = it.next().toString();
            switch (modifier) {
                case "private":
                    return "private";
                case "protected":
                    return "protected";
                case "public":
                    return "public";
                default:
                    break;
            }
        }

        // No visibility found
        return null;

    }

}
