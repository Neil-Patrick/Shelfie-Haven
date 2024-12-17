package org.example;    // package org.example;
import org.fusesource.jansi.AnsiConsole;

public class Controls {
    public enum EventState {
        LOGIN, HOME
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
            Controls.PrintInCenter(greenColor + text + resetColor);
        } else {
            Controls.PrintInCenter(text);
            
        }

    }

    public static void PrintInCenter(String message) {
        int leftPadding = (windowWidth - message.length()) / 2;

        System.out.println(new String(new char[leftPadding]).replace('\0', ' ') + message);    
    }
}


