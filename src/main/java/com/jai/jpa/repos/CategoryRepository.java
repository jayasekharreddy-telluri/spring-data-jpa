package com.jai.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jai.jpa.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> { }