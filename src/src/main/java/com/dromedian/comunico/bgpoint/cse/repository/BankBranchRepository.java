package com.dromedian.comunico.bgpoint.cse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dromedian.comunico.bgpoint.cse.entity.BankBranch;

@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch, Long>{		
	
	public  Optional<BankBranch> findByIdAndIsDeleted(Long branchId, boolean isDeleted);
	public boolean existsById(Long id);
	public BankBranch findByIsDeleted(boolean isDeleted);
	
	public Optional<BankBranch> findById(Long id);
	
	//where bb.isDeleted=false and sd.isDeleted=false 
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.BankBranch(bb.id, bb.branchName, bb.externalId,(select count(1) from SmartDesk sd where sd.bankBranch.id = bb.id) as noOfSmartDesk, bb.isDeleted ) "
			+ "from BankBranch bb where bb.isDeleted=false order by bb.createdOn desc")
	
	public List<BankBranch> findAllBankBranch();
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.BankBranch(bb.id, bb.branchName, bb.externalId,(select count(1) from SmartDesk sd where sd.bankBranch.id = bb.id) as noOfSmartDesk, bb.isDeleted ) "
			+ "from BankBranch bb where  bb.branchName like %:searchKeyword% or bb.externalId like %:searchKeyword% and  bb.isDeleted = false order by bb.createdOn desc")
	public List<BankBranch> searchBankBranch(@Param("searchKeyword") String searchKeyword);
}
