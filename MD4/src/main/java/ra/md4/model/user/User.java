package ra.md4.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(length = 100, unique = true)
    private String username;
    @Column(length = 100)
    private String password;
    @Column(length = 100)
    private String confirmPassword;
    @Column(length = 100, unique = true)
    private String email;
    @Column(length = 100, unique = true)
    private String phoneNumber;
    @Column(length = 100)
    private String address;
    @Column(columnDefinition = "bit")
    private Boolean accountStatus;
    private String avatarUrl;
    @ManyToOne
    @JoinColumn(name = "roleName")
    private Role role;
}
