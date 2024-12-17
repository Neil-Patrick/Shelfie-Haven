package org.example;

public class AsciiUIDesign {
    
    //https://patorjk.com/software/taag/#p=display&f=ANSI%20Shadow&t=Login = link for design generator

    public static void LoginUi() {
        Controls.PrintInCenter("██╗      ██████╗  ██████╗ ██╗███╗   ██╗");
        Controls.PrintInCenter("██║     ██╔═══██╗██╔════╝ ██║████╗  ██║");
        Controls.PrintInCenter("██║     ██║   ██║██║  ███╗██║██╔██╗ ██║");
        Controls.PrintInCenter("██║     ██║   ██║██║   ██║██║██║╚██╗██║");
        Controls.PrintInCenter("███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║");
        Controls.PrintInCenter("╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝");

    }

    public static void HomePageUi() {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                     Welcome to Library Home Page                ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════╝");
    }

    public static String Status() {
        return "╔═════════════════════════════════════════════════════════════════╗\n" +
               "║                               Status                            ║\n" +
               "╚═════════════════════════════════════════════════════════════════╝";
    }
    public static String BookCatalog() {
        return "╔═════════════════════════════════════════════════════════════════╗\n" +
               "║                            Book Catalogs                        ║\n" +
               "╚═════════════════════════════════════════════════════════════════╝";
    }
    public static String SearchAndBorrow() {
        return "╔═════════════════════════════════════════════════════════════════╗\n" +
               "║                         Search And Borrow                       ║\n" +
               "╚═════════════════════════════════════════════════════════════════╝";
    }
    public static String BorrowBookStatus() {
        return "╔═════════════════════════════════════════════════════════════════╗\n" +
               "║                        Borrowed Book Status                     ║\n" +
               "╚═════════════════════════════════════════════════════════════════╝";
    } 
    public static String Logout() {
        return "╔═════════════════════════════════════════════════════════════════╗\n" +
               "║                                Logout                           ║\n" +
               "╚═════════════════════════════════════════════════════════════════╝";
    } 


    


}
