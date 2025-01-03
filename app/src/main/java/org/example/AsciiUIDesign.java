package org.example;

public class AsciiUIDesign 
{
    //https://patorjk.com/software/taag/#p=display&f=ANSI%20Shadow&t=Login = link for design generator

    public static void LoginUi() 
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                         Login                                   ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        


    }
    public static void HomePageUi() 
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                              Welcome to Library Menu Page                       ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }
    public static String Status() 
    {
        return "╔═══════════════════════════════╗\n" +
               "║             Status            ║\n" +
               "╚═══════════════════════════════╝\n";
    }
    public static String BookCatalog() 
    {
        return "╔═══════════════════════════════╗\n" +
               "║          Book Catalogs        ║\n" +
               "╚═══════════════════════════════╝\n";
    }
    public static String SearchAndBorrow() 
    {
        return "╔═══════════════════════════════╗\n" +
               "║           Borrow Book         ║\n" +
               "╚═══════════════════════════════╝\n";
    }
    public static String BorrowBookStatus() 
    {
        return "╔═══════════════════════════════╗\n" +
               "║       Borrowed Book List      ║\n" +
               "╚═══════════════════════════════╝\n";
    } 
    public static String Logout() 
    {
        return "╔═══════════════════════════════╗\n" +
               "║              Exit             ║\n" +
               "╚═══════════════════════════════╝\n";
    } 
    public static void BookStatusUi() 
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                   Library Status                                ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }
    public static void BookCatalogUi() 
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                    Book Catalog                                 ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }
    public static void AddBookUi() 
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                       Add Book                                  ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }
    public static void UpdateBookUi() 
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                      Update Book                                ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
    }
    public static void BorrowBookUi() 
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                  Borrowed Book List                             ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        Controls.PrintInCenter("");
    }
    public static void SuccessfulProcess() 
    {
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                     Your request have been processed succesfully.               ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");

    }
    public static void AvailableBooksUI()
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                   Available Books                               ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
    }
    public static void FailedProcess() 
    {
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                 Your request failed.                            ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");

    }
    public static void SearchUI()
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                    Search Book                                  ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
    }
    public static void LoginGuideUI()
    {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║  [Enter]      Confirm Input  ║");
        System.out.println("╚══════════════════════════════╝");
    }
    public static void BorrowedBookGuideUI()
    {
        Controls.PrintInCenter("╔═════════════════════════════════════╗");
        Controls.PrintInCenter("║  [Left & right]        Browse       ║");
        Controls.PrintInCenter("║  [Enter]               Return Book  ║");
        Controls.PrintInCenter("║  [Esc]                 Back         ║");
        Controls.PrintInCenter("╚═════════════════════════════════════╝");
    }
    public static void ConfirmReturnGuideUI()
    {
        Controls.PrintInCenter("╔════════════════════════╗");
        Controls.PrintInCenter("║  [Enter]      Confirm  ║");
        Controls.PrintInCenter("║  [Esc]        Back     ║");
        Controls.PrintInCenter("╚════════════════════════╝");
    }
    public static void MenuGuideUI()
    {
        Controls.PrintInCenter("╔════════════════════════╗");
        Controls.PrintInCenter("║  [Up & Down]  Navigate ║");
        Controls.PrintInCenter("║  [Enter]      Confirm  ║");
        Controls.PrintInCenter("╚════════════════════════╝");
       
    }
    public static void ConfirmBorrowGuideUI()
    {
        Controls.PrintInCenter("╔════════════════════════╗");
        Controls.PrintInCenter("║  [Up & Down]  Navigate ║");
        Controls.PrintInCenter("║  [Enter]      Confirm  ║");
        Controls.PrintInCenter("║  [Esc]        Back     ║");
        Controls.PrintInCenter("╚════════════════════════╝");
    }
    public static void BookCatalogGuideUI()
    {
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
    public static void BorrowGuideUI()
    {
        Controls.PrintInCenter("╔══════════════════════════╗");
        Controls.PrintInCenter("║  [Left & Right]  Browse  ║");
        Controls.PrintInCenter("║  [Enter]         Borrow  ║");
        Controls.PrintInCenter("║  [Esc]           Back    ║");
        Controls.PrintInCenter("╚══════════════════════════╝");
    }
    public static void AddBookGuideUI()
    {
        Controls.PrintInCenter("╔═══════════════════════╗");
        Controls.PrintInCenter("║  [Up & Down]  Browse  ║");
        Controls.PrintInCenter("║  [Enter]      Save    ║");
        Controls.PrintInCenter("╚═══════════════════════╝");
    }
    public static void SearchGuideUI()
    {
        Controls.PrintInCenter("╔════════════════════╗");
        Controls.PrintInCenter("║  [Enter]   Confirm ║");
        Controls.PrintInCenter("║  [Esc]     Back    ║");
        Controls.PrintInCenter("╚════════════════════╝");
    }
    public static void LoginSuccessful()
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                 Login Successful!                               ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        
    }
    public static void NoBookUI()
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                 No Books To Return                              ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
    }
    public static void LoginFailed()
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                                   Login Failed                                  ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        
    }
    public static void InvalidInputUI()
    {
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                         Invalid Input. Please Try Again.                        ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");
        
    }
    public static void DeleteGuideUI()
    {
        Controls.PrintInCenter("╔════════════════════╗");
        Controls.PrintInCenter("║  [Enter]   Confirm ║");
        Controls.PrintInCenter("║  [Esc]     Back    ║");
        Controls.PrintInCenter("╚════════════════════╝");
    }
    public static void DeleteUI() 
    {
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("");
        Controls.PrintInCenter("╔═════════════════════════════════════════════════════════════════════════════════╗");
        Controls.PrintInCenter("║                    Are you sure you want to delete this book?.                  ║");
        Controls.PrintInCenter("╚═════════════════════════════════════════════════════════════════════════════════╝");

    }
}
