package org.mgilgar.whtest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinderImpl implements FileFinder {

	public List<String> find(final File directory, final String fileName) {
		List<String> list = new ArrayList<String>();
		if (directory == null || directory.getPath().isEmpty()) return list;
		for (File file: directory.listFiles()) {
			if (fileName == null || fileName.isEmpty() || file.getName().equals(fileName)) {
				list.add(file.getPath());
			}
			if (file.isDirectory()) {
				list.addAll(this.find(file, fileName));
			}
		}
		return list;
	}

}
