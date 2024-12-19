package org.example;

public class AsciiUIDesign {
    
    //https://patorjk.com/software/taag/#p=display&f=ANSI%20Shadow&t=Login = link for design generator

    public static void LoginUi() {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                         Login                                   ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        


    }

    public static void HomePageUi() {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                              Welcome to Library Menu Page                       ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }

    public static String Status() {
        return "╔═══════════════════════════════╗\n" +
               "║             Status            ║\n" +
               "╚═══════════════════════════════╝\n";
    }
    public static String BookCatalog() {
        return "╔═══════════════════════════════╗\n" +
               "║          Book Catalogs        ║\n" +
               "╚═══════════════════════════════╝\n";
    }
    public static String SearchAndBorrow() {
        return "╔═══════════════════════════════╗\n" +
               "║       Search And Borrow       ║\n" +
               "╚═══════════════════════════════╝\n";
    }
    public static String BorrowBookStatus() {
        return "╔═══════════════════════════════╗\n" +
               "║      Borrowed Book Status     ║\n" +
               "╚═══════════════════════════════╝\n";
    } 
    public static String Logout() {
        return "╔═══════════════════════════════╗\n" +
               "║              Logout           ║\n" +
               "╚═══════════════════════════════╝\n";
    } 

    public static void BookCatalogUi() {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                    Book Catalog                                 ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }
    public static void AddBookUi() {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                       Add Book                                  ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }

    public static void UpdateBookUi() {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                      Update Book                                ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }
    public static void MenuGuideUI(){
        Controls.PrintInCenter("╔══════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                NAVIGATION GUIDE              ║");
        Controls.PrintInCenter("║                                              ║");
        Controls.PrintInCenter("║    Select: Up Arrow Key and Down Arrow Key   ║");
        Controls.PrintInCenter("║               Confirm: Enter Key             ║");
        Controls.PrintInCenter("╚══════════════════════════════════════════════╝");
       
    }
    public static void BookCatalogGuideUI(){
        Controls.PrintInCenter("╔════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                      NAVIGATION GUIDE                      ║");
        Controls.PrintInCenter("║                                                            ║");
        Controls.PrintInCenter("║         Browse: Left Arrow Key and Right Arrow Key         ║");
        Controls.PrintInCenter("║         Add Book: CTRL Key + Shift + (+) plus sign         ║");
        Controls.PrintInCenter("║    Add Book Navigation: Up Arrow Key and Down Arrow Key    ║");
        Controls.PrintInCenter("║         Remove a Character: Backspace/DELETE Key           ║");
        Controls.PrintInCenter("║                Confirm Add Book: Enter Key                 ║");
        Controls.PrintInCenter("║                   Back: Escape Key (Esc)                   ║");
        Controls.PrintInCenter("╚════════════════════════════════════════════════════════════╝");
        
    }
    


}
