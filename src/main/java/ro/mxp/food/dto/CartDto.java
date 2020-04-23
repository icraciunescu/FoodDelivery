package ro.mxp.food.dto;

import ro.mxp.food.entity.ProductInCart;

import java.util.List;

public class CartDto {

    private Long Id;
    private List<ProductInCart> productInCartList;

    public CartDto() {
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

}
