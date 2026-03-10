package com.xaviervinicius.labschedule.repository.SchedulerRepositoy;

import com.xaviervinicius.labschedule.models.scheduleModel.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, UUID> {
}
