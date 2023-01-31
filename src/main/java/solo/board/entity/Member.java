package solo.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String nickName;

    private Address address;

    // @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    // private List<Order> orderList;

    public static Member createMember(String email, String password, String nickName, String city, String street, String zipcode){
        Member member = new Member();
        Address address = new Address(city, street, zipcode);
        member.update(email, password, nickName, address);
        return member;
    }

    public void update(String email, String password, String nickName, Address address) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.address = address;
    }

}
