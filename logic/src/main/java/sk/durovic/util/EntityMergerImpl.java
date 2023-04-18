package sk.durovic.util;

import sk.durovic.helper.EntityMapperHelper;
import sk.durovic.mapper.EntityMapper;

public class EntityMergerImpl implements EntityMerger{

    @Override
    public <T, R> R merge(T src, R dst) {
        EntityMapper<T, R> mapper = EntityMapperHelper.getMapper(dst.getClass());

        return mapper.updateEntity(src, dst);
    }


}
