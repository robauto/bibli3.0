/**
 * purpose- arrange list in assending and decending order
 * Created by pi on 5/18/16.
 */
public class testitem1 {


    public static void main(String args[])
    {
        Item1[] hardware = new Item1[6];

        hardware[0] = new Item1("1011", "Air Filters", 200, 10.5);
        hardware[1] = new Item1("1034", "Door Knobs",  60 , 21.5);
        hardware[2] = new Item1("1101", "Hammers,",    90,  9.99);
        hardware[3] = new Item1("1600", "Levels\t", 80, 19.99);
        hardware[4] = new Item1("1500", "Celing Fans",100, 59);
        hardware[5] = new Item1("1201", "Wrench Sets", 55, 80);


        printInventory(hardware);
        System.out.println("\n\nSorting with  item id");
        printInventory(sortID(hardware,1));
        System.out.println("\n\nSorting with  Name");
        printInventory(sortName(hardware,1));
        System.out.println("\n\nSorting by instore");
        printInventory(sortInStore(hardware,1));
        System.out.println("\n\nSorting by price");
        printInventory(sortPrice(hardware,1));


    }
    public static void printInventory(Item1[] hardware)
    {
        System.out.println("itemID\t\t\titemName\t\t\tinStore\t\t\tPrice");
        for(int i = 0; i < hardware.length; i++)
            System.out.println(hardware[i]);
    }
    public  static Item1[] sortID(Item1[] hardware, int z)
    {

        Item1[] dest = new Item1[ hardware.length ];
        if(z == 1) {
            for (int i = 0; i < hardware.length; i++) {
                Item1 next = hardware[i];
                int insertindex = 0;
                int k = i;
                while (k > 0 && insertindex == 0) {
                    if (next.getItemID().compareTo(dest[k - 1].getItemID()) > 0) {
                        insertindex = k;
                    }
                    else {
                        dest[k] = dest[k - 1];
                    }
                    k--;
                }

                dest[insertindex] = next;
            }
        }

        // descending sort
        else if(z == 2)
        {
            for (int i = 0; i < hardware.length; i++) {
                Item1 next = hardware[i];
                int insertindex = 0;
                int k = i;
                while (k > 0 && insertindex == 0) {
                    if (next.getItemID().compareTo(dest[k - 1].getItemID()) < 0) {
                        insertindex = k;
                    }
                    else {
                        dest[k] = dest[k - 1];
                    }
                    k--;
                }

                dest[insertindex] = next;
            }
        }
        return dest;
    }


    public static Item1[] sortName(Item1[] hardware, int z)
    {

        Item1[] dest = new Item1[ hardware.length ];
        if(z == 1) {
            for (int i = 0; i < hardware.length; i++) {
                Item1 next = hardware[i];
                int insertindex = 0;
                int k = i;
                while (k > 0 && insertindex == 0) {
                    if (next.getItemName().compareTo(dest[k - 1].getItemName()) > 0) {
                        insertindex = k;
                    }
                    else {
                        dest[k] = dest[k - 1];
                    }
                    k--;
                }

                dest[insertindex] = next;
            }
        }

        // descending sort
        else if(z == 2)
        {
            for (int i = 0; i < hardware.length; i++) {
                Item1 next = hardware[i];
                int insertindex = 0;
                int k = i;
                while (k > 0 && insertindex == 0) {
                    if (next.getItemName().compareTo(dest[k - 1].getItemName()) < 0) {
                        insertindex = k;
                    }
                    else {
                        dest[k] = dest[k - 1];
                    }
                    k--;
                }

                dest[insertindex] = next;
            }
        }
        return dest;
    }
    public  static Item1[] sortInStore(Item1[] hardware, int z)
    {

        Item1[] dest = new Item1[ hardware.length ];
        if(z == 1) {
            for (int i = 0; i < hardware.length; i++) {
                Item1 next = hardware[i];
                int insertindex = 0;
                int k = i;
                while (k > 0 && insertindex == 0) {
                    if (next.getInStore() > dest[k - 1].getInStore()) {
                        insertindex = k;
                    }
                    else {
                        dest[k] = dest[k - 1];
                    }
                    k--;
                }

                dest[insertindex] = next;
            }
        }

        // descending sort
        else if(z == 2)
        {
            for (int i = 0; i < hardware.length; i++) {
                Item1 next = hardware[i];
                int insertindex = 0;
                int k = i;
                while (k > 0 && insertindex == 0) {
                    if (next.getInStore() < dest[k - 1].getInStore()) {
                        insertindex = k;
                    }
                    else {
                        dest[k] = dest[k - 1];
                    }
                    k--;
                }

                dest[insertindex] = next;
            }
        }
        return dest;
    }
    public static Item1[] sortPrice(Item1[] hardware, int z)
    {

        Item1[] dest = new Item1[ hardware.length ];
        if(z == 1) {
            for (int i = 0; i < hardware.length; i++) {
                Item1 next = hardware[i];
                int insertindex = 0;
                int k = i;
                while (k > 0 && insertindex == 0) {
                    if (next.getPrice() > dest[k - 1].getPrice()) {
                        insertindex = k;
                    }
                    else {
                        dest[k] = dest[k - 1];
                    }
                    k--;
                }

                dest[insertindex] = next;
            }
        }

        // descending sort
        else if(z == 2)
        {
            for (int i = 0; i < hardware.length; i++) {
                Item1 next = hardware[i];
                int insertindex = 0;
                int k = i;
                while (k > 0 && insertindex == 0) {
                    if (next.getPrice() < dest[k - 1].getPrice()) {
                        insertindex = k;
                    }
                    else {
                        dest[k] = dest[k - 1];
                    }
                    k--;
                }

                dest[insertindex] = next;
            }
        }
        return dest;
    }


}
