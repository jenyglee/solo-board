package solo.board.exception.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Status {
    // 500 -> INTERNAL SERVER ERROR : 서버에러
    // 400 ->  BAD _ REQUEST : 잘못된 요청 (ex. 파라미터 값을 확인해주세요 )
    // 409 ->  CONFLICT : 중복 데이터 (ex. 이미 중복된 값)
    // 404 ->  NOT _ FOUND : 잘못된 리소스 접근 (ex. 존재하지 않는 값)
    // 401 -> 잘못된 인증 및 인가 정보
    NOT_FOUND_ITEM(HttpStatus.BAD_REQUEST, "상품을 찾을 수 없습니다."),
    NOT_FOUND_ORDER(HttpStatus.BAD_REQUEST, "주문을 찾을 수 없습니다."),
    NOT_FOUND_ORDER_ITEM(HttpStatus.BAD_REQUEST, "주문상품을 찾을 수 없습니다.");
    private HttpStatus httpStatus;
    private String message;

    Status(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
