package com.dromedian.comunico.bgpoint.cse.service.impl;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.OperatorCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dromedian.bgpoint.hb.AtmInfoT;
import com.dromedian.bgpoint.hb.BgPointHbProperties;
import com.dromedian.bgpoint.hb.BgPointHbServiceImpl;
import com.dromedian.bgpoint.hb.EsecuzioneOperazioneInputData;
import com.dromedian.bgpoint.hb.EsecuzioneOperazioneOutputData;
import com.dromedian.bgpoint.hb.FiltroModuliInBiancoT;
import com.dromedian.bgpoint.hb.OperationInfoT;
import com.dromedian.bgpoint.hb.RapportoT;
import com.dromedian.bgpoint.hb.WZApplicationException_Exception;
import com.dromedian.bgpoint.token.BgpointToken;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBook;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCard;
import com.dromedian.comunico.bgpoint.cse.util.CSEConstants;

@Service
public class BgpointCseUtility  implements CSEConstants{
	private static final Logger LOG = LoggerFactory.getLogger(BgpointCseUtility.class);
	BgPointHbServiceImpl bgPointService = null;

	@Value("${endpointUrl}")
	String endpointUrl;

	public BgPointHbServiceImpl getBgPointObject() {
		BgpointToken bgpointToken = new BgpointToken();
		String token = null;
		try {
			token = bgpointToken.getToken(null);
		} catch (UnrecoverableKeyException e) {
			LOG.error(" Getting UnrecoverableKeyException in bgpointToken");
			e.printStackTrace();
		} catch (CertificateException e) {
			LOG.error(" Getting CertificateException in bgpointToken");
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			LOG.error(" Getting NoSuchProviderException in bgpointToken");
			e.printStackTrace();
		} catch (KeyStoreException e) {
			LOG.error(" Getting KeyStoreException in bgpointToken");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			LOG.error(" Getting NoSuchAlgorithmException in bgpointToken");
			e.printStackTrace();
		} catch (OperatorCreationException e) {
			LOG.error(" Getting OperatorCreationException in bgpointToken");
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error(" Getting IOException in bgpointToken");
			e.printStackTrace();
		} catch (CMSException e) {
			LOG.error(" Getting CMSException in bgpointToken");
			e.printStackTrace();
		}
		
		BgPointHbProperties properties = new BgPointHbProperties();
		properties.setToken(token);
		properties.setEndpoint(endpointUrl);
		this.bgPointService = new BgPointHbServiceImpl(properties);
		return bgPointService;
	}

	//  1.9 Action 37: Ricezione moduli in bianco da TCR ( Reception of blank modules from TCR ) for check Book second scenario

