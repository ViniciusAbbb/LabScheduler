package com.xaviervinicius.labschedule.dto.mappers;

import com.xaviervinicius.labschedule.dto.ScheduleDto;
import com.xaviervinicius.labschedule.models.scheduleModel.ScheduleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(target = "schedulerId", source = "scheduler.id")
    @Mapping(target = "labId", source = "lab.id")
    ScheduleDto map(ScheduleModel model);

    @Mapping(target = "scheduler.id", source = "id")
    @Mapping(target = "lab.id", source = "labId")
    ScheduleModel map(ScheduleDto dto);
}
