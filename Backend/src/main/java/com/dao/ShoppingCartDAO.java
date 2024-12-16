package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.ShoppingCart;
@Repository
	public interface AuthorDAO extends JpaRepository<ShoppingCart, Integer> {
	}
