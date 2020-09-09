package pdl.data;

import dtc.data.Customer;

import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * Created by aaronbriel on 6/28/17.
 */
public class Tire {

    private final Logger LOGGER = Logger.getLogger(Tire.class.getName());
    public String itemId;
    public String vendor;
    public String model;
    public String size;
    public String price;
    public final static String ITEM = "ITEM_";

    private enum TireEnum {
        ITEM_19661,
        ITEM_27334,
        ITEM_36251,
        ITEM_26043;

        public static TireEnum tireForName(String tire) throws IllegalArgumentException {
            for (TireEnum c : values()) {
                if (c.toString().equalsIgnoreCase(tire)) {
                    return c;
                }
            }
            throw tireNotFound(tire);
        }

        private static IllegalArgumentException tireNotFound(String outcome) {
            return new IllegalArgumentException(("Invalid customer [" + outcome + "]"));
        }
    }

    public Tire() {
    }


    /**
     * Stores and returns specific product (tire) data based on the item ID passed in
     *
     * @param tireEnum Tire based on Item ID
     * @return Tire values
     */
    public Tire getTire(String tireEnum) {
        TireEnum productEnum = TireEnum.tireForName(tireEnum);
        Tire tire = new Tire();

        switch (productEnum) {
            case ITEM_19661:
                tire.itemId = "19661";
                tire.vendor = "Continental";
                tire.model = "Control Contact Touring A/S";
                tire.size = "195/65 R15";
                tire.price = "105";
                break;
            case ITEM_27334:
                tire.itemId = "27334";
                tire.vendor = "Yokohama";
                tire.model = "YK740 GTX";
                tire.size = "195/65 R15";
                tire.price = "107";
                break;
            case ITEM_36251:
                tire.itemId = "36251";
                tire.vendor = "Bridgestone";
                tire.model = "Potenza S-04 Pole Position";
                tire.size = "265 /35 R18 97Y XL BSW";
                tire.price = "204";
                break;
            case ITEM_26043:
                tire.itemId = "26043";
                tire.vendor = "Michelin";
                tire.model = "PILOT SPORT A/S 3 PLUS";
                tire.size = "265 /35 R18 97Y XL BSW";
                tire.price = "218";
                break;
        }
        return tire;
    }

    /**
     * Returns tire data based on item id
     *
     * @param tire tire to get data string for
     * @return String of product data
     */
    public String getProductDataString(Tire tire) {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        Field[] fields = tire.getClass().getDeclaredFields();
        result.append(newLine);

        //print field names paired with their values if not null
        for (Field field : fields) {
            try {
                if (field.get(tire) != null) {
                    result.append("  ");
                    result.append(field.getName());
                    result.append(": ");
                    result.append(field.get(tire));
                    result.append(newLine);
                }
            } catch (IllegalAccessException ex) {
                LOGGER.info(ex.toString());
            }
        }

        return result.toString();
    }

}
