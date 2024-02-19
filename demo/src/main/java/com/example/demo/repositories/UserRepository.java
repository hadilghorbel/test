package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entities.User;

import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


}
