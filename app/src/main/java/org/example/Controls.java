package org.example;    // package org.example;
import org.fusesource.jansi.AnsiConsole;

public class Controls {
    public enum EventState {
        LOGIN, HOME, CATALOG
    }

    private static int windowWidth = 150;
    public static int SelectMenu(String keys, int numberOfOptions, int SelectedMenuForView) {
        int step = (keys == "up") ? -1 : 1;
        int n = (SelectedMenuForView + step + numberOfOptions) % numberOfOptions;
        return n;
    }

    public static void clearScreen() {  
		System.out.print("\033\143");
	}

    public static void PrintSelectedOptions(String text, int currentUi, boolean isSelected) {
        AnsiConsole.systemInstall();
        String greenColor = "\033[32m";
        String resetColor = "\033[0m";
        if (isSelected) {
            PrintInCenter(greenColor + text + resetColor);
        } else {
            PrintInCenter(text);
            
        }

    }

    public static void PrintInCenter(String message) {
        int leftPadding = (windowWidth - message.length()) / 2;
        System.out.println(new String(new char[leftPadding]).replace('\0', ' ') + message);    
    }
    
    public static void PrintOptionInCenter(String text, int windowWidth, boolean isSelected, int homeLeftPadding) {
        AnsiConsole.systemInstall();
        String greenColor = "\033[32m";
        String resetColor = "\033[0m";
    
        // Split the input text into lines by \n
        String[] lines = text.split("\n");
    
        for (String line : lines) {
            // Calculate padding for centering the current line
            String paddedLine = new String(new char[homeLeftPadding]).replace('\0', ' ') + line;
    
            // Print each line with or without color
            if (isSelected) {
                System.out.println(greenColor + paddedLine + resetColor);
            } else {
                System.out.println(paddedLine);
            }
        }
    }
    
    
}


