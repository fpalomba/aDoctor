package it.unisa.aDoctor.ui;

import java.util.List;

/**
 *
 * @author dardin88
 */
public class SmellData {

    private final String className;
    private final List<String> values;

    public SmellData(String className, List<String> values) {
        this.className = className;
        this.values = values;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getValues() {
        return values;
    }

}
