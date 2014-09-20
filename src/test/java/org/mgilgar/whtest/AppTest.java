package org.mgilgar.whtest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AppTest {
	
	@Test
	public void mainWithoutParametersShouldWork() {
		App.main(new String[]{});
		assertTrue(true);
	}
	
	@Test
	public void mainWith2ParametersShouldWork() {
		String[] args = {"-f", "pom.xml"};
		App.main(args);
		assertTrue(true);
	}
	
	@Test
	public void mainWith3ParametersShouldWork() {
		String[] args = {"-f", "pom.xml", "."};
		App.main(args);
		assertTrue(true);
	}
}
