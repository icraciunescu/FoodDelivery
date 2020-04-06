package ro.mxp.food.dto;

public class ProductDto {

    private Long id;
    private String productName;
    private String productType;
    private long productPrice;
    private String productIngredients;

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductIngredients() {
        return productIngredients;
    }

    public void setProductIngredients(String productIngredients) {
        this.productIngredients = productIngredients;
    }

}
