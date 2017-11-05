package cn.mailu.LushX.repository;

import cn.mailu.LushX.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 8:34 2017-11-05
 * @Modified By:
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}
