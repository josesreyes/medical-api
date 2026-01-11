package com.jsrdev.medapi.infrastructure.database.mysql.repository;

import com.jsrdev.medapi.infrastructure.database.mysql.entity.PhysicianEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhysicianRepository extends JpaRepository<PhysicianEntity, UUID> {
}
