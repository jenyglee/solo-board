package solo.board.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solo.board.entity.Address;
import solo.board.entity.MemberRole;
import solo.board.entity.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
// @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tableType")
public abstract class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String email; // 아이디(이메일형식)
    private String password; // 비밀번호
    private String nickName; // 이름
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orderList; // 주문 리스트


    // @Enumerated(EnumType.STRING)
    // private MemberRole role;


    public Member() {
    }

    public void setBasicInfo(String email, String password, String nickName){
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        // this.role = MemberRole.CUSTOMER;
    }


    //
    // public Member(String email, String password, String nickName, String city, String street, String zipcode) {
    //     this.email = email;
    //     this.password = password;
    //     this.nickName = nickName;
    //     Address address = new Address(city, street, zipcode);
    //     this.address = address;
    //     this.role = MemberRole.CUSTOMER;
    // }
    //
    // public void changeAdmin(){
    //     this.role = MemberRole.ADMIN;
    // }
    //
    // public void changeRole(String role) {
    //     switch (role){
    //         case "CUSTOMER":
    //             this.role = MemberRole.CUSTOMER;
    //             break;
    //         case "SELLER":
    //             this.role = MemberRole.SELLER;
    //             break;
    //         case "ADMIN":
    //             this.role = MemberRole.ADMIN;
    //             break;
    //         default:
    //             throw new IllegalArgumentException("잘못된 요청입니다.");
    //     }
    // }

    // public void setRole(MemberRole role) {
    //     this.role = role;
    // }
}
