package org.mgilgar.whtest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileFinderImplTest {
	
	private static final String EMPTY_FILENAME = "";
	private static final String NOT_FOUND_FILENAME = "jfksjfksjlfd";
	private static final String FOUND_FILENAME = "pom.xml";
	
	
	public static final String FILENAME1 = ".classpath";
	public static final String FILENAME2 = ".project";
	public static final String FILENAME3 = ".settings";
	public static final String FILENAME4 = FOUND_FILENAME;
	public static final String FILENAME5 = "README.txt";
	public static final String FILENAME6 = "src";
	public static final String FILENAME7 = "target";
	public static final String FILENAME8 = "main";
	public static final String FILENAME9 = "test";
	public static final String FILENAME10 = "myTest.java";
	public static final String FILENAME11 = FOUND_FILENAME;
	
	public static final String FILEPATH1 = "mgilgar/.classpath";
	public static final String FILEPATH2 = "mgilgar/.project";
	public static final String FILEPATH3 = "mgilgar/.settings";
	public static final String FILEPATH4 = "mgilgar/pom.xml";
	public static final String FILEPATH5 = "mgilgar/README.txt";
	public static final String FILEPATH6 = "mgilgar/src";
	public static final String FILEPATH7 = "mgilgar/target";
	public static final String FILEPATH8 = "mgilgar/src/main";
	public static final String FILEPATH9 = "mgilgar/src/test";
	public static final String FILEPATH10 = "mgilgar/test/myTest.java";
	public static final String FILEPATH11 = "mgilgar/test/pom.xml";
	
	public static final String START_DIRECTORY_FILEPATH = "mgilgar";

	FileFinder fileFinder;
	FilenameFilter fileNameFilter;
	
	@Mock
	File file1, file2, file3, file4, file5, file6, file7, file8, file9, file10, file11;

	@Mock
	File startDirectory;

	@Before
	public void setUp() {
		fileFinder = new FileFinderImpl();
		
		when(file1.getPath()).thenReturn(FILEPATH1);
		when(file1.getName()).thenReturn(FILENAME1);
		when(file1.isDirectory()).thenReturn(false);
		when(file2.getPath()).thenReturn(FILEPATH2);
		when(file2.getName()).thenReturn(FILENAME2);
		when(file2.isDirectory()).thenReturn(false);
		when(file3.getPath()).thenReturn(FILEPATH3);
		when(file3.getName()).thenReturn(FILENAME3);
		when(file3.isDirectory()).thenReturn(false);
		when(file4.getPath()).thenReturn(FILEPATH4);
		when(file4.getName()).thenReturn(FILENAME4);
		when(file4.isDirectory()).thenReturn(false);
		when(file5.getPath()).thenReturn(FILEPATH5);
		when(file5.getName()).thenReturn(FILENAME5);
		when(file5.isDirectory()).thenReturn(false);
		when(file6.getPath()).thenReturn(FILEPATH6);
		when(file6.getName()).thenReturn(FILENAME6);
		when(file6.isDirectory()).thenReturn(true);
		when(file7.getPath()).thenReturn(FILEPATH7);
		when(file7.getName()).thenReturn(FILENAME7);
		when(file7.isDirectory()).thenReturn(true);
		when(file8.getPath()).thenReturn(FILEPATH8);
		when(file8.getName()).thenReturn(FILENAME8);
		when(file8.isDirectory()).thenReturn(true);
		when(file9.getPath()).thenReturn(FILEPATH9);
		when(file9.getName()).thenReturn(FILENAME9);
		when(file9.isDirectory()).thenReturn(true);
		when(file10.getPath()).thenReturn(FILEPATH10);
		when(file10.getName()).thenReturn(FILENAME10);
		when(file10.isDirectory()).thenReturn(false);
		when(file11.getPath()).thenReturn(FILEPATH11);
		when(file11.getName()).thenReturn(FILENAME11);
		when(file11.isDirectory()).thenReturn(false);
		
		when(startDirectory.getPath()).thenReturn(START_DIRECTORY_FILEPATH);
		when(startDirectory.isDirectory()).thenReturn(true);
		when(startDirectory.listFiles()).thenReturn(new File[]{file1, file2, file3, file4, file5, file6, file7});
		
		when(file6.listFiles()).thenReturn(new File[]{file8, file9});
		when(file7.listFiles()).thenReturn(new File[]{});
		when(file8.listFiles()).thenReturn(new File[]{});
		when(file9.listFiles()).thenReturn(new File[]{file10, file11});

	}

	@Test
	public void findShouldReturnEmptyListWhenNull() {
		assertEquals(fileFinder.find(null, EMPTY_FILENAME).size(), 0);
	}

	@Test
	public void findShouldReturnEmptyListWhenEmptyString() {
		assertEquals(fileFinder.find(new File(""), EMPTY_FILENAME).size(), 0);
	}
	
	@Test
	public void findShouldReturnAListOfFilePaths() {
		List<String> list = fileFinder.find(startDirectory, EMPTY_FILENAME);
		assertTrue(list != null);
	}

	@Test
	public void findShouldReturnSubdirectoryContentsRecursively() {
		List<String> list = fileFinder.find(startDirectory, EMPTY_FILENAME);
		assertTrue(list != null);
		assertTrue(list.size() == 11);
	}

	@Test
	public void findShouldReturnFileFullRelativePath() {
		List<String> list = fileFinder.find(startDirectory, EMPTY_FILENAME);
		assertTrue(list != null);
		for (String filePath: list) {
			assert(filePath.startsWith(startDirectory.getPath()));
		}
	}

	@Test
	public void findShouldReturnAFilteredResultIfThereIsAFileName() {
		List<String> list = fileFinder.find(startDirectory, FOUND_FILENAME);
		assertTrue(list != null);
		assertEquals(list.size(), 2);
	}

	@Test
	public void findShouldReturnAFilteredResultIfThereIsAFileNameButIsNotFound() {
		List<String> list = fileFinder.find(startDirectory, NOT_FOUND_FILENAME);
		assertTrue(list != null);
		assertEquals(list.size(), 0);
	}
}
