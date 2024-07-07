package ueb;

/**
 * Methods to manage the transport from products from warehouses to customers by drone.
 *
 * @author Mo, klk
 */
public class Analyze {

//<editor-fold defaultstate="collapsed" desc="constants">
    /**
     * signs to show for printing the map.
     */
    public static final String SIGN_WAREHOUSE = "W";
    public static final String SIGN_CUSTOMER = "C";
    public static final String SIGN_EMPTY = "E";

    /**
     * position of service-station of the drone {@code POS_SERVICE}
     */
    public static final int[] POS_SERVICE = {0, 0};

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="attributes">
    /**
     * the current map working on. Default is the Map from Data.{@code map}
     */
    public static int[][][] map = Data.getMap();

    /**
     * the current position of the Drone. Default is POS_SERVICE. {@code posDrone}
     */
    public static int[] posDrone = POS_SERVICE;

    /**
     * the amount of units the drone flew. Default is 0. {@code units}
     */
    public static int units = 0;

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="init">

    /**
     * resetting every value to its default
     */
    public static void resetToOrigState() {
        map = Data.getMap();
        posDrone = POS_SERVICE;
        units = 0;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="output">

    /**
     * ermittelt den Inhalt des Warehouse
     *
     * @param x X-Koordinate der Map
     * @param y Y-Koordinate der Map
     * @return Inhalt des Warehouse als String, wenn kein Warehouse gibt es den leeren String aus.
     */
    private static String houseInfo(int x, int y) {
        int[][][] compare = map;
        String info = "";
        for (int i = 0; i <= compare[x][y].length - 1; i++) {
            info = info + compare[x][y][i];
        }
        return info;
    }

    /**
     * ermittelt die Position der Drone
     *
     * @param posDrone Position der Drone
     * @return Position der Drone als String.
     */
    private static String droneInfo(int[] posDrone) {
        String droneInfo;
        droneInfo = posDrone[0] + "/" + posDrone[1];
        return droneInfo;
    }


    /**
     * Prints the current map to sout.
     * The signs declared in constants at begin of the class are used to
     * visualize the cells.
     */
    public static void printCurrentState() {
        int[] length = Data.getMapDimensions();
        System.out.println("---------------------------------------");
        for (int i = 0; i <= map[i].length - 1; i++) {
            for (int j = 0; j <= map.length - 1; j++) {
                if (Data.isWarehouse(j, i)) {
                    System.out.printf(SIGN_WAREHOUSE + "%-" + getPrintWidthPerColumn(j) + "s", houseInfo(j, i));
                } else {
                    if ((map[j][i].length != 0) && (!Data.isWarehouse(j, i))) {
                        System.out.printf(SIGN_CUSTOMER + "%-" + getPrintWidthPerColumn(j) + "s", houseInfo(j, i));
                    } else {
                        System.out.printf(SIGN_EMPTY + "%-" + getPrintWidthPerColumn(j) + "s", houseInfo(j, i));
                    }
                }
                if (j == length[0] - 1) {
                    System.out.println();
                }
            }
            if (i == length[1] - 1) {
                System.out.printf("Drone now at " + droneInfo(posDrone) + " flew " + units + " units%n");
            }
        }
        System.out.println("---------------------------------------");

    }

    /**
     * Determines the maximum length of a given
     * {@code column} in the map-array. Used for nice output only.
     *
     * @param column the given column
     * @return the maximum of the length of all cells in the given
     * {@code column} plus 1 (for the unique identifier of one's cell E, C or W)
     */
    static int getPrintWidthPerColumn(int column) {
        int width = 1;
        int[][][] compare = map;
        for (int i = 0; i <= compare[column].length - 1; i++) {
            if ((compare[column][i].length != 0) && (compare[column][i].length >= width)) {
                width = compare[column][i].length + 1;
            }
        }
        return width;
    }
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="helping methods">

    /**
     * Calculate the Euclidean distance between two points. Math.sqrt(), Math.pow() and Math.ceil() are used.
     *
     * @param pos1 the first point
     * @param pos2 the second point
     * @return the Euclidean distance between those two points,
     * Integer.MAX_VALUE if param is invalid
     */
    static int calcDistanceBetween(int[] pos1, int[] pos2) {
        //TODO DONE: Auch überprüfen, dass eine Position genau 2 Werte im Array hat, nicht mehr, nicht weniger.
        //TODO DONE: Hier auch überprüfen ob die Position innerhalb der Map ligt, sie also valide ist.

        //TODO 2 DONE: Weiterhin wird nicht geprüft ob die Position innerhalb der Map liegt. Zudem wird nicht überprüft,
        //        dass beide Positionen jeweils zwei Einträge haben müssen und diese auch nicht null sein dürfen. Wird
        //        z.B. mit pos1 = {1} und pos2 = {1, 1} getestet wird die invalide Positionsangabe nicht erkannt. Auch
        //        pos1 und/oder pos2 = null wird nicht als invalide erkannt. Ist eine der Positionen zu lang (z.B.
        //        pos1 = {1, 2, 3}) wird dies auch nicht als invalide erkannt.
        if (!(pos1 == null || pos2 == null)) {
            if (pos1.length == Data.getMapDimensions().length && pos2.length == Data.getMapDimensions().length) {
                return (int) Math.ceil(Math.sqrt(Math.pow(((float) pos2[0] - (float) pos1[0]), (2)) + Math.pow(((float) pos2[1] - (float) pos1[1]), (2))));
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Checks if the given position is valid in the map.
     *
     * @param pos an array with
     * @return true, if pos is a valid position in the map
     */
    static boolean isValidPosition(int[] pos) {
        if (pos != null && pos.length == Data.getMapDimensions().length) {
            return ((pos[0] >= 0) && (pos[1] >= 0) && (pos[0] <= Data.getMapDimensions()[0]) && (pos[1] <= Data.getMapDimensions()[1]));
        }
        return false;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="working methods">

    /**
     * Flies the drone from current position to a given position via the
     * Euclidean distance. Prints the destination and flewn distance.
     * Changes the attributes {@code units} and {@code posDrone}.
     * If the given position isn_t valid, a message on serr is shown.
     *
     * @param pos the position to fly to
     */
    private static void flyDroneTo(int[] pos) {
        int distance;
        if (!isValidPosition(pos)) {
            System.err.println("Error: Position ist nicht vergeben.");
        } else {
            distance = calcDistanceBetween(posDrone, pos);
            units = units + distance;
            posDrone = pos;
            System.out.printf("fly drone to " + droneInfo(posDrone) + " distance " + distance + "%n");
        }
    }

    /**
     * Transports one product of an order to a specified position with the drone.
     * Flies drone to from, collects count of ordered products at from and
     * flies drone to to. If there aren't enough products at from, the
     * remaining count of the order is given as result.
     * If from or to is not a valid position, a message is given at System.err.
     * If the product is not at from, a message is given at System.err.
     *
     * @param from    the position of the warehouse to get the product at
     * @param to      the position to transport to
     * @param product product to transport
     * @param count   count of products to transport
     * @return count of products still to transport
     */
    //TODO DONE: Inline Kommentare um den Code verständlicher zu machen.
    private static int transportSameProducts(int[] from, int[] to, int product, int count) {
        //TODO DONE: Passende Fehlermeldung ausgeben. Diese hier sollte nicht bei invalider Position, sondern bei fehlenden Produkten ausgegben werden.
        if (!(isValidPosition(from) || isValidPosition(to))) {
            System.err.print("Error: Position nicht valide.\n");
        }
        //TODO DONE: Um Codeverdopplung zu vermeiden, jedes Produkt einzeln aus dem Warehouse löschen und beim Kunden
        //      einfügen bis die gewünschte Menge geliefert wurde, oder das Produkt nicht mehr verfügbar ist.
        for (int i = count; i > 0; i--) {
            // findet die Ware im Warehouse
            if ((findNearestWarehouse(to, product) != null) && (ArrayTools.containsAt(map[from[0]][from[1]], product) != -1)) {
                //fügt die Ware  beim Customer ein und löscht sie im Warehouse
                map[to[0]][to[1]] = ArrayTools.insertElementAt(map[to[0]][to[1]], 0, product);
                map[from[0]][from[1]] = ArrayTools.deleteElementAt(map[from[0]][from[1]], ArrayTools.containsAt(map[from[0]][from[1]], product));
                count--;
            } else {
                // Wenn das Warehouse leer ist, wird das nächste gesucht.
                if (isValidPosition(findNearestWarehouse(to, product))) {
                    flyDroneTo(findNearestWarehouse(to, product));
                    flyDroneTo(to);
                    count = transportSameProducts(findNearestWarehouse(to, product), to, product, count);
                } else {
                    //Gibt es kein weiteres Warehouse kommt diese Fehleraussage und die Schleife wird beendet.
                    System.err.print("Error: " + count + " of product " + product + " missing in warehouses\n");
                    i = 0;

                }
            }
        }
        return count;
    }


    /**
     * Determines the nearest warehouse for a specified position and product.
     *
     * @param pos     the starting point
     * @param product the product
     * @return the nearest (Euclidean distance) warehouse position having the {@code product};
     * {@code null} if there is no warehouse having the product
     */
    private static int[] findNearestWarehouse(int[] pos, int product) {
        int frsDistance = 0;
        int schalter = 0;
        int secDistance;
        int[] frsWarehouse = null;
        int[] secWarehouse = new int[]{0, 0};
        for (int i = 0; i <= map[i].length - 1; i++) {
            for (int j = 0; j <= map.length - 1; j++) {
                //TODO DONE: Nur mit zwei Schleifen lösen. Bereits implementierte Methoden nutzen und in einem If verbinden.
                if (Data.isWarehouse(j, i) && (ArrayTools.containsAt(map[j][i], product) != -1)) {
                    secDistance = calcDistanceBetween(pos, secWarehouse);
                    if ((frsDistance > secDistance) || (schalter == 0)) {
                        frsDistance = secDistance;
                        frsWarehouse = new int[2];
                        frsWarehouse[0] = j;
                        frsWarehouse[1] = i;
                        schalter++;
                    }
                }
            }
        }
        //TODO DONE: Nur ein Return verwenden. Einfach zu lösen durch geeigneten Initialwert von frsWareHouse.

        return frsWarehouse;
    }

    /**
     * Transports an order-series by the drone.
     * Process every order of the series. Prints the values of the order.
     * Searches for the nearest warehouse with the product,
     * transports the ordered number of the product to the target address.
     * If the first detected warehouse doesn_t hold enough of the product,
     * the next warehouse with the product has to be used.
     * If there is no warehouse with the product, a message on System.err is printed.
     * After all orders have been delivered, the drone flies to the service-station.
     *
     * @param orders the order-series working on
     * @throws IllegalArgumentException if {@code null} is given as param
     */
    public static void transportOrdersOfOneSeries(int[][] orders) {
        if (orders == null) {
            throw new IllegalArgumentException();
        }
        int[] customer;

        for (int i = 0; i <= orders.length - 1; i++) {
            customer = new int[2];
            customer[Data.X] = orders[i][Data.X];
            customer[Data.Y] = orders[i][Data.Y];
            if (!isValidPosition(customer)) {
                throw new IllegalArgumentException();
            }
            System.out.printf("deliver " + orders[i][Data.CT] + " of product " + orders[i][Data.ID] + " to (" + droneInfo(customer) + ")%n");
            flyDroneTo(findNearestWarehouse(posDrone, orders[i][Data.ID]));
            flyDroneTo(customer);
            transportSameProducts(findNearestWarehouse(posDrone, orders[i][Data.ID]), customer, orders[i][Data.ID], orders[i][Data.CT]);


        }
        flyDroneTo(POS_SERVICE);
    }
//</editor-fold>
}
