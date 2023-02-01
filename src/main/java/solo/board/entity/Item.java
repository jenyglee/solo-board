package solo.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solo.board.entity.member.Seller;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.*;

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
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Item createItem(String name, int price, int stock_quantity){
        Item item = new Item();
        item.update(name, price, stock_quantity);
        return item;
    }

    public void update(String name, int price, int stock_quantity){
        this.name = name;
        this.price = price;
        this.stock_quantity = stock_quantity;
    }
}
