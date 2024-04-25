package com.card91.closedloopsystem.repository;

import com.card91.closedloopsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByPhoneNumber(String phoneNumber);

    User findByUserName(String phoneNo);
}
