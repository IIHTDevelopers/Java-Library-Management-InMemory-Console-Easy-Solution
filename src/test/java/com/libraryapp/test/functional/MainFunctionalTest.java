package com.libraryapp.test.functional;

import static com.libraryapp.test.utils.TestUtils.businessTestFile;
import static com.libraryapp.test.utils.TestUtils.currentTest;
import static com.libraryapp.test.utils.TestUtils.testReport;
import static com.libraryapp.test.utils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainapp.MyApp;

public class MainFunctionalTest {
	private MyApp myApp;

	@BeforeEach
	public void setUp() {
		myApp = new MyApp();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testAddBook() throws IOException {
		try {
			String output = myApp.directoryExists();
			System.out.println(output);
			yakshaAssert(currentTest(), output.equals("true"), businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}
}
