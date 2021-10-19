/**
 * Java 1. Homework 4
 *
 * @author Andrey Usmanov
 * @version 19.10.2021
 */
import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    
    final char SIGN_EMPTY = '*';
    final char SIGN_X = 'X';
    final char SIGN_O = 'O';
    char[][] table;
    Random random;
    Scanner scanner;
	
    public static void main(String[] args) {
        new TicTacToe().game();
    }
	
    TicTacToe() {
        table = new char[3][3];
	random = new Random();
	scanner = new Scanner(System.in);
    }
	
    void game() {
	initTable();
	while (true) {
	    printTable();
	    turnHuman();
	    if (isWin(SIGN_X)) {
                System.out.println("YOU WIN");
                break;
            }
            if (isTableFull()) {
                System.out.println("Sorry, DRAW...");
                break;
            }
            turnAi(SIGN_O, SIGN_X);
            if (isWin(SIGN_O)) {
                System.out.println("Ai WIN");
                break;
            }
            if (isTableFull()) {
                System.out.println("Sorry, DRAW...");
                break;
            }
        }
	printTable();
    }
	
    void initTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = SIGN_EMPTY;
            }
        }
    }
	
    void printTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(table[j][i] + " ");
            }
            System.out.println();
        }
    }
	
    void turnHuman() {
        int x, y;
        do {
            System.out.print("Enter [1..3] X Y: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[x][y] = SIGN_X;
    }
	
    void turnAi(char ch, char enemyDot) {
        int x, y;
        for (x = 0; x < 3; x++) {
	    for (y = 0; y < 3; y++) {
                if (isCellValid(x, y)) {
		    table[x][y] = enemyDot;
		    if (isWin(enemyDot)) {
			table[x][y] = ch;
		        return;
                    }
		    table[x][y] = SIGN_EMPTY;
		}
	    }
	}
	do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        table[x][y] = SIGN_O;
    }
	
    boolean isCellValid(int x, int y) {
        if (x < 0 || x > 2 || y < 0 || y > 2) {
	    return false;
	}
        return table[x][y] == SIGN_EMPTY;
    }
	
    boolean isWin(char ch) {
	for (int i = 0; i < 3; i++) {
	    //x and y
            if ((table[0][i] == ch && table[1][i] == ch && table[2][i] == ch) || 
	        (table[i][0] == ch && table[i][1] == ch && table[i][2] == ch)) 
		 return true;
            //d
            if (table[0][0] == ch && table[1][1] == ch && table[2][2] == ch) return true;
            if (table[2][0] == ch && table[1][1] == ch && table[0][2] == ch) return true;
            return false;
	}
        return false;
    }
	
    boolean isTableFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == SIGN_EMPTY) {
		    return false;
                }
	    }
        }
	return false;
    }
}