	public EsecuzioneOperazioneOutputData esecuzioneOperazioneOutputData2(ChequeBook chequebook)throws WZApplicationException_Exception {
		EsecuzioneOperazioneInputData esecuzioneOperazioneInputparameters2 = new EsecuzioneOperazioneInputData();
		AtmInfoT atmInfoT1 = new AtmInfoT();
		atmInfoT1.setRequestId(chequebook.getRequestId());
		esecuzioneOperazioneInputparameters2.setAtminfo(atmInfoT1);

		FiltroModuliInBiancoT filtroModuliInBianco = new FiltroModuliInBiancoT();
		
		if (!StringUtils.isEmpty(chequebook.getSerieAssegni())) {
			//filtroModuliInBianco.setSerieAssegni(chequebook.getSerieAssegni());
		}
		filtroModuliInBianco.setSerieAssegni("R1");
		
		if (!StringUtils.isEmpty(chequebook.getFilAssegno())) {
			filtroModuliInBianco.setFilAssegno(chequebook.getFilAssegno());
		}

		if (!StringUtils.isEmpty(chequebook.getCabAssegno())) {
			filtroModuliInBianco.setCabAssegno(chequebook.getCabAssegno());
		}
		if (!StringUtils.isEmpty(chequebook.getNrPrimoAssegno())) {
			filtroModuliInBianco.setNrPrimoAssegno(chequebook.getNrPrimoAssegno());
		}
		if (!StringUtils.isEmpty(chequebook.getNrUltimoAssegno())) {
			filtroModuliInBianco.setNrUltimoAssegno(chequebook.getNrUltimoAssegno());
		}

		if (!StringUtils.isEmpty(chequebook.getNrAssegni())) {
			filtroModuliInBianco.setNrAssegni(chequebook.getNrAssegni());
		}
		if (!StringUtils.isEmpty(chequebook.getCinPrimoAssegno())) {
			filtroModuliInBianco.setCinPrimoAssegno(chequebook.getCinPrimoAssegno()%13);
		}
		esecuzioneOperazioneInputparameters2.setFiltroModuliInBianco(filtroModuliInBianco);

		OperationInfoT operationInfoT=new OperationInfoT();
			operationInfoT.setIdAzione(CHEQUEBOOK_LOADING_ACTION);	
			
		
		esecuzioneOperazioneInputparameters2.setOperinfo(operationInfoT);
		// Removed according to latest WSDL
		/*
		RapportoT rapporto=new RapportoT();
		if(!StringUtils.isEmpty(header.get("codCategoria"))){
			rapporto.setCodCategoria(header.get("codCategoria")));
		}

		if(!StringUtils.isEmpty(header.get("codGruppo")))
		{
			rapporto.setCodGruppo(header.get("codGruppo")));
		}

		if(!StringUtils.isEmpty(header.get("numRapporto"))){
			rapporto.setNumRapporto(header.get("numRapporto")));
		}

		if(!StringUtils.isEmpty(header.get("codFilialeAssegnaz"))){
			rapporto.setCodFilialeAssegnaz(header.get("codFilialeAssegnaz")));
		}*/

		LOG.info("Making call to CSE with parameter : ", esecuzioneOperazioneInputparameters2);
		System.out.println("bbb    " + bgPointService);
		if (bgPointService == null) {
			bgPointService = getBgPointObject();
		}
		return bgPointService.esecuzioneOperazione(esecuzioneOperazioneInputparameters2);
	}


	//  1.7 Action 35: Rilascio carnet assegni da TCR (Cheque book issue by TCR) for cheque Book third scenario

	public EsecuzioneOperazioneOutputData esecuzioneOperazioneOutputData3(ChequeBook chequebook)throws WZApplicationException_Exception {
		EsecuzioneOperazioneInputData esecuzioneOperazioneInputparameters3 = new EsecuzioneOperazioneInputData();
		AtmInfoT atmInfoT1 = new AtmInfoT();
		atmInfoT1.setRequestId(chequebook.getRequestId());
		esecuzioneOperazioneInputparameters3.setAtminfo(atmInfoT1);

	
		FiltroModuliInBiancoT filtroModuliInBianco = new FiltroModuliInBiancoT();

		if(!StringUtils.isEmpty(chequebook.getFilAssegno())){

			filtroModuliInBianco.setFilAssegno(chequebook.getFilAssegno());
		}

		if (!StringUtils.isEmpty(chequebook.getCabAssegno())) {
			filtroModuliInBianco.setCabAssegno(chequebook.getCabAssegno());
		}

		if(!StringUtils.isEmpty(chequebook.getNrPrimoAssegno())){
			filtroModuliInBianco.setNrPrimoAssegno(chequebook.getNrPrimoAssegno());
		}

		if(!StringUtils.isEmpty(chequebook.getNrUltimoAssegno())){
			filtroModuliInBianco.setNrUltimoAssegno(chequebook.getNrUltimoAssegno());
		}
		if(!StringUtils.isEmpty(chequebook.getNrAssegni())){
			filtroModuliInBianco.setNrAssegni(chequebook.getNrAssegni());
		}
		if (!StringUtils.isEmpty(chequebook.getCinPrimoAssegno())) {
			filtroModuliInBianco.setCinPrimoAssegno(chequebook.getCinPrimoAssegno());
		}		
		esecuzioneOperazioneInputparameters3.setFiltroModuliInBianco(filtroModuliInBianco);

		OperationInfoT operationInfoT = new OperationInfoT();
		operationInfoT.setIdAzione(CHEQUEBOOK_DELIVERY_ACTION);

		esecuzioneOperazioneInputparameters3.setOperinfo(operationInfoT);
		
		RapportoT rapporto = new RapportoT();
		if (!StringUtils.isEmpty(chequebook.getCodCategoria())) {
			rapporto.setCodCategoria(chequebook.getCodCategoria());
		}

		if(!StringUtils.isEmpty(chequebook.getCodGruppo())){
			rapporto.setCodGruppo(chequebook.getCodGruppo());
		}
		if (!StringUtils.isEmpty(chequebook.getNumRapporto())){
			rapporto.setNumRapporto(chequebook.getNumRapporto());
		}
		esecuzioneOperazioneInputparameters3.setRapporto(rapporto);
		LOG.info("Making call to CSE with parameter : ", esecuzioneOperazioneInputparameters3);
		System.out.println("bbb    " + bgPointService);
		if (bgPointService == null) {
			bgPointService = getBgPointObject();
		}
		return bgPointService.esecuzioneOperazione(esecuzioneOperazioneInputparameters3);
	}

