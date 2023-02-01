package solo.board.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class OrderItemResponseDto {
    private String name;
    private int count;

    public OrderItemResponseDto(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
