package ro.mxp.food.dto;

import ro.mxp.food.entity.ProductInCart;

import java.util.Date;
import java.util.List;

public class ClientDto extends MyUserDto {

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private List<ProductInCart> productInCart;

    public ClientDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
