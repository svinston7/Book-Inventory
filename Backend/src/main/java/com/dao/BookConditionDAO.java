package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.BookCondition;
@Repository
	public interface BookConditionDAO extends JpaRepository<BookCondition, Integer> {
	BookCondition findByRanks(int rank);
	//ranks
}

