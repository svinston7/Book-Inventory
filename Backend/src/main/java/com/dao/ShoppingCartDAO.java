package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.ShoppingCart;
@Repository
	public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Integer> {

	List<ShoppingCart> findByUserName(String username);
	
}
