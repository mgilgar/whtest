package org.mgilgar.whtest;

import java.io.File;
import java.util.List;

public interface FileFinder {

	/**
	 * Returns a List of file names that are in directory or any of its sub-directories that are equal to fileName.
	 * 
	 * @param directory the directory where we start the search
	 * @param fileName the file name that should match the files we want to return.
	 * @return a list of file names that are in directory or any of its sub-directories and matches fileName. 
	 * If fileName is null or empty it returns or file names in the hierarchy starting at directory.
	 */
	List<String> find(final File directory, final String fileName);
	 
}
