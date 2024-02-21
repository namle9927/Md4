package ra.md4.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ra.md4.model.user.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@PasswordMatch(password = "password" , confirmPassword = "confirmPassword")
public class UserDto {
    private Integer userId;
    @NotBlank
    @Size(min=8, message = "Tối thiểu 6 ký tự")
//    @UsernameUnique
    private String username;
    @NotBlank
    @Size(min=8, message = "Tối thiểu 6 ký tự")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+\n",message = "Phải có ít nhất 1 số và 1 ký tự hoa")
    private String password;
    private String confirmPassword;
    @NotBlank
    @Email(message = "Địa chỉ Email không đúng định dạng")
    private String email;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10,11}$" , message = "Số điện thoại không đúng định dạng")
//    @PhoneUnique
    private String phoneNumber;
    private String address;
    private String avatarUrl;
    private MultipartFile userFile;
    private Role role;
}
