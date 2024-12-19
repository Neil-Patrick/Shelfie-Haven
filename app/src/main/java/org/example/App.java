/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.example.Controls.EventState;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class App implements NativeKeyListener {
	public static EventState currentEventState = EventState.HOME; // Default state
	public static App app = new App();
	public static Status status = new Status();
	public static Home home = new Home();
	public static Catalog catalog = new Catalog();
	public static Borrow borrow = new Borrow();
	public static BorrowedList borrowedList = new BorrowedList();

    public static void main(String[] args) {	
		// Scanner scanner = new Scanner(System.in);
		// //==================== Registering Global Key Listener ================================
		// System.out.println("Press Enter to start the program");
		// scanner.nextLine();
		//TODO: Proof na pede gumamit ng scanner pero before magregister ng global key listener dapat
		// So ang solution kapag need ng scanner is maggawa ng login using scanner then aalisin na yung isang login
		//================================================================================================
        try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(app);

		//==================== Login ================================
		
			
		switch (currentEventState) {
			case STATUS:
				status.PrintStatusUI();
				break;
			case HOME:
				Home.PrintHomeUI();
				break;
			case CATALOG:
				Catalog.ListBooks();
			case BORROW:
				Catalog.ListBooks();
				break;
			case BORROWEDLIST:
				BorrowedList.BorrowedBookListUi();
				break;
			default:
				break;
		}
		
		
		
    }

	public void nativeKeyPressed(NativeKeyEvent e) {
		switch (currentEventState) {
			case STATUS:
				status.StatusNativeKeyPressed(e);
				break;
			case HOME:
				home.HomeNativeKeyPressed(e);
				break;
			case CATALOG:
				catalog.CatalogNativeKeyPressed(e);
				break;
			case BORROW:
				borrow.BorrowNativeKeyPressed(e);
				break;
			case BORROWEDLIST:
				borrowedList.BorrowedBookListNativeKeyPressed(e);
				break;
			default:
				break;
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		switch (currentEventState) {
			case CATALOG:
				catalog.CatalogNativeKeyReleased(e);
				break;
			case BORROW:
				borrow.BorrowNativeKeyReleased(e);
				break;
			default:
				break;
		}
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		switch (currentEventState) {
			case CATALOG:
				catalog.CatalogNativeKeyTyped(e);
				break;
			case BORROW:
				borrow.BorrowNativeKeyTyped(e);
				break;
			default:
				break;
		}
	}
	
}
