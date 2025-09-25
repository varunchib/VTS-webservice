package com.VTS.demo.modules.auth.repository;

import java.util.Optional;
import java.util.UUID;

import com.VTS.demo.modules.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
	boolean existsByEmployeeId(String employeeId);
}
