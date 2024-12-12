package org.example;    // package org.example;

public class Controls {

    public static int SelectMenu(String keys, int numberOfOptions, int SelectedMenuForView) {
        int step = (keys == "up") ? -1 : 1;
        int n = (SelectedMenuForView + step + numberOfOptions) % numberOfOptions;
        return n;
    }

    public static void clearScreen() {  
		System.out.print("\033\143");
	}

    public static void PrintSelectedOptions(String text, int currentUi, boolean isSelected) {
        String greenColor = "\033[32m";
        String resetColor = "\033[0m";
        if (isSelected) {
            System.out.println(greenColor + text + resetColor);
        } else {
            System.out.println(text);
            
        }


    }
}


