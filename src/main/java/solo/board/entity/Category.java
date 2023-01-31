package solo.board.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    // private List<Item> itemList;
}
