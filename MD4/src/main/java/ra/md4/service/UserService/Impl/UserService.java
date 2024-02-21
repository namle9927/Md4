package ra.md4.service.UserService.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ra.md4.dto.user.AuthenticationUser;
import ra.md4.dto.user.UserDto;
import ra.md4.model.user.Role;
import ra.md4.model.user.User;
import ra.md4.repository.AccountRepositoty.UserRepository;
import ra.md4.service.UploadService;
import ra.md4.service.UserService.IRoleService;
import ra.md4.service.UserService.IUserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UploadService uploadService;
    private final ModelMapper modelMapper;
    private final IRoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        String avatarUrl = null;
        if(userDto.getUserId()!= null){
            avatarUrl = userRepository.findById(userDto.getUserId()).get().getAvatarUrl();
        }
        if(userDto.getUserFile() != null && userDto.getUserFile().getSize()>0){
            avatarUrl = uploadService.uploadFileToServer(userDto.getUserFile());
        }
        User user = modelMapper.map(userDto,User.class);
        user.setRole(roleService.findRoleByName("USER"));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAccountStatus(true);
        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findUserByName(name).orElse(null);

    }

    @Override
    public Boolean checkPhoneUnique(String phone) {
        return userRepository.existsByPhoneNumber(phone);
    }

    @Override
    public Boolean checkUsernameUnique(String name) {
        return userRepository.existsByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByName(username);
        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException("tài khoản hoặc mật khẩu ko đúng");
        }
        User user = userOptional.get();
        return new AuthenticationUser(user.getUsername(),user.getPassword(),mapRolesToAuthorities(Arrays.asList(user.getRole())));
    }
    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
