package org.mgilgar.whtest;

import java.io.File;

public class App {

	FileFinder fileFinder = new FileFinderImpl();
	
	public static void main(String[] args) {
		if (args.length < 3) {
			System.err.println("There are not enough arguments");
			return;
		}
		App app = new App();
		for (String fileName: app.fileFinder.find(new File(args[2]), args[1])) {
			System.out.println(fileName);
		}
	}

}
