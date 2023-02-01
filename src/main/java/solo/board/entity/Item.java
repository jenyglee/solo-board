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
    private int stockQuantity;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();


    public Item(String name, int price, int stockQuantity, Member member) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
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

    public void update(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void removeQuantity(int count) {
        int leftQuantity = this.stockQuantity - count;
        if(leftQuantity < 0){
            throw new IllegalArgumentException("구매 수량이 재고보다 많습니다.");
        }
        this.stockQuantity = leftQuantity;
    }
}
