package com.example.backend.dao;

import com.example.backend.entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunctionRepository extends JpaRepository<Function,Long> {
}
