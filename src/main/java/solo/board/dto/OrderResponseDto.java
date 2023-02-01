package solo.board.dto;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private String nickName;
    private List<OrderItemResponseDto> orderItemResponseDtoList;

    public OrderResponseDto(Long id, String nickName, List<OrderItemResponseDto> orderItemResponseDtoList) {
        this.id = id;
        this.nickName = nickName;
        this.orderItemResponseDtoList = orderItemResponseDtoList;
    }
}
