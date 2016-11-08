package it.unisa.aDoctor.parser;

import it.unisa.aDoctor.beans.MethodBean;
import it.unisa.aDoctor.beans.InstanceVariableBean;

import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodParser {

    @SuppressWarnings("unchecked")
    public static MethodBean parse(MethodDeclaration pMethodNode, Collection<InstanceVariableBean> pClassInstanceVariableBeans) {

        // Instantiate the bean
        MethodBean methodBean = new MethodBean();

        // Set the name
        methodBean.setName(pMethodNode.getName().toString());

        methodBean.setParameters(pMethodNode.parameters());

        methodBean.setReturnType(pMethodNode.getReturnType2());

        // Set the textual content
        methodBean.setTextContent(pMethodNode.toString());

        if ((pMethodNode.toString().contains("static "))) {
            methodBean.setStatic(true);
        } else {
            methodBean.setStatic(false);
        }

        // Get the names in the method
        Collection<String> names = new HashSet<>();
        pMethodNode.accept(new NameVisitor(names));

        // Verify the correspondence between names and instance variables 
        Collection<InstanceVariableBean> usedInstanceVariableBeans = getUsedInstanceVariable(names, pClassInstanceVariableBeans);

        // Set the used instance variables
        methodBean.setUsedInstanceVariables(usedInstanceVariableBeans);

        // Get the invocation names
        Collection<String> invocations = new HashSet<>();
        pMethodNode.accept(new InvocationVisitor(invocations));

        // Get the invocation beans from the invocation names
        Collection<MethodBean> invocationBeans = new ArrayList<>();
        for (String invocation : invocations) {
            invocationBeans.add(InvocationParser.parse(invocation));
        }

        // Set the invocations
        methodBean.setMethodCalls(invocationBeans);

        // Return the bean
        return methodBean;

    }

    private static Collection<InstanceVariableBean> getUsedInstanceVariable(Collection<String> pNames, Collection<InstanceVariableBean> pClassInstanceVariableBeans) {

        // Instantiate the collection to return
        Collection<InstanceVariableBean> usedInstanceVariableBeans = new ArrayList<>();

        // Iterate over the instance variables defined in the class
        for (InstanceVariableBean classInstanceVariableBean : pClassInstanceVariableBeans) // If there is a correspondence, add to the returned collection
        {
            if (pNames.remove(classInstanceVariableBean.getName())) {
                usedInstanceVariableBeans.add(classInstanceVariableBean);
            }
        }

        // Return the collection
        return usedInstanceVariableBeans;

    }

}
