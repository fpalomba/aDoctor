package it.aDoctor.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Vector;
import java.util.regex.Pattern;

public class FileUtilities {

	public static String readFile(String nomeFile) throws IOException {
		InputStream is = null;
		InputStreamReader isr = null;

		StringBuffer sb = new StringBuffer();
		char[] buf = new char[1024];
		int len;

		try {
			is = new FileInputStream(nomeFile);
			isr = new InputStreamReader(is);

			while ((len = isr.read(buf)) > 0)
				sb.append(buf, 0, len);

			return sb.toString();
		} finally {
			if (isr != null)
				isr.close();
		}
	}


	public static String getClassFromSrcMLstring(String srcMLstring, String start, String end){

		int countClass = 0;

		String toReturn = "";

		Pattern newLine = Pattern.compile("\n");
		String[] lines = newLine.split(srcMLstring);

		for(int i=0; i<lines.length; i++){
			if(lines[i].contains(start)){
				countClass++;
				toReturn+=lines[i];
			}
			if(lines[i].contains(end)){
				countClass--;
				toReturn+=lines[i];
				if(countClass == 0)
					return toReturn;

			} else {
				toReturn+=lines[i];
			}
		}

		return null;

	}


	public void copyDirectory(File srcPath, File dstPath) throws IOException {

		if (srcPath.isDirectory()) {

			if (!dstPath.exists()) {

				dstPath.mkdir();

			}

			String files[] = srcPath.list();

			for (int i = 0; i < files.length; i++) {
				copyDirectory(new File(srcPath, files[i]), new File(dstPath,
						files[i]));

			}

		}

		else {

			if (!srcPath.exists()) {

				System.out.println("File or directory does not exist.");

				System.exit(0);

			}

			else

			{

				InputStream in = new FileInputStream(srcPath);
				OutputStream out = new FileOutputStream(dstPath);
				// Transfer bytes from in to out
				byte[] buf = new byte[1024];

				int len;

				while ((len = in.read(buf)) > 0) {

					out.write(buf, 0, len);

				}

				in.close();

				out.close();

			}

		}

		System.out.println("Directory copied.");

	}

	public static boolean DelDir(File dir)
	{
		if (dir.isDirectory())
		{
			String[] contenuto = dir.list();
			for (int i=0; i<contenuto.length; i++)
			{
				boolean success = DelDir(new File(dir, contenuto[i]));
				if (!success) { return false; }
			}
		}
		return dir.delete();
	}

	public static void writeFile(String pContent, String pPath){
		File file=new File(pPath);
		FileWriter fstream;
		try {
			fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(pContent);
			out.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static Vector<File> listJavaFiles(File pDirectory) {
		Vector<File> javaFiles=new Vector<File>(); 
		File[] fList = pDirectory.listFiles();

		if(fList != null) {
			for (File file : fList) {
				if (file.isFile()) {
					if(file.getName().contains(".java")) {
						javaFiles.add(file);
					}
				} else if (file.isDirectory()) {
					File directory = new File(file.getAbsolutePath());
					javaFiles.addAll(listJavaFiles(directory));
				}
			}
		}
		return javaFiles;
	}

	public static Vector<File> listRepositoryDataFiles(File pDirectory) {
		Vector<File> gitRepoDataFiles=new Vector<File>(); 
		File[] fList = pDirectory.listFiles();

		if(fList != null) {
			for (File file : fList) {
				if (file.isFile()) {
					if(file.getName().contains(".data")) {
						gitRepoDataFiles.add(file);
					}
				} else if (file.isDirectory()) {
					File directory = new File(file.getAbsolutePath());
					gitRepoDataFiles.addAll(listRepositoryDataFiles(directory));
				}
			}
		}
		return gitRepoDataFiles;
	}

	public static Vector<File> listIssueFiles(File pDirectory) {
		Vector<File> gitRepoDataFiles=new Vector<File>(); 
		File[] fList = pDirectory.listFiles();

		if(fList != null) {
			for (File file : fList) {
				if (file.isFile()) {
					if(file.getName().contains("_issues")) {
						gitRepoDataFiles.add(file);
					}
				} else if (file.isDirectory()) {
					File directory = new File(file.getAbsolutePath());
					gitRepoDataFiles.addAll(listIssueFiles(directory));
				}
			}
		}
		return gitRepoDataFiles;
	}

	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!sourceFile.exists()) {
			return;
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		source = new FileInputStream(sourceFile).getChannel();
		destination = new FileOutputStream(destFile).getChannel();
		if (destination != null && source != null) {
			destination.transferFrom(source, 0, source.size());
		}
		if (source != null) {
			source.close();
		}
		if (destination != null) {
			destination.close();
		}

	}
}
