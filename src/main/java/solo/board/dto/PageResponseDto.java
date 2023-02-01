package solo.board.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class PageResponseDto<T> {
    private int currentPage;
    private long totalCount;
    private T data;

    public PageResponseDto(int currentPage, long totalCount, T data) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.data = data;
    }
}
