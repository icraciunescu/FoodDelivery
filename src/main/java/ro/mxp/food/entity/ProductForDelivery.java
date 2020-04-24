package ro.mxp.food.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProductForDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ProductInCart> productInCartList;

    private Long valueCart;

    public ProductForDelivery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
