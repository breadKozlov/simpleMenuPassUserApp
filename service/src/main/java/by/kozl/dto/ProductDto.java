package by.kozl.dto;

public class ProductDto {

    private int id;
    private String name;
    private String description;

    public ProductDto() {
    }

    public ProductDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProductDto(String name, String description) {
        this.name = name;
        this.description = description;
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

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
