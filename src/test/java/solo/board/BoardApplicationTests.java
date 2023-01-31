package solo.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import solo.board.entity.Item;
import solo.board.entity.Member;
import solo.board.entity.Order;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class BoardApplicationTests {
    @Test
    void contextLoads() {
    }
}
