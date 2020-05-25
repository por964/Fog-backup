package FunctionLayer;

/**
 * Formålet med denne klasse er at kunne skabe de enkelte materialer til materialelisten.
 *
 */

public class Material {
    private int id;
    private String category;
    private String name;
    private String description;
    private int quantity;
    private int length;
    private Double price = 0.0;

    public Material(int id, String category, String name, String description, int quantity, int length, Double price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.length = length;
        this.price = price;
    }

    public Material() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Material{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", length=" + length +
                ", price=" + price +
                '}';
    }
}