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
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orderList;


    public Member(String email, String password, String nickName, String city, String street, String zipcode) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        Address address = new Address(city, street, zipcode);
        this.address = address;
        this.role = MemberRole.CUSTOMER;
    }

    public static Member createMember(String email, String password, String nickName, String city, String street, String zipcode){
        Member member = new Member(email, password, nickName, city, street, zipcode);
        return member;
    }

    public void changeAdmin(){
        this.role = MemberRole.ADMIN;
    }

    public void changeRole(String role) {
        switch (role){
            case "CUSTOMER":
                this.role = MemberRole.CUSTOMER;
                break;
            case "SELLER":
                this.role = MemberRole.SELLER;
                break;
            case "ADMIN":
                this.role = MemberRole.ADMIN;
                break;
            default:
                throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }
}
