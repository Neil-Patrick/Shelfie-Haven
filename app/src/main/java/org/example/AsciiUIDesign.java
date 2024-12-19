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
    public static void LoginGuideUI(){
        Controls.PrintInCenter("╔════════════════════════╗");
        Controls.PrintInCenter("║  [Up & Down]  Navigate ║");
        Controls.PrintInCenter("║  [Enter]      Login    ║");
        Controls.PrintInCenter("║  [Esc]        Back     ║");
        Controls.PrintInCenter("╚════════════════════════╝");
    }
    public static void MenuGuideUI(){
        Controls.PrintInCenter("╔════════════════════════╗");
        Controls.PrintInCenter("║  [Up & Down]  Navigate ║");
        Controls.PrintInCenter("║  [Enter]      Confirm  ║");
        Controls.PrintInCenter("╚════════════════════════╝");
       
    }
    public static void BookCatalogGuideUI(){
        Controls.PrintInCenter("╔════════════════════════════════════╗");
        Controls.PrintInCenter("║ [Left & Right]        Browse       ║");
        Controls.PrintInCenter("║ [CTRL + n]            Add Book     ║");
        Controls.PrintInCenter("║ [CTRL + b]            Search       ║");
        Controls.PrintInCenter("║ [Up & Down]           Add Book Nav ║");
        Controls.PrintInCenter("║ [Del]                 Delete Book  ║");
        Controls.PrintInCenter("║ [Enter]               Edit         ║");
        Controls.PrintInCenter("║ [Esc]                 Back         ║");
        Controls.PrintInCenter("╚════════════════════════════════════╝");
        
    }
    public static void AddBookGuideUI(){
        Controls.PrintInCenter("╔═══════════════════════╗");
        Controls.PrintInCenter("║  [Up & Down]  Browse  ║");
        Controls.PrintInCenter("║  [Enter]      Save    ║");
        Controls.PrintInCenter("╚═══════════════════════╝");
    }

    public static void SearchGuideUI(){
        Controls.PrintInCenter("╔════════════════════╗");
        Controls.PrintInCenter("║  [Enter]   Confirm ║");
        Controls.PrintInCenter("║  [Esc]     Back    ║");
        Controls.PrintInCenter("╚════════════════════╝");
    }
    


}
