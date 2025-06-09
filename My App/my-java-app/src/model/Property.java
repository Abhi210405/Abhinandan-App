public class Property {
    private String name;
    private String description;
    private String price;

    // Example construction details
    private int yearBuilt;
    private String builderName;
    private double areaSqFt;

    public Property(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Property(String name, String description, String price, int yearBuilt, String builderName, double areaSqFt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.yearBuilt = yearBuilt;
        this.builderName = builderName;
        this.areaSqFt = areaSqFt;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public int getYearBuilt() { return yearBuilt; }
    public String getBuilderName() { return builderName; }
    public double getAreaSqFt() { return areaSqFt; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(String price) { this.price = price; }
    public void setYearBuilt(int yearBuilt) { this.yearBuilt = yearBuilt; }
    public void setBuilderName(String builderName) { this.builderName = builderName; }
    public void setAreaSqFt(double areaSqFt) { this.areaSqFt = areaSqFt; }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", yearBuilt=" + yearBuilt +
                ", builderName='" + builderName + '\'' +
                ", areaSqFt=" + areaSqFt +
                '}';
    }
}