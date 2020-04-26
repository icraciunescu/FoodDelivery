package ro.mxp.food.entity;

import javax.persistence.*;

@Entity
public class InProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToOne
    private Client client;

    private Long valueCart;

    public InProgress() {
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
