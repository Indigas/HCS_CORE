package sk.durovic.util;

import org.mapstruct.factory.Mappers;
import sk.durovic.helper.EntityMapperHelper;
import sk.durovic.mapper.*;
import sk.durovic.model.*;

public class EntityMergerImpl implements EntityMerger{

    @Override
    public <T, R> R merge(T src, R dst) {
        EntityMapper<T, R> mapper = EntityMapperHelper.getMapper(dst.getClass());

        return mapper.updateEntity(src, dst);
    }


}
