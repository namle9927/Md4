package ra.md4.service;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ra.md4.model.user.Role;
import ra.md4.model.user.User;
import ra.md4.repository.AccountRepositoty.UserRepository;
import ra.md4.service.UserService.IRoleService;

@Component
@RequiredArgsConstructor
public class DefaultAdminInitializer {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IRoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void intializeDefaultAdmin() {
        Role roleUser = new Role("USER");
        Role roleAdmin = new Role("ADMIN");
        Role roleMod = new Role("MOD");
        roleService.save(roleUser);// can hoi cho nay
        roleService.save(roleAdmin);
        roleService.save(roleMod);
        if (userRepository.findUserByName("admin").isEmpty()){
            User admin = new User();
            admin.setUserId(1);
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setUsername("admin");
            Role adminRole = roleService.findRoleByName("ADMIN");
            admin.setRole(adminRole);
            userRepository.save(admin);
        }
    }
}
