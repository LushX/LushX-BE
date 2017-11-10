package cn.mailu.LushX.service;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:00 2017-11-05
 * @Modified By:
 */
public interface UserService extends BaseService<User> {

    ServerResponse<String> register(User user);

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    User save(User user);
}
