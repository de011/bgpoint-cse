package com.dromedian.comunico.bgpoint.cse.dto;

/**
 * @author DEEPAK
 *
 */
public class ChequeBookDeliveryDTO {

	private Long  id;
	private Long  codiceUnivoco;

	public ChequeBookDeliveryDTO() {

	}

	public ChequeBookDeliveryDTO(Long id, Long codiceUnivoco) {
		super();
		this.id = id;
		this.codiceUnivoco = codiceUnivoco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodiceUnivoco() {
		return codiceUnivoco;
	}

	public void setCodiceUnivoco(Long codiceUnivoco) {
		this.codiceUnivoco = codiceUnivoco;
	}

	@Override
	public String toString() {
		return "ChequeBookDeliveryDTO [id=" + id + ", codiceUnivoco=" + codiceUnivoco + "]";
	}
}
