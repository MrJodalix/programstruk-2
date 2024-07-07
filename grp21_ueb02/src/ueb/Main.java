package ueb;

/**
 * This program calculates for every given order scenario the end-snapshot
 * and prints the results to stdout.
 * @author Mo
 */
public class Main {

    /**
     * Calculates for every given order scenario the end-snapshot
     * and prints the results to stdout.
     *
     * @param args the command line arguments (not used yet)
     */
    public static void main(String[] args) {
        for (int no = 0; no < Data.getNoOfSeries(); no++) {
            int[][] order = Data.getOrderSeries(no);
            ueb.Analyze.resetToOrigState();
            System.out.println("***************************************");
            System.out.println("Warehouses (initial):");
            Analyze.printCurrentState();
            System.out.println("Orders of series " + no + ":");
            Analyze.transportOrdersOfOneSeries(order);
            Analyze.printCurrentState();
        }
    }
}
