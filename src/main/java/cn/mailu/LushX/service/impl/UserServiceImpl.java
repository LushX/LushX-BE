package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.repository.UserRepository;
import cn.mailu.LushX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:00 2017-11-05
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateById(User user) {
        deleteById(user.getUserId());
        userRepository.save(user);
    }

    @Override
    public void deleteById(String Id) {
        userRepository.delete(Id);
    }

    @Override
    public User selectById(String Id) {
        return userRepository.findOne(Id);
    }

    @Override
    public List<User> selectAll() {
        return userRepository.findAll();
    }
}
