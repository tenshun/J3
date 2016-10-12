package com.tenshun.repository;

import com.tenshun.model.entity.Role;
import com.tenshun.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, String> {


}
