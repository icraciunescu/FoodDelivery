package ro.mxp.food.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductInCart> productInCartList;

    private Long valueCart;

    public Cart() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<ProductInCart> getProductInCartList() {
        return productInCartList;
    }

    public void setProductInCartList(List<ProductInCart> productInCartList) {
        this.productInCartList = productInCartList;
    }

    public Long getValueCart() {
        return valueCart;
    }

    public void setValueCart(Long valueCart) {
        this.valueCart = valueCart;
    }

}
