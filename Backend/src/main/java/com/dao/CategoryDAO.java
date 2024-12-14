package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Category;
@Repository
	public interface CategoryDAO extends JpaRepository<Category, Integer> {
	}

