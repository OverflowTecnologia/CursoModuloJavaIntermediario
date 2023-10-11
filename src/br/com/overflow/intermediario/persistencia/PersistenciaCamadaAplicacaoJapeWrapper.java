package br.com.overflow.intermediario.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.bmp.PersistentLocalEntity;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.util.FinderWrapper;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.DynamicEntityNames;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class PersistenciaCamadaAplicacaoJapeWrapper {
	
	//READ
	
	public void ExemploBuscaDeRegistroUnicoJapeWrapper(BigDecimal nuNota) throws Exception {
		SessionHandle hnd = null;
		try {

			hnd = JapeSession.open();
			JapeWrapper empresaDAO = JapeFactory.dao("ItemNota");
			Collection<DynamicVO> dynamicVOs = empresaDAO.find("NUNOTA =  ?",nuNota);
			for(DynamicVO dynamicVO : dynamicVOs) {
				
				/*
					Dentro da variavel dynamicVO esta todas as colunas do registro que voce consultou.
					vc pode acessar da seguinte forma :
					
					dynamicVO.asBigDecimal("NOMEDACOLUNA");
					dynamicVO.asString("NOMEDACOLUNA");
					dynamicVO.asTimestamp("NOMEDACOLUNA");
			
				 */
				
				BigDecimal codprod = dynamicVO.asBigDecimal("CODPROD");
				
			}
		} finally {
			JapeSession.close(hnd);
		}
		
	}

	//UPDATE
	
	public void ExemploAtualizacaoJapeWrapper(BigDecimal nufin, BigDecimal codTipTit, Timestamp dtVenc) throws Exception {
		SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();
			JapeWrapper fechamentoDAO = JapeFactory.dao("Financeiro");
			
			fechamentoDAO.prepareToUpdateByPK(nufin)
				.set("CODTIPTIT", codTipTit)
				.set("DTVENC", dtVenc)
				.update();
			
		} finally {
			JapeSession.close(hnd);
		}
		
	}
	
	//CREATE
	
	public void ExemploCriacaoDeRegistroJapeWrapper(BigDecimal codParc,BigDecimal vlrCustoAgrupamento,BigDecimal codprod,BigDecimal codEmp) throws Exception {
		
		SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();

			JapeWrapper regitroDAO = JapeFactory.dao("AD_ATUALCUSPROD");
			regitroDAO.create()
				.set("VLRCUSTO", vlrCustoAgrupamento)		
				.set("CODPROD", codprod)	
				.set("CODEMP", codEmp)	
				.save();
			
		} finally {
			JapeSession.close(hnd);
		}
		
	}


}
