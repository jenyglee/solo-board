package solo.board.entity;

import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;
    private DeliveryStatus status;

    @Embedded
    private Address address;

    public static Delivery createDelivery(Address address){
        Delivery delivery = new Delivery();
        Address address1 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
        delivery.update(address1);
        return delivery;
    }

    private void update(Address address) {
        this.status = DeliveryStatus.READY;
        this.address = address;
    }
}
