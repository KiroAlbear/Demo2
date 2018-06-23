// IScreenshotProvider.aidl
package com.example.admin.demo;

// Declare any non-default types here with import statements

// Interface for fetching screenshots
interface IScreenshotProvider {
	// Checks whether the native background application is running
	// (and thus whether the screenshots are available)
	boolean isAvailable();

	// Create a screen snapshot and returns path to file where it is written.
	String takeScreenshot();
}