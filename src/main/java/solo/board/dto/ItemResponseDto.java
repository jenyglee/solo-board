package solo.board.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class ItemResponseDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public ItemResponseDto(Long id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
