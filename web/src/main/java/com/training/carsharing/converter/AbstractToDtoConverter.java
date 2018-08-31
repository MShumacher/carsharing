package com.training.carsharing.converter;

import com.training.carsharing.dto.BaseDto;
import com.training.carsharing.model.impl.BaseEntity;

import java.util.function.Function;

public abstract class AbstractToDtoConverter<ENTITY, DTO> implements Function<ENTITY, DTO> {

    public AbstractToDtoConverter(Class<? extends DTO> dtoClass) {
        this.dtoClass = dtoClass;
    }

    private final Class<? extends DTO> dtoClass;

    protected DTO applyBaseDto(BaseEntity entity) {
        BaseDto baseDto=null;
        try {
            baseDto = (BaseDto)dtoClass.newInstance();
            baseDto.setId(entity.getId());
            baseDto.setVersion(entity.getVersion());
            baseDto.setCreatedDate(entity.getCreatedDate().get());
            baseDto.setLastModifiedDate(entity.getLastModifiedDate().get());
//        baseDto.setCreatedBy(entity.getCreatedBy().get());
//        baseDto.setLastModifiedBy(entity.getLastModifiedBy().get());

        } catch (InstantiationException | IllegalAccessException e) {
            //TODo something with this
            e.printStackTrace();
        }
        return (DTO)baseDto;
    }
}
