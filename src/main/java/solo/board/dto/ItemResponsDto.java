package solo.board.dto;

import lombok.Getter;

@Getter
public class ItemResponsDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
}
