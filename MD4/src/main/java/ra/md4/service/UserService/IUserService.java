package ra.md4.service.UserService;

import org.springframework.security.core.userdetails.UserDetailsService;
import ra.md4.dto.user.UserDto;
import ra.md4.model.user.User;

import java.util.List;
import java.util.Optional;

public interface IUserService extends UserDetailsService {
    List<User> findAll();
    void save(UserDto userDto);
    User findUserById(Integer id);
    User findUserByName(String name);
    Boolean checkPhoneUnique(String phone);
    Boolean checkUsernameUnique(String name);

}
