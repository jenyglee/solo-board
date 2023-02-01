package solo.board.entity.member;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// @DiscriminatorValue("A")
@Getter
public class Admin extends Member {
}
