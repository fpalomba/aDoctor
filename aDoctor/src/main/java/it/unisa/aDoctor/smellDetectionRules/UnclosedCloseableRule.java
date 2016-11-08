package it.unisa.aDoctor.smellDetectionRules;

import it.unisa.aDoctor.beans.MethodBean;
import it.unisa.aDoctor.beans.ClassBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UnclosedCloseableRule {

    private final List<String> closeableClassList;

    public UnclosedCloseableRule() throws IOException {

        // Recupero la lista delle classi che implementano Closeable
        closeableClassList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(NoLowMemoryResolverRule.class.getResourceAsStream("/unclosedCloseableClasses.txt")))) {
            while (br.ready()) {
                closeableClassList.add(br.readLine());
            }
        }
    }

    /**
     * Questo metodo ritorna true se dentro la classe c'è un closeable non
     * chiuso
     *
     * @param pClass
     * @return
     */
    public boolean isUnclosedCloseable(ClassBean pClass) {
        if (hasCloseableObject(pClass)) {
            Pattern regex = Pattern.compile("(.*)close(\\s*)()", Pattern.MULTILINE);
            Matcher regexMatcher = regex.matcher(pClass.getTextContent());
            if (!regexMatcher.find()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Questo metodo ritorna true se invidivua un oggetto Closeable dentro la
     * classe
     *
     * @param pClass
     * @return boolean
     */
    public boolean hasCloseableObject(ClassBean pClass) {
        for (MethodBean method : pClass.getMethods()) {
            for (String closeableObject : closeableClassList) {
                if (method.getTextContent().contains(closeableObject + " ")) {
                    //System.out.println(pClass.getName() + " -> " + closeableObject);
                    return true;
                }
            }
        }
        return false;
    }

}
