package ro.mxp.food.dto;

import ro.mxp.food.entity.Client;
import ro.mxp.food.entity.Product;

public class CartDto {

    private Long id;
    private int quantity;
    private Product product;
    private Client client;

    public CartDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
