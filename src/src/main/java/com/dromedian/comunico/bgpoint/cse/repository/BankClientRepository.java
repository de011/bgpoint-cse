package com.dromedian.comunico.bgpoint.cse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dromedian.comunico.bgpoint.cse.entity.BankClient;

@Repository
public interface BankClientRepository extends JpaRepository<BankClient, Long> {
	public  Optional<BankClient> findByIdAndIsDeleted(Long bankclientid, boolean isDeleted);
	public boolean existsById(Long id);
	public List<BankClient> findByIsDeleted(boolean isDeleted);
	public Optional<BankClient> findById(Long id);

}
