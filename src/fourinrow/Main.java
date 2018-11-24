package fourinrow;

import java.util.Scanner;

public class Main {

    public static int gracz = 1; // numer gracza (pozniej moze byc na booleana)

    public static int kolumny = 7, wiersze = 6;  // ustaw ile wierszy i kolum

    public static int[][] p = new int[kolumny][wiersze]; //plansza - jest 7 kolumn i 6 wierszy w planszy

    public static int wybor = -1;

    //          0 1 2 3 4 5 6 taki jest planowany wyglad planszy :)
    //      0  [][][][][][][] // DATA ROZPOCZECIA projektu:  01:45:00 17.08.2018r.
    //      1  [][][][][][][] // proste gui bez wygranej:    02:23:00 17.08.2018r.
    //      2  [][][][][][][] // testowanie pion i poziom:   02:56:00 17.08.2018r.
    //      3  [][][][][][][] // test skosów prawy i lewy:   13:40:00 15.10.2018r.
    //      4  [][][][][][][] // błedy z błednym inputem:    13:04:00 16.10.2018r.
    //      5  [][][][][][][]

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        pokaz();
        while (wybor != 0) {
            try {
                String input = scan.nextLine(); //wybor kolumny
                wybor = Integer.parseInt(input);

                if (wybor > 0) wrzut(wybor);
                scanWin();
                pokaz();
            } catch (NumberFormatException e) {
                wybor = -1;
                System.out.println("Proszę wpisywać tylko cyfry!");
            } catch(ArrayIndexOutOfBoundsException e) {
                wybor = -1;
                System.out.println("Proszę wpisywać tylko cyfry od 1 do 7.");
            }
        }
    }

    static public void wrzut(int k) {
        k--;
        //sprawdza czy kolumna nie jest pełna
        if (p[k][0] != 0) {
            System.out.println("Ta kolumna jest już pełna.");
        } else {
            szukaDolu:
            for (int i = 0; i < wiersze; i++) { //ta petla obniża nasz żeton
                if (i < wiersze - 1) { //jeżeli wartość jest wieksza niż dół planszy patrzy na dolne pole spadającego żetonu
                    if (p[k][i + 1] != 0) { // jeżeli wartość pola pod żetonem nie jest równa 0 to przypisuje temu polu żeton (numer gracza)
                        p[k][i] = gracz;
                        gracz++;
                        break szukaDolu;
                    }
                } else {
                    p[k][wiersze - 1] = gracz;
                    gracz++;
                    break szukaDolu;
                }
            }
        }
        if (gracz > 2) gracz = 1;
        System.out.println("teraz gracz: " + gracz);
    }

    public static void pokaz() {

        String wierszZliczbamiKolumn = "";
        for(int i=1; i <=kolumny; i++) {wierszZliczbamiKolumn += " " + i + " ";}
        System.out.println(wierszZliczbamiKolumn);

        for (int i = 0; i < wiersze; i++) {
            for (int j = 0; j < kolumny; j++) {
                if(p[j][i]==0) System.out.print("[ ]");
                else System.out.print("[" + p[j][i] + "]");
            }
            System.out.println("");
        }
    }

    public static void scanWin() {

        //sprawdza pion
        for (int i = 0; i < kolumny; i++) {
            for (int j = wiersze - 1; j >= 3; j--) {
                int jm1 = j - 1, jm2 = j - 2, jm3 = j - 3;
                if (p[i][j] == p[i][jm1] && p[i][jm2] == p[i][jm3] && p[i][jm1] == p[i][jm2] && p[i][j]!=0) {
                    System.out.println("Wygrywa gracz nr: " + p[i][j]);
                    wybor=0;
                }
            }
        }

        //sprawdza poziom
        for(int i=wiersze-1; i>=0; i--){
            for(int j=kolumny-1; j>=3; j--){
                int jm1=j-1, jm2=j-2, jm3=j-3;
                if(p[j][i] == p[jm1][i] && p[jm2][i]==p[jm3][i] && p[jm1][i]==p[jm2][i] && p[j][i]!=0){
                    System.out.println("Wygrywa gracz nr: " + p[j][i]);
                    wybor=0;
                }
            }
        }

        //sprawdza prawy skos "/"
        for(int i=0; i<kolumny-3; i++) {
            for (int j = 0; j < wiersze-3; j++) {
                if (p[0 + i][5 - j]!=0 && p[0 + i][5 - j] == p[1 + i][4 - j] && p[2 + i][3 - j] == p[3 + i][2 - j] && p[0 + i][5 - j] == p[3 + i][2 - j]) {
                    System.out.println("Wygrywa gracz nr: " + p[0 + i][5 - j]);
                    wybor=0;
                }
            }
        }

        //sprawdza lewy skos "\"
        for(int i=kolumny-1; i>=kolumny-4; i--) {
            for (int j = wiersze-4; j >= 0; j--) {
                if (
                        p[0 + i][5 - j]!=0 &&
                        p[0 + i][5 - j] == p[i - 1][4 - j] &&
                        p[i - 2][3 - j] == p[i - 3][2 - j] &&
                        p[0 + i][5 - j] == p[i - 3][2 - j]
                ) {
                    System.out.println("Wygrywa gracz nr: " + p[0 + i][5 - j]);
                    wybor=0;
                }
            }
        }

    }

}
