package solo.board.entity;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;
    private DeliveryStatus status;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;


    public static Delivery createDelivery(Address address){
        Delivery delivery = new Delivery();
        Address address1 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
        delivery.update(address1);
        return delivery;
    }

    public void update(Address address) {
        this.status = DeliveryStatus.READY;
        this.address = address;
    }

    public void setOrder(Order order){
        this.order = order;
    }

}
