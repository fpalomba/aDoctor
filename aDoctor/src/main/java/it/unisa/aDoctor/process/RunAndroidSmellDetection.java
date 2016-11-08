package it.unisa.aDoctor.process;

import it.unisa.aDoctor.smellDetectionRules.DurableWakeLockRule;
import it.unisa.aDoctor.smellDetectionRules.InefficientDataFormatAndParserRule;
import it.unisa.aDoctor.smellDetectionRules.SlowLoopRule;
import it.unisa.aDoctor.smellDetectionRules.NoLowMemoryResolverRule;
import it.unisa.aDoctor.smellDetectionRules.MemberIgnoringMethodRule;
import it.unisa.aDoctor.smellDetectionRules.InefficientDataStructureRule;
import it.unisa.aDoctor.smellDetectionRules.InternalGetterSetterRule;
import it.unisa.aDoctor.smellDetectionRules.DataTransmissionWithoutCompressionRule;
import it.unisa.aDoctor.smellDetectionRules.DebuggableReleaseRule;
import it.unisa.aDoctor.smellDetectionRules.InefficientSQLQueryRule;
import it.unisa.aDoctor.smellDetectionRules.LeakingInnerClassRule;
import it.unisa.aDoctor.smellDetectionRules.RigidAlarmManagerRule;
import it.unisa.aDoctor.smellDetectionRules.PublicDataRule;
import it.unisa.aDoctor.smellDetectionRules.LeakingThreadRule;
import it.unisa.aDoctor.smellDetectionRules.UnclosedCloseableRule;
import it.unisa.aDoctor.beans.ClassBean;
import it.unisa.aDoctor.beans.PackageBean;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.core.runtime.CoreException;

import java.io.FileWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang.StringUtils;

public class RunAndroidSmellDetection {

    private static final String NEW_LINE_SEPARATOR = "\n";
    public static String[] FILE_HEADER;

    // The folder contains the set of Android apps that need to be analyzed
    public static void main(String[] args) throws IOException, CoreException {

        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        System.out.println("Started at " + ft.format(new Date()));

        // Folder containing android apps to analyze
        File experimentDirectory = FileUtils.getFile(args[0]);
        File fileName = new File(args[1]);
        String smellsNeeded = args[2];

        FILE_HEADER = new String[StringUtils.countMatches(smellsNeeded, "1") + 1];

        DataTransmissionWithoutCompressionRule dataTransmissionWithoutCompressionRule = new DataTransmissionWithoutCompressionRule();
        DebuggableReleaseRule debbugableReleaseRule = new DebuggableReleaseRule();
        DurableWakeLockRule durableWakeLockRule = new DurableWakeLockRule();
        InefficientDataFormatAndParserRule inefficientDataFormatAndParserRule = new InefficientDataFormatAndParserRule();
        InefficientDataStructureRule inefficientDataStructureRule = new InefficientDataStructureRule();
        InefficientSQLQueryRule inefficientSQLQueryRule = new InefficientSQLQueryRule();
        InternalGetterSetterRule internaleGetterSetterRule = new InternalGetterSetterRule();
        LeakingInnerClassRule leakingInnerClassRule = new LeakingInnerClassRule();
        LeakingThreadRule leakingThreadRule = new LeakingThreadRule();
        MemberIgnoringMethodRule memberIgnoringMethodRule = new MemberIgnoringMethodRule();
        NoLowMemoryResolverRule noLowMemoryResolverRule = new NoLowMemoryResolverRule();
        PublicDataRule publicDataRule = new PublicDataRule();
        RigidAlarmManagerRule rigidAlarmManagerRule = new RigidAlarmManagerRule();
        SlowLoopRule slowLoopRule = new SlowLoopRule();
        UnclosedCloseableRule unclosedCloseableRule = new UnclosedCloseableRule();

        String[] smellsType = {"DTWC", "DR", "DW", "IDFP", "IDS", "ISQLQ", "IGS", "LIC", "LT", "MIM", "NLMR", "PD", "RAM", "SL", "UC"};

        FILE_HEADER[0] = "Class";

        int headerCounter = 1;

        for (int i = 0; i < smellsNeeded.length(); i++) {
            if (smellsNeeded.charAt(i) == '1') {
                FILE_HEADER[headerCounter] = smellsType[i];
                headerCounter++;
            }
        }

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        FileWriter fileWriter = new FileWriter(fileName);
        try (CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat)) {
            csvFilePrinter.printRecord((Object[]) FILE_HEADER);

            for (File project : experimentDirectory.listFiles()) {

                if (!project.isHidden()) {

                    // Method to convert a directory into a set of java packages.
                    ArrayList<PackageBean> packages = FolderToJavaProjectConverter.convert(project.getAbsolutePath());

                    for (PackageBean packageBean : packages) {

                        for (ClassBean classBean : packageBean.getClasses()) {

                            List record = new ArrayList();

                            System.out.println("-- Analyzing class: " + classBean.getBelongingPackage() + "." + classBean.getName());

                            record.add(classBean.getBelongingPackage() + "." + classBean.getName());

                            if (smellsNeeded.charAt(0) == '1') {
                                if (dataTransmissionWithoutCompressionRule.isDataTransmissionWithoutCompression(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(1) == '1') {
                                if (debbugableReleaseRule.isDebuggableRelease(RunAndroidSmellDetection.getAndroidManifest(project))) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(2) == '1') {
                                if (durableWakeLockRule.isDurableWakeLock(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(3) == '1') {
                                if (inefficientDataFormatAndParserRule.isInefficientDataFormatAndParser(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(4) == '1') {
                                if (inefficientDataStructureRule.isInefficientDataStructure(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(5) == '1') {
                                if (inefficientSQLQueryRule.isInefficientSQLQuery(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(6) == '1') {
                                if (internaleGetterSetterRule.isInternalGetterSetter(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(7) == '1') {
                                if (leakingInnerClassRule.isLeakingInnerClass(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(8) == '1') {
                                if (leakingThreadRule.isLeakingThread(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(9) == '1') {
                                if (memberIgnoringMethodRule.isMemberIgnoringMethod(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(10) == '1') {
                                if (noLowMemoryResolverRule.isNoLowMemoryResolver(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(11) == '1') {
                                if (publicDataRule.isPublicData(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(12) == '1') {
                                if (rigidAlarmManagerRule.isRigidAlarmManager(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(13) == '1') {
                                if (slowLoopRule.isSlowLoop(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }

                            if (smellsNeeded.charAt(14) == '1') {
                                if (unclosedCloseableRule.isUnclosedCloseable(classBean)) {
                                    record.add("1");
                                } else {
                                    record.add("0");
                                }
                            }
                            csvFilePrinter.printRecord(record);
                        }
                    }
                }
            }
        }
        System.out.println("CSV file was created successfully!");
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
