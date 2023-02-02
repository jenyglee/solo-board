package solo.board.exception;


import lombok.Getter;
import solo.board.exception.api.Status;

@Getter
public class NotFoundException extends RuntimeException{
    private Status status;

    public NotFoundException(Status status) {
        this.status = status;
    }
}
