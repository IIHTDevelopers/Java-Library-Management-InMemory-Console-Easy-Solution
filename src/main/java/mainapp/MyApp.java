package mainapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyApp {

	public static String directoryExists() {
		String gitDirectoryExists;
		try {
			gitDirectoryExists = executeCommand("git rev-parse --is-inside-work-tree").trim();
			if (gitDirectoryExists.equals("true")) {
				System.out.println("Git repository initialized successfully.");
				return "true";
			} else {
				System.out.println("Git repository not initialized.");
				return "false";
			}
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) {
		try {
			// Check if .git directory exists
//			String gitDirectoryExists = executeCommand("git rev-parse --is-inside-work-tree").trim();
			String gitDirectoryExists = directoryExists();
			if (gitDirectoryExists.equals("true")) {
				System.out.println("Git repository initialized successfully.");
//				yakshaAssert(currentTest(), true, businessTestFile);
			} else {
				System.out.println("Git repository not initialized.");
//				yakshaAssert(currentTest(), false, businessTestFile);
				return;
			}

//			// Check for commit (This checks if there's at least one commit)
//			String atleastOneCommit = executeCommand("git log --oneline").trim();
//			if (!atleastOneCommit.isEmpty()) {
//				System.out.println("Changes have been committed.");
//				yakshaAssert(currentTest(), true, businessTestFile);
//			} else {
//				System.out.println("No changes committed.");
//				yakshaAssert(currentTest(), false, businessTestFile);
//				return;
//			}
//
//			// Check if tmpbranch exists
//			String tempBranchExists = executeCommand("git branch --list tmpbranch");
//			if (tempBranchExists.contains("tmpbranch")) {
//				System.out.println("tmpbranch exists.");
//				yakshaAssert(currentTest(), true, businessTestFile);
//			} else {
//				System.out.println("tmpbranch does not exist.");
//				yakshaAssert(currentTest(), false, businessTestFile);
//				return;
//			}
//
//			// Check if tmpbranch has been merged into main
//			// Note: This is a simplistic check. It assumes if tmpbranch is not ahead of
//			// main, it's merged.
//			String mergeBase = executeCommand("git merge-base main tmpbranch").trim();
//			String tmpBranchLastCommit = executeCommand("git rev-parse tmpbranch").trim();
//			if (mergeBase.equals(tmpBranchLastCommit)) {
//				System.out.println("tmpbranch has been merged into main.");
//				yakshaAssert(currentTest(), true, businessTestFile);
//			} else {
//				System.out.println("tmpbranch has not been merged into main.");
//				yakshaAssert(currentTest(), false, businessTestFile);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String executeCommand(String command) throws Exception {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("bash", "-c", command);
		Process process = processBuilder.start();

		StringBuilder output = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line).append("\n");
		}

		int exitVal = process.waitFor();
		if (exitVal == 0) {
			return output.toString();
		} else {
			throw new RuntimeException("Failed to execute command: " + command);
		}
	}
}
