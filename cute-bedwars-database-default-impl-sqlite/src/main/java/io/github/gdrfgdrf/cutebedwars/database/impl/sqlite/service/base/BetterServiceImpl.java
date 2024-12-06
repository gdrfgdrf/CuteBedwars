package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.dreamyoung.mprelation.ServiceImpl;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IClasses;
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common.Mappers;

/**
 * @author gdrfgdrf
 */
public class BetterServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
    private M mapperInstance;

    @SuppressWarnings("unchecked")
    public M mapper() {
        if (mapperInstance != null) {
            return mapperInstance;
        }

        Class<?> mType = IClasses.Companion.instance().getClassParameter(this, 0);
        mapperInstance = Mappers.INSTANCE.getOrCreateMapper((Class<? extends BaseMapper<?>>) mType);
        return mapperInstance;
    }

    public int insert(T t) {
        if (mapper() == null) {
            return -1;
        }
        return mapper().insert(t);
    }

}
