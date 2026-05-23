import lombok.Getter;

@Entity
@Getter
public class InstitutionsUser {

    @Id
    @Column(name = "institutions_user_id")
    private String id;
    private String name; // 기관 사용자 이름
    private String email;
    private String password;
    private UserType userType; // 사용자 유형 (기관 사용자)

    public InstitutionsUser() {
    }

    public InstitutionsUser(Long id, String name, String email, String password, UserType userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

}
