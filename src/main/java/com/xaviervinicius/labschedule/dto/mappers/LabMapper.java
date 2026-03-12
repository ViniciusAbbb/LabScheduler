package com.xaviervinicius.labschedule.dto.mappers;

import com.xaviervinicius.labschedule.dto.LabDto;
import com.xaviervinicius.labschedule.models.labModel.LabModel;
import com.xaviervinicius.labschedule.models.scheduleModel.ScheduleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {ScheduleModel.class})
public interface LabMapper {
    @Mapping(
            target = "schedulesIds",
            expression = "java(model.getSchedules().stream().map(schedule -> schedule.getId()).toList())"
    )
    LabDto map(LabModel model);

    @Mapping(
            target = "schedules",
            expression = "java(dto.schedulesIds().stream().map(ScheduleModel::new).toList())"
    )
    LabModel map(LabDto dto);
}
