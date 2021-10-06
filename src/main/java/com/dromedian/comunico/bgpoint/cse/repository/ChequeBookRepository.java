package com.dromedian.comunico.bgpoint.cse.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dromedian.comunico.bgpoint.cse.entity.ChequeBook;

/**
 * @author DEEPAK
 *
 */
@Repository
public interface ChequeBookRepository extends JpaRepository<ChequeBook, Long> {

	public Optional<ChequeBook> findChequeBookByCodiceUnivoco(Long codiceUnivoco);
	public boolean existsById(Long id);
	@Query("select chequeBook.codiceUnivoco from ChequeBook chequeBook where chequeBook.codiceUnivoco in :codiceUnivoco")
	public List<Long> getDuplicateChequeBook(@Param("codiceUnivoco") List<Long> codiceUnivoco);

	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.ChequeBook( chequeBook.id,  chequeBook.cabAssegno,  chequeBook.cinPrimoAssegno,  chequeBook.codCategoria,  "
			+ "chequeBook.codGruppo,chequeBook.codiceUnivoco,  chequeBook.filAssegno,  chequeBook.idAzione,  chequeBook.nrAssegni,  chequeBook.nrPrimoAssegno,chequeBook.nrUltimoAssegno,  "
			+ "chequeBook.numRapporto,  chequeBook.requestId,  chequeBook.serieAssegni, chequeBook.isDeleted,"
			+ "(select count(cblt.smartDesk.id) from ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id = chequeBook.id) as noOfSmartDesk, "
			+ "(select count(cbit.chequeBook.id) from ChequeBooksIngestionTransaction cbit where cbit.chequeBook.id = chequeBook.id) as noOfIngestionTransaction, "
			+ "(select count(cblt.chequeBook.id) from ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id = chequeBook.id) as noOfLodingTransaction, "
			+ "(select count(cbdt.chequeBook.id) from ChequeBooksDeliveryTransaction cbdt where cbdt.chequeBook.id = chequeBook.id) as noOfDeliveryTransaction)"
			+ "from ChequeBook  chequeBook, ChequeBooksLoadingTransaction  chequebookLT WHERE chequeBook.id =chequebookLT.chequeBook.id and  chequebookLT.smartDesk.id =:smartDeskId")
	public List<ChequeBook> getChequeBooksBySmartDeskId(@Param("smartDeskId") Long smartDeskId);

	public Optional<ChequeBook> findById(Long id);


	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.ChequeBook( chequeBook.id,  chequeBook.cabAssegno,  chequeBook.cinPrimoAssegno,  chequeBook.codCategoria,  "
			+ "chequeBook.codGruppo,chequeBook.codiceUnivoco,  chequeBook.filAssegno,  chequeBook.idAzione,  chequeBook.nrAssegni,  chequeBook.nrPrimoAssegno,chequeBook.nrUltimoAssegno,  "
			+ "chequeBook.numRapporto,  chequeBook.requestId,  chequeBook.serieAssegni, chequeBook.isDeleted, "
			+ "(select count(cblt.smartDesk.id) from ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id = chequeBook.id) as noOfSmartDesk, "
			+ "(select count(cbit.chequeBook.id) from ChequeBooksIngestionTransaction cbit where cbit.chequeBook.id = chequeBook.id) as noOfIngestionTransaction, "
			+ "(select count(cblt.chequeBook.id) from ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id = chequeBook.id) as noOfLodingTransaction, "
			+ "(select count(cbdt.chequeBook.id) from ChequeBooksDeliveryTransaction cbdt where cbdt.chequeBook.id = chequeBook.id) as noOfDeliveryTransaction)"
			+ " from ChequeBook  chequeBook WHERE "
			+ "chequeBook.id like CONCAT('%',:searchKeyword, '%') "
			+ "or chequeBook.cabAssegno like CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.cinPrimoAssegno like CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.codCategoria like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.codGruppo like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.codiceUnivoco like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.filAssegno like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.idAzione like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.nrAssegni like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.nrPrimoAssegno like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.nrUltimoAssegno like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.numRapporto like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.requestId like  CONCAT('%',:searchKeyword, '%')"
			+ "or chequeBook.serieAssegni like  CONCAT('%',:searchKeyword, '%')"
			+ "and chequeBook.isDeleted=false "
			+ "order by chequeBook.createdOn desc")
	public List<ChequeBook>  searchChequeBook(@Param("searchKeyword") String searchKeyword);


	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.ChequeBook( chequeBook.id,  chequeBook.cabAssegno,  chequeBook.cinPrimoAssegno,  chequeBook.codCategoria,  "
			+ "chequeBook.codGruppo,chequeBook.codiceUnivoco,  chequeBook.filAssegno,  chequeBook.idAzione,  chequeBook.nrAssegni,  chequeBook.nrPrimoAssegno,chequeBook.nrUltimoAssegno,  "
			+ "chequeBook.numRapporto,  chequeBook.requestId,  chequeBook.serieAssegni, chequeBook.isDeleted, "
			+ "(select count(cblt.smartDesk.id) from ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id = chequeBook.id) as noOfSmartDesk, "
			+ "(select count(cbit.chequeBook.id) from ChequeBooksIngestionTransaction cbit where cbit.chequeBook.id = chequeBook.id) as noOfIngestionTransaction, "
			+ "(select count(cblt.chequeBook.id) from ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id = chequeBook.id) as noOfLodingTransaction, "
			+ "(select count(cbdt.chequeBook.id) from ChequeBooksDeliveryTransaction cbdt where cbdt.chequeBook.id = chequeBook.id) as noOfDeliveryTransaction)"
			+ " from ChequeBook  chequeBook WHERE "
			+ " chequeBook.isDeleted=false "
			+ "order by chequeBook.createdOn desc")
	public List<ChequeBook> findAllChequeBooks();

	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.ChequeBook( chequeBook.id,  chequeBook.cabAssegno,  chequeBook.cinPrimoAssegno,  chequeBook.codCategoria,  "
			+ "chequeBook.codGruppo,chequeBook.codiceUnivoco,  chequeBook.filAssegno,  chequeBook.idAzione,  chequeBook.nrAssegni,  chequeBook.nrPrimoAssegno,chequeBook.nrUltimoAssegno,  "
			+ "chequeBook.numRapporto,  chequeBook.requestId,  chequeBook.serieAssegni, chequeBook.isDeleted, "
			+ "(select count(cblt.smartDesk.id) from ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id = chequeBook.id) as noOfSmartDesk, "
			+ "(select count(cbit.chequeBook.id) from ChequeBooksIngestionTransaction cbit where cbit.chequeBook.id = chequeBook.id) as noOfIngestionTransaction, "
			+ "(select count(cblt.chequeBook.id) from ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id = chequeBook.id) as noOfLodingTransaction, "
			+ "(select count(cbdt.chequeBook.id) from ChequeBooksDeliveryTransaction cbdt where cbdt.chequeBook.id = chequeBook.id) as noOfDeliveryTransaction)"
			+ " from ChequeBook  chequeBook WHERE chequeBook.id=:chequebookId "
			+ " and chequeBook.isDeleted=false order by chequeBook.createdOn desc")
	public Optional<ChequeBook> findChequeBookById(@Param("chequebookId") Long chequebookId);
}
