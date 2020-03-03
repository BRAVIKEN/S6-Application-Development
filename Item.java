public class Item {
    private String description;
    private Double weight;
    private Double price;

    public Item(String description, Double weight, Double price) {
        this.description = description;
        this.weight = weight;
        this.price = price;
    }

    /**
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }
}