package com.xaviervinicius.labschedule.repository.LabRepository;

import com.xaviervinicius.labschedule.models.labModel.LabModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends JpaRepository<LabModel,Long> {
}
