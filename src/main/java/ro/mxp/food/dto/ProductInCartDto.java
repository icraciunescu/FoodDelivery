package ro.mxp.food.dto;

import ro.mxp.food.entity.Client;
import ro.mxp.food.entity.Product;

public class ProductInCartDto {

    private Long id;
    private int quantityProduct;
    private Product product;
    private Client client;

    public ProductInCartDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
