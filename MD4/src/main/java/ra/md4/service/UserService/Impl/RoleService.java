package ra.md4.service.UserService.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.md4.model.user.Role;
import ra.md4.repository.AccountRepositoty.RoleRepository;
import ra.md4.service.UserService.IRoleService;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByRoleName(name);
    }
}
