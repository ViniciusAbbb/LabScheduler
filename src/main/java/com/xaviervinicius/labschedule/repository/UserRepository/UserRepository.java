package com.xaviervinicius.labschedule.repository.UserRepository;

import com.xaviervinicius.labschedule.models.UserModel.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