	//  1.9 Action 37: Ricezione moduli in bianco da TCR ( Reception of blank modules from TCR ) for credit card Second scenario

	public EsecuzioneOperazioneOutputData esecuzioneOperazione2(CreditCard creditcard) throws WZApplicationException_Exception {
		EsecuzioneOperazioneInputData esecuzioneOperazioneParameters2 = new EsecuzioneOperazioneInputData();
		EsecuzioneOperazioneOutputData esecuzioneOperazione = null;
		try {
			AtmInfoT atmInfoT1 = new AtmInfoT();

			/*atmInfoT1.setIdPostazione(creditcard.getIdPostazione());
			atmInfoT1.setRequestId(creditcard.getRequestId());
			esecuzioneOperazioneParameters2.setAtminfo(atmInfoT1);
			if (!StringUtils.isEmpty(creditcard.getCdg())) {
				esecuzioneOperazioneParameters2.setCdg(creditcard.getCdg());
			}

			FiltroModuliInBiancoT filtroModuliInBianco = new FiltroModuliInBiancoT();
			
			if(!StringUtils.isEmpty(creditcard.getSerieAssegni())){
				filtroModuliInBianco.setSerieAssegni(creditcard.getSerieAssegni());
			}

			if (!StringUtils.isEmpty(creditcard.getFilAssegno())) {
				filtroModuliInBianco.setFilAssegno(creditcard.getFilAssegno());
			}

			if (!StringUtils.isEmpty(creditcard.getCabAssegno())){
				filtroModuliInBianco.setCabAssegno(creditcard.getCabAssegno());
			}


			if (!StringUtils.isEmpty(creditcard.getNrPrimoAssegno())){
				filtroModuliInBianco.setNrPrimoAssegno(creditcard.getNrPrimoAssegno());
			}

			if (!StringUtils.isEmpty(creditcard.getNrUltimoAssegno())) {
				filtroModuliInBianco.setNrUltimoAssegno(creditcard.getNrUltimoAssegno());
			}

			if (!StringUtils.isEmpty(creditcard.getNrAssegni())) {
				filtroModuliInBianco.setNrAssegni(creditcard.getNrAssegni());
			}
			if (!StringUtils.isEmpty(creditcard.getCinPrimoAssegno())) {
				filtroModuliInBianco.setCinPrimoAssegno(creditcard.getCinPrimoAssegno());
			}

			OperationInfoT operationInfoT=new OperationInfoT();
				operationInfoT.setIdAzione(CREDITCARD_LOADING_ACTION);	
				
			if (StringUtils.isEmpty(creditcard.getTipoAzione())){
				operationInfoT.setTipoAzione(TipoAzioneT.valueOf(creditcard.getTipoAzione()));
			}

			esecuzioneOperazioneParameters2.setOperinfo(operationInfoT);
*/

			/*// Removed according to latest WSDL
			RapportoT rapporto=new RapportoT();
			if(!StringUtils.isEmpty(header.get("codCategoria"))){
				rapporto.setCodCategoria(header.get("codCategoria")));
			}

			if(!StringUtils.isEmpty(header.get("codGruppo")))
			{
				rapporto.setCodGruppo(header.get("codGruppo")));
			}

			if(!StringUtils.isEmpty(header.get("numRapporto"))){
				rapporto.setNumRapporto(header.get("numRapporto")));
			}

			if(!StringUtils.isEmpty(header.get("codFilialeAssegnaz"))){
				rapporto.setCodFilialeAssegnaz(header.get("codFilialeAssegnaz")));
			}*/
			/*
			header.forEach((key, value) -> {
				LOG.info(String.format("Header '%s' = %s", key, value));
			});*/

			LOG.info("Making call to CSE with parameter : ", esecuzioneOperazioneParameters2);
			System.out.println("bbb    " + bgPointService);
			if (bgPointService == null) {
				bgPointService = getBgPointObject();
			}
			esecuzioneOperazione = bgPointService.esecuzioneOperazione(esecuzioneOperazioneParameters2);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return esecuzioneOperazione;
	}





	// 1.10 Azione 41: Rilascio carta bancomat ( ATM card release )for credit card third scenario

	public EsecuzioneOperazioneOutputData esecuzioneOperazione3(CreditCard creditCard)throws WZApplicationException_Exception {
		EsecuzioneOperazioneInputData esecuzioneOperazioneParameters3 = new EsecuzioneOperazioneInputData();
		AtmInfoT atmInfoT1 = new AtmInfoT();

		/*atmInfoT1.setIdPostazione(creditCard.getIdPostazione());
		atmInfoT1.setRequestId(creditCard.getRequestId());
		esecuzioneOperazioneParameters3.setAtminfo(atmInfoT1);

		if (!StringUtils.isEmpty(creditCard.getCdg())) {
			esecuzioneOperazioneParameters3.setCdg(creditCard.getCdg());			
		}

		OperationInfoT operationInfoT = new OperationInfoT();

			operationInfoT.setIdAzione(CREDITCARD_DELIVERY_ACTION);
		esecuzioneOperazioneParameters3.setOperinfo(operationInfoT);

		RapportoT rapporto = new RapportoT();
		if(!StringUtils.isEmpty(creditCard.getCodCategoria())){
			rapporto.setCodCategoria(creditCard.getCodCategoria());
		}

		if(!StringUtils.isEmpty(creditCard.getCodGruppo())){
			rapporto.setCodGruppo(creditCard.getCodGruppo());
		}

		if(!StringUtils.isEmpty(creditCard.getNumRapporto())){
			rapporto.setNumRapporto(creditCard.getNumRapporto());
		}
		esecuzioneOperazioneParameters3.setRapporto(rapporto);

		RichiestaCartaBancomatInput chiaveRichiestaCarta = new RichiestaCartaBancomatInput();
		if(!StringUtils.isEmpty(creditCard.getCodProd())){
			chiaveRichiestaCarta.setCodProd(creditCard.getCodProd());
		}

		if(!StringUtils.isEmpty(creditCard.getCodAnagrafico())){
			chiaveRichiestaCarta.setCodAnagrafico(creditCard.getCodAnagrafico());
		}
		if(!StringUtils.isEmpty(creditCard.getAzione())){
			chiaveRichiestaCarta.setAzione(creditCard.getAzione());
		}
		esecuzioneOperazioneParameters3.setChiaveOperazioneSportello("chiaveRichiestaCarta");
*/		//chequebook.forEach((key, value) -> {
		//LOG.info(String.format("Header '%s' = %s", key, value));
		//});
		LOG.info("Making call to CSE with parameter : ", esecuzioneOperazioneParameters3);
		System.out.println("bbb    " + bgPointService);
		if (bgPointService == null) {
			bgPointService = getBgPointObject();
		}
		return bgPointService.esecuzioneOperazione(esecuzioneOperazioneParameters3);
	}
}
