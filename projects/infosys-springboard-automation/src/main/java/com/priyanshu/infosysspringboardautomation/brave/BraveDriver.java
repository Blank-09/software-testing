package com.priyanshu.infosysspringboardautomation.brave;

import org.openqa.selenium.chrome.ChromeDriver;

import com.priyanshu.infosysspringboardautomation.brave.BraveOptions;
import com.priyanshu.infosysspringboardautomation.utils.Color;

public class BraveDriver extends ChromeDriver {

    // Get the current directory
    private static String currentDirectory = System.getProperty("user.dir");
    private static String appData = currentDirectory + "\\AppData";

    public BraveDriver() {
        this(new BraveOptions());
    }

    public BraveDriver(BraveOptions options) {
        super(setOptions(options));
        System.out.println("\n" + Color.success("Brave Browser launched successfully!\n"));
    }

    private static BraveOptions setOptions(BraveOptions options) {
        // Set the user data directory
        options.addArguments("--user-data-dir=" + appData);
        return options;
    }

}
