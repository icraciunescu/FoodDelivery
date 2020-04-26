package ro.mxp.food.dto;

import ro.mxp.food.entity.Cart;
import ro.mxp.food.entity.Client;

public class InProgressDto {

    private Long id;
    private Cart cart;
    private Client client;
    private Long valueCart;

    public InProgressDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getValueCart() {
        return valueCart;
    }

    public void setValueCart(Long valueCart) {
        this.valueCart = valueCart;
    }

}
