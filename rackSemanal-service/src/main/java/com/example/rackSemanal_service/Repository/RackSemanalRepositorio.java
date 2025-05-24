package com.example.rackSemanal_service.Repository;

import com.example.rackSemanal_service.Entity.RackSemanal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RackSemanalRepositorio extends JpaRepository<RackSemanal, Long> {

}
