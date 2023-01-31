package solo.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @ManyToOne(fetch = FetchType.LAZY)
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
