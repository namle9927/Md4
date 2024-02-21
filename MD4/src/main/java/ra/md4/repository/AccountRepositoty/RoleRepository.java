package ra.md4.repository.AccountRepositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.md4.model.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByRoleName(String name);
}
