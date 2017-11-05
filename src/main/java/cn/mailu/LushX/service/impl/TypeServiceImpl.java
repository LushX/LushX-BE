package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.Type;
import cn.mailu.LushX.repository.TypeRepository;
import cn.mailu.LushX.service.TypeService;
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
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;


    @Override
    public void insert(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void updateById(Type type) {
        typeRepository.delete(type.getTypeId());
        typeRepository.save(type);
    }

    @Override
    public void deleteById(String Id) {
        typeRepository.delete(Id);
    }

    @Override
    public Type selectById(String Id) {
        return typeRepository.findOne(Id);
    }

    @Override
    public List<Type> selectAll() {
        return typeRepository.findAll();
    }
}
