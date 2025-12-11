import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;
import java.io.Serializable;

public class Product implements Serializable {
    public static final int NAME_LENGTH = 35;
    public static final int DESCRIPTION_LENGTH = 75;
    public static final int ID_LENGTH = 6;

    public static final int RECORD_SIZE = (NAME_LENGTH*2)+(DESCRIPTION_LENGTH*2)+(ID_LENGTH*2)+8;

    private String name;
    private String description;
    private String ID;
    private double cost;

    /**
     * Construct a new Product object
     *
     * @param name The product's name
     * @param description Product's details
     * @param ID ID never change
     * @param cost cost of product
     */

    public Product(String name, String description, String ID, double cost) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Returns a JSON string representation of the Product object.
     *
     * @return The JSON string.
     */
    public String toJSON(){
        return "{" +
                "\"ID\": \"" + this.ID + "\"," +
                "\"name\": \"" + this.name + "\"," +
                "\"description\": \"" + this.description + "\"," +
                "\"price\": \"" + this.cost + "\"," +
                "}";
    }

    /**
     * Returns an XML string of the Product object.
     *
     * @return The XML string.
     */
    public String toXML(){
        return "<Person>" +
                "<ID>" + this.ID + "</ID>" +
                "<name>" + this.name + "</name>" +
                "<description>" + this.description + "</description>" +
                "<price>" + this.cost + "</price>" +
                "</Person>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return ID.equals(product.ID)
                && name.equals(product.name)
                && description.equals(product.description)
                && cost == product.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ID, cost);
    }

    private static String padString(String s, int length){
        if(s.length()>length){
            return s.substring(0, length);
        }else{
            StringBuilder sb = new StringBuilder(s);
            while (sb.length()<length){
                sb.append(' ');
            }
            return sb.toString();
        }
    }

    public void writeToFile(RandomAccessFile raf) throws IOException {
        raf.writeChars(padString(name, NAME_LENGTH));
        raf.writeChars(padString(description, DESCRIPTION_LENGTH));
        raf.writeChars(padString(ID, ID_LENGTH));
        raf.writeDouble(cost);
    }
}
