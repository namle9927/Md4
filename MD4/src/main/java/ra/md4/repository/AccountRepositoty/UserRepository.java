package ra.md4.repository.AccountRepositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.md4.model.user.User;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   @Query("SELECT U from User U where U.username = :findName")
   Optional<User> findUserByName(@Param("findName") String findName);
   Boolean existsByPhoneNumber(String phone);
   Boolean existsByUsername(String name);
}
