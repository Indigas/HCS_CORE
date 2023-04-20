package sk.durovic.util;

import sk.durovic.helper.EntityMapperHelper;
import sk.durovic.mapper.EntityMapper;

public class EntityMergerImpl implements EntityMerger{

    @Override
    public <T> T merge(T src, T dst) {
        EntityMapper<T> mapper = EntityMapperHelper.getMapper(dst.getClass());

        return mapper.updateEntity(src, dst);
    }


}
