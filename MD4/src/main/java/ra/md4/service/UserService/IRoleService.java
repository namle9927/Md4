package ra.md4.service.UserService;

import ra.md4.model.user.Role;

public interface IRoleService {
    void save(Role role);
    Role findRoleByName(String name);
}
