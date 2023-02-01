package solo.board.entity.member;

import lombok.Getter;
import solo.board.entity.Address;
import solo.board.entity.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
// @DiscriminatorValue("S")
@Getter
public class Seller extends Member {
    private Address address; // 배송지 주소
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private List<Item> itemList = new ArrayList<>();  // 등록한 상품 리스트

    public void setAddress(String city, String street, String zipcode){
        Address address = new Address(city, street, zipcode);
        this.address = address;
    }
}
