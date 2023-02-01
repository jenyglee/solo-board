package solo.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import solo.board.entity.Address;
import solo.board.entity.MemberRole;
import solo.board.entity.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String email; // 아이디(이메일형식)
    private String password; // 비밀번호
    private String nickName; // 이름
    private Address address; // 배송지 주소
    @Enumerated(EnumType.STRING)
    private MemberRole role; // 권한

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>(); // 주문 리스트

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>(); // 등록한 상품 리스트


    public static Member createMember(String email, String password, String nickName, String city, String street, String zipcode){
        Address address = new Address(city, street, zipcode);
        return new Member(email, password, nickName, address);
    }


    public Member(String email, String password, String nickName, Address address) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.address = address;
        this.role = MemberRole.CUSTOMER;
    }

    public void addOrderList(Order order){
        this.orderList.add(order);
    }

    public void addItemList(Item item){
        this.itemList.add(item);
    }

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

    public void setRole(MemberRole role) {
        this.role = role;
    }
}
