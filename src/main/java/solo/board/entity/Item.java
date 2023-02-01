package solo.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private int stock_quantity;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();


    public Item(String name, int price, int stock_quantity, Member member) {
        this.name = name;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.member = member;
        // member.addItemList(this);
    }

    public static Item createItem(String name, int price, int stock_quantity, Member member){
        Item item = new Item(name, price, stock_quantity, member);
        return item;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItemList.add(orderItem);
    }
}
