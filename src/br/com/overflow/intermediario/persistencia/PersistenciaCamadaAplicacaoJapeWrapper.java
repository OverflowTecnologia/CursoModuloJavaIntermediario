package br.com.overflow.intermediario.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.DynamicEntityNames;

public class PersistenciaCamadaAplicacaoJapeWrapper {
	
	//CREATE
	
	public void exemploCriacaoDeRegistroJapeWrapper(BigDecimal codParc,BigDecimal vlrCustoAgrupamento,BigDecimal codprod,BigDecimal codEmp) throws Exception {
		
		SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();

			JapeWrapper regitroDAO = JapeFactory.dao("AD_ATUALCUSPROD"); //instancia a tabela em questão
			regitroDAO.create()
				.set("VLRCUSTO", vlrCustoAgrupamento) // o metodo .set() insere os valores dentro da coluna informada		
				.set("CODPROD", codprod)	
				.set("CODEMP", codEmp)	
				.save(); // funcao para salvar o registro
			
		} finally {
			JapeSession.close(hnd);
		}
		
	}

	
	//READ
	
	public void exemploBuscaDeRegistroPKJapeWrapper(BigDecimal codEmp) throws Exception {

		JapeSession.SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();
			JapeWrapper empresaDAO = JapeFactory.dao(DynamicEntityNames.EMPRESA); //instancia a tabela em questão
			DynamicVO dynamicVO = empresaDAO.findByPK(codEmp);
			
			/*
				Dentro da variavel dynamicVO esta todas as colunas do registro que voce consultou.
				vc pode acessar da seguinte forma :
				
				dynamicVO.asBigDecimal("NOMEDACOLUNA");
				dynamicVO.asString("NOMEDACOLUNA");
				dynamicVO.asTimestamp("NOMEDACOLUNA");
		
			 */
			
			dynamicVO.asString("RAZAOSOCIAL");
			
		} finally {
			JapeSession.close(hnd);
		}
	}
	
	//READ
	
	public void exemploBuscaVariosRegistroJapeWrapper(String nomeEmpresa) throws Exception {

		SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();
			JapeWrapper empresaDAO = JapeFactory.dao(DynamicEntityNames.EMPRESA); //instancia a tabela em questão
			Collection<DynamicVO> dynamicVOs = empresaDAO.find("RAZAOSOCIAL = ?", nomeEmpresa);
			for (DynamicVO dynamicVO : dynamicVOs) {

				/*
					Dentro da variavel dynamicVO esta todas as colunas dos registros que voce consultou.
					vc pode acessar da seguinte forma :
					
					dynamicVO.asBigDecimal("NOMEDACOLUNA");
					dynamicVO.asString("NOMEDACOLUNA");
					dynamicVO.asTimestamp("NOMEDACOLUNA");
			
				 */

				dynamicVO.asString("RAZAOSOCIAL");
				
			}
		} finally {
			JapeSession.close(hnd);
		}
	}
	

	
	//UPDATE
	
	public void exemploAtualizacaoJapeWrapper(BigDecimal nufin, BigDecimal codTipTit, Timestamp dtVenc) throws Exception {
		SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();
			JapeWrapper financeiroDAO = JapeFactory.dao("Financeiro"); //instancia a tabela em questão
			
			financeiroDAO.prepareToUpdateByPK(nufin)
				.set("CODTIPTIT", codTipTit) // o metodo .set() insere os valores dentro da coluna informada
				.set("DTVENC", dtVenc)
				.update(); // função que executa o Update
			
		} finally {
			JapeSession.close(hnd);
		}
		
	}
	
	//DELETE	

	public void exemploExclusaoPKJapeWrapper(BigDecimal nufin) throws Exception {
	
		SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();
			JapeWrapper empresaDAO = JapeFactory.dao(DynamicEntityNames.FINANCEIRO); //instancia a tabela em questão
			empresaDAO.delete(nufin); //função que executa o Delete com base na PK do registro
		} finally {
			JapeSession.close(hnd);
		}
	}

	//DELETE		
	
	public void exemploExclusaoVariosRegistrosJapeWrapper(BigDecimal codParc, Timestamp dtVenc) throws Exception {	
		SessionHandle hnd = null;
	
		try {
			hnd = JapeSession.open();
			
			JapeWrapper configDao = JapeFactory.dao(DynamicEntityNames.FINANCEIRO); //instancia a tabela em questão
			configDao.deleteByCriteria("this.CODPARC = ? AND this.DTIVENC >= ?", codParc, dtVenc); // função que executa o Delete com base na query
		
		} finally {
			JapeSession.close(hnd);
		}
	}
	

}
