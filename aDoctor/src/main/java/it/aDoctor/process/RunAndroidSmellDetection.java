package it.aDoctor.process;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.core.runtime.CoreException;

import it.aDoctor.beans.*;
import it.aDoctor.smellDetectionRules.*;
import java.util.ArrayList;

public class RunAndroidSmellDetection {

    // The folder contains the set of Android apps that need to be analyzed
    private static final String PROJECTS_DIR = "/Users/fabiopalomba/Documents/PhD/Papers/Working/Antipatterns/impact-on-energy-consumption/projects/source/";

    public static void main(String[] args) throws IOException, CoreException {

        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        System.out.println("Started at " + ft.format(new Date()));

        // Folder containing android apps to analyze
        File experimentDirectory = FileUtils.getFile(PROJECTS_DIR);

        SlowLoopRule slowLoopRule = new SlowLoopRule();
        DataTransmissionWithoutCompressionRule dataTransmissionWithoutCompressionRule = new DataTransmissionWithoutCompressionRule();
        InefficientDataFormatAndParserRule inefficientDataFormatAndParserRule = new InefficientDataFormatAndParserRule();
        LeakingThreadRule leakingThreadRule = new LeakingThreadRule();
        NoLowMemoryResolverRule nlmrRule = new NoLowMemoryResolverRule();
        UnclosedCloseableRule unclRule = new UnclosedCloseableRule();
        DurableWakeLockRule wakeRule = new DurableWakeLockRule();

        for (File project : experimentDirectory.listFiles()) {

            // Method to convert a directory into a set of java packages.
            ArrayList<PackageBean> packages = FolderToJavaProjectConverter.convert(project.getAbsolutePath());

            for (PackageBean packageBean : packages) {

                for (ClassBean classBean : packageBean.getClasses()) {
                    System.out.println("-- Analyzing class: " + classBean.getBelongingPackage() + "." + classBean.getName());

                    if (slowLoopRule.isSlowLoop(classBean)) {
                        System.out.println("	Class affected by Slow Loop");
                    }

                    if (dataTransmissionWithoutCompressionRule.isDataTransmissionWithoutCompression(classBean)) {
                        System.out.println("	Class affected by Data Trasmission Without Compression");
                    }

                    if (inefficientDataFormatAndParserRule.isInefficientDataFormatAndParser(classBean)) {
                        System.out.println("	Class affected by Inefficient Data Format and Parser");
                    }

                    if (leakingThreadRule.isLeakingThread(classBean)) {
                        System.out.println("	Class affected by Leaking Thread");
                    }

                    if (nlmrRule.isNoLowMemoryResolver(classBean)) {
                        System.out.println("	Class affected by No Low Memory Resolver");
                    }

                    if (unclRule.isUnclosedCloseable(classBean)) {
                        System.out.println("	Class affected by Unclosed Closable");
                    }

                    if (wakeRule.isDurableWakeLock(classBean)) {
                        System.out.println("	Class affected by Durable Wakelock");
                    }
                }

            }
        }

        System.out.println("Finished at " + ft.format(new Date()));

    }

    public static File getAndroidManifest(File dir) {
        File androidManifest = null;
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            if (file.getName().equals("AndroidManifest.xml")) {
                androidManifest = file;
            }
        }
        return androidManifest;
    }

}
