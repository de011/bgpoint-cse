package com.dromedian.comunico.bgpoint.cse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dromedian.comunico.bgpoint.cse.entity.BankOperator;

@Repository
public interface BankOperatorRepository extends JpaRepository<BankOperator, Long>{
	
	public  Optional<BankOperator> findByIdAndIsDeleted(Long bankoptId, boolean isDeleted);
	public boolean existsById(Long id);
	public List<BankOperator> findByIsDeleted(boolean isDeleted);
	public Optional<BankOperator> findById(Long id);
}
