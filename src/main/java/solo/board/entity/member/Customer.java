package solo.board.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solo.board.entity.Address;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// @DiscriminatorValue("C")
@Getter
// @Setter
@NoArgsConstructor
public class Customer extends Member{
    private Address address; // 배송지 주소

    public void setAddress(String city, String street, String zipcode){
        Address address = new Address(city, street, zipcode);
        this.address = address;
    }


}
