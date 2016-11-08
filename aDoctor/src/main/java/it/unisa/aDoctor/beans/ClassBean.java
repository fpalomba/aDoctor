package it.unisa.aDoctor.beans;

import java.util.Collection;
import java.util.ArrayList;

public final class ClassBean {

    private String name;
    private Collection<InstanceVariableBean> instanceVariables;
    private Collection<MethodBean> methods;
    private Collection<String> imports;
    private Collection<ClassBean> innerClasses;
    private String textContent;
    private int LOC;
    private String superclass;
    private String belongingPackage;
    private String pathToClass;
    private int entityClassUsage;
    private boolean isStatic;

    public int getLOC() {
        return LOC;
    }

    public void setLOC(int lOC) {
        LOC = lOC;
    }

    public ClassBean() {
        name = null;
        instanceVariables = new ArrayList<>();
        methods = new ArrayList<>();
        setImports(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public Collection<InstanceVariableBean> getInstanceVariables() {
        return instanceVariables;
    }

    public void setInstanceVariables(Collection<InstanceVariableBean> pInstanceVariables) {
        instanceVariables = pInstanceVariables;
    }

    public void addInstanceVariables(InstanceVariableBean pInstanceVariable) {
        instanceVariables.add(pInstanceVariable);
    }

    public void removeInstanceVariables(InstanceVariableBean pInstanceVariable) {
        instanceVariables.remove(pInstanceVariable);
    }

    public Collection<MethodBean> getMethods() {
        return methods;
    }

    public void setMethods(Collection<MethodBean> pMethods) {
        methods = pMethods;
    }

    public void addMethod(MethodBean pMethod) {
        methods.add(pMethod);
    }

    public void removeMethod(MethodBean pMethod) {
        methods.remove(pMethod);
    }

    @Override
    public String toString() {
        return "name = " + name + "\n"
                + "instanceVariables = " + instanceVariables + "\n"
                + "methods = " + methods + "\n";
    }

    public int compareTo(Object pClassBean) {
        return this.getName().compareTo(((ClassBean) pClassBean).getName());
    }

    public Collection<String> getImports() {
        return imports;
    }

    public void setImports(Collection<String> imports) {
        this.imports = imports;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getSuperclass() {
        return superclass;
    }

    public void setSuperclass(String superclass) {
        this.superclass = superclass;
    }

    public String getBelongingPackage() {
        return belongingPackage;
    }

    public void setBelongingPackage(String belongingPackage) {
        this.belongingPackage = belongingPackage;
    }

    public int getEntityClassUsage() {
        return entityClassUsage;
    }

    public String getPathToClass() {
        return pathToClass;
    }

    public void setPathToClass(String pathToClass) {
        this.pathToClass = pathToClass;
    }

    public void setNumberOfGetterAndSetter(int entityClassUsage) {
        this.entityClassUsage = entityClassUsage;
    }

    public boolean equals(Object arg) {
        return this.getName().equals(((ClassBean) arg).getName())
                && this.getBelongingPackage().equals(((ClassBean) arg).getBelongingPackage());
    }

    public Collection<ClassBean> getInnerClasses() {
        return innerClasses;
    }

    public void setInnerClasses(Collection<ClassBean> innerClasses) {
        this.innerClasses = innerClasses;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

}
