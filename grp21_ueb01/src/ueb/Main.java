package ueb;

/**
 * Durchläuft alle Zahlen in einem gegebenem Zahlenbereich und prüft, was ihre Quersumme ist, ob sie eine Harshad-Zahl
 * ist, in der Collatz-Folge ist und ihre XOR-Verschlüsselung wiedergibt. Die Berechnungen werden auf der Konsole
 * ausgegeben.
 *
 * @author Anton Schmitter, Joshua Schöttke
 * @version 1.0
 */
public class Main {


    public static void main(String[] args) {
        final int START = -1;
        final int END = 2;
        final int WIDTH = getMaxLength(START, END);
        final int SCHLUESSEL = 163;


        System.out.printf("Untersucht werden Zahlen zwischen %d und %d.%n", START, END);
        if (isValidKey(SCHLUESSEL)) {
            System.out.printf("Schlüssel = %s (%d)%n", getBinaryString(SCHLUESSEL), SCHLUESSEL);
        } else {
            System.out.println("WARNUNG: Verschlüsselung nicht möglich, ungültiger Schlüssel = "
                    + getBinaryString(SCHLUESSEL) + " (" + SCHLUESSEL + ")");
        }

        for (int i = START; i <= END; i++) {
            boolean isHarshad = isHarshadNum(i);
            boolean isValidKey = isValidKey(i);
            int collatz = countCollatz(i);

            //TODO DONE Quersumme in jedem Fall
            //FIXME DONE der Text ", Collatz-Schritte =" darf nicht erscheinen, wenn die gar nicht ausgegeben werden sollen
           // System.out.printf("%" + WIDTH + "d : Quersumme = %2s, ", i, calculateDigitSum(i));
          //  if (isHarshad || isValidKey || (collatz != -1)) {
                String HarshadString = ", " + "Harshad-Zahl";
                String isValidKeyString = "Verschlüsselung = " + getBinaryString(XOREncription(i, SCHLUESSEL)) + " (" + XOREncription(i, SCHLUESSEL) + ")";
                String collatzString = ", Collatz-Schritte = " + collatz;
                if (!isHarshad) {
                    HarshadString = "";
                }
                if (!isValidKey(SCHLUESSEL)) {
                    isValidKeyString = "";
                }
                if (collatz == -1) {
                    collatzString = "";
                }

                System.out.printf("%" + WIDTH +"d : Quersumme = %2s%3s%1s%n      %1s%n",
                        i, calculateDigitSum(i), collatzString, HarshadString, isValidKeyString);
           // }
        }
    }

    /**
     * Berechnet die Ziffernlänge einer Zahl und gibt diese wieder. Bei negativen
     * Ganzzahlen wird auch das Minus als Stelle gewertet.
     *
     * @param num die Zahl, deren Stellen gezählt werden soll
     * @return Anzahl der Ziffern (Ziffernlänge)
     */
    private static int calcNumLength(int num) {
        int len = 1;
        if (num < 0) {
            len++;
            num *= -1;
        }
        while (num > 9) {
            len++;
            num /= 10;
        }
        return len;
    }

    /**
     * Bestimmt das Maximum zweier Zahlen und gibt die größere Zahl zurück.
     *
     * @param one   der erste Wert, der verglichen werden soll
     * @param other der zweite Wert, der verglichen werden soll
     * @return den größeren der beiden verglichenen Werte
     */
    private static int getMaxLength(int one, int other) {
        final int lenOne = calcNumLength(one);
        final int lenOther = calcNumLength(other);
        return lenOne > lenOther ? lenOne : lenOther;
    }


    /**
     * Berechnet die Quersumme einer Zahl, bei negativen Zahlen wird -1 zurückgegeben
     *
     * @param num die Zahl, deren Quersumme berechnet werden soll
     * @return die berechnete Quersumme. -1 für negative Zahlen
     */
    public static int calculateDigitSum(int num) {
        //TODO DONE im Kommentar festlegen, was für negative Werte gilt
        int sum = 0;
        int rest;
        while (num > 0) {
            rest = num % 10;
            sum = sum + rest;
            num = num / 10;
        }
        if (num < 0) {
            sum = -1;
        }
        return sum;
    }


    /**
     * Überprüft ob eine Zahl eine Harshad Zahl ist. Eine Harshad Zahl ist eine natürliche Zahl, die durch ihre Quersumme teilbar ist
     *
     * @param num die zu überprüfende Zahl
     * @return true wenn sie eine Harshad Zahl ist, false wenn nicht
     */
    public static boolean isHarshadNum(int num) {
        return num > 0 && num % calculateDigitSum(num) == 0;
//        int rest, sum = 0, n;
//        n = num;
//        while (num > 0) {
//            rest = num % 10;
//            sum = sum + rest;
//            num = num / 10;
//        }
//        return n % sum == 0;
    }

    /**
     * Zählt die Anzahl der benötigten Schritte in einer Collatz Folge bis zum Erreichen einer 1 mit num als Ausgangspunkt
     *
     * @param num die Zahl von der aus gezählt werden soll
     * @return die Anzahl der Schritte; -1 wenn num < 1
     */
    public static int countCollatz(int num) {
        int count = 0;
        if (num == 1) {
            return count;
        }
        if (num < 1) {
            return -1;
        }

        //Collatz Folge nur bei Zahlen >= 1 möglich
        while (num > 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = (num * 3) + 1;
            }
            count++;
        }
        return count;
    }

    /**
     * Überprüft, ob der Schlüssel im Bereich einer 8-Bit Zahl liegt
     *
     * @param key der zu überprüfende Schlüssel
     * @return true, wenn der Schlüssel im Bereich einer 8 -Bit Zahl liegt, false wenn nicht
     */
    public static boolean isValidKey(int key) {
        return key >>> 8 == 0;
    }

    /**
     * Hilfsmethode zur Erzeugung der binären Repräsentation einer Zahl
     *
     * @param num Zahl deren binären Darstellung erzeugt werden soll
     * @return binäre Darstellung der Zahl als String
     */
    public static String getBinaryString(int num) {
        //TODO DONE StringBuilder nicht nutzen
        String result = "";
        boolean wert = false;     //int j auf boolean wert gesetzt
        for (int i = 31; i >= 0; --i) {
            //FIXME Setzen Sie den Typ von j auf boolean und benennen Sie die Variable sprechend, um den Code leichter lesbar zu machen.
            if ((num & (1 << i)) != 0) {
                wert = true;
            }
            if (wert) {
                if ((num & (1 << i)) != 0) {
                    result = result + "1";
                }
                else result = result + "0";
            }
        }
        return result;

        // return Integer.toBinaryString(num);
    }

    /**
     * XOR verschlüsselt die eingebene Zahl num mit dem eingebenen Schlüssel key
     *
     * @param num zu verschlüsselnde Zahl
     * @param key Schlüssel mit dem verschlüsselt wird
     * @return die verschlüsselte Zahl
     */
    public static int XOREncription(int num, int key) {
        int n = num;
        n = n ^ key;
        for (int i = 0; i < 3; i++) {
            key = key << 8;
            n = n ^ key;
        }
        return n;
    }
}
