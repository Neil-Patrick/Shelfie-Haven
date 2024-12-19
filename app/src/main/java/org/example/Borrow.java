package org.example;

import java.util.List;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Borrow {

    private static List<Books> booksList = Queries.GetBooks();

    public void BorrowNativeKeyPressed(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_ESCAPE:
                switch (LayerManager.BorrowLayer) {
                    case 0:
                        GotoHome();
                        break;
                
                    default:
                        break;
                }
                break;

            case NativeKeyEvent.VC_LEFT:
                switch (LayerManager.BorrowLayer) {
                    case 0:
                        Select(e);
                        break;
                
                    default:
                        break;
                }
                break;
            case NativeKeyEvent.VC_RIGHT:
                switch (LayerManager.BorrowLayer) {
                    case 0:
                        Select(e);
                        break;
                
                    default:
                        break;
                }
            break;
            default:
                break;
        }



        
    }

    public void BorrowNativeKeyReleased(NativeKeyEvent e) {
        
    }

    public void BorrowNativeKeyTyped(NativeKeyEvent e) {
        
    }

    public static void Select(NativeKeyEvent e) {
        Controls.clearScreen();
        String keys = (e.getKeyCode() == NativeKeyEvent.VC_LEFT) ? "up" : "down";
        LayerManager.BookIndex = Controls.SelectMenu(keys, booksList.size(), LayerManager.BookIndex);
        Catalog.ListBooks();

    }

    public void GotoHome() {
        Controls.clearScreen();
        LayerManager.BookIndex = 0;
        LayerManager.HomeOptions = 0;
        App.currentEventState = Controls.EventState.HOME;
        Home.PrintHomeUI();
    }
}
