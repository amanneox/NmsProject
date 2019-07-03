package com.project.nms.repository;

import com.project.nms.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    UserModel findByEmployeeID(String EmployeeID);

}