package ro.mxp.food.dto;

import ro.mxp.food.entity.ProductInCart;

import java.util.List;

public class PendingDto {

    private Long id;
    private List<ProductInCart> productInCartList;
    private Long valueCart;

    public PendingDto() {
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
