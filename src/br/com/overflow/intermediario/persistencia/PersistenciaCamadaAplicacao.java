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
import br.com.sankhya.modelcore.util.DynamicEntityNames;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class PersistenciaCamadaAplicacao {
	
	//CREATE

	public void exemploCriacaoDeRegistro(String nome, BigDecimal codParc) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

			DynamicVO dynamicVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance(DynamicEntityNames.FINANCEIRO);
			
			
			/*
			Dentro da variavel dynamicVO esta toda relacionada a Tabela que voce Instanciou para criar o registro.
			Para que voce gere um registro dentro dela é necessario que você informe os campos como o Exemplo abaixo.
			
			OBS: não é necessario passar o ID da Tabela pois ele sera gerado automaticamente pelo Sankhya.
			
			dynamicVO.setProperty("DTNEG", dtNeg);
			dynamicVO.setProperty("DTVENC", dtVenc);
			dynamicVO.setProperty("CODEMP", codEmp);
			dynamicVO.setProperty("CODPARC", codParc);
			dynamicVO.setProperty("CODTIPOPER", codTipOper);
			dynamicVO.setProperty("CODTIPTIT", codTipTit);
			dynamicVO.setProperty("CODCTABCO", codCtaBco);
			dynamicVO.setProperty("VLRDESDOB", vlrDesdob);
	
		 */

			dwfFacade.createEntity(DynamicEntityNames.FINANCEIRO, (EntityVO) dynamicVO);

		} finally {
			JapeSession.close(hnd);
		}
		
	}

	//READ
	
	public void exemploBuscaDeRegistroUnico(BigDecimal codParc) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
			PersistentLocalEntity persistentLocalEntity = dwfFacade.findEntityByPrimaryKey("Parceiro", new Object[] {codParc});
			EntityVO entityVO = persistentLocalEntity.getValueObject();
			DynamicVO dynamicVO = (DynamicVO) entityVO;
			
			/*
				Dentro da variavel dynamicVO esta todas as colunas do registro que voce consultou.
				vc pode acessar da seguinte forma :
				
				dynamicVO.asBigDecimal("NOMEDACOLUNA");
				dynamicVO.asString("NOMEDACOLUNA");
				dynamicVO.asTimestamp("NOMEDACOLUNA");
			
			*/
		
		} finally {
			JapeSession.close(hnd);
		}
		
	}
	
	//READ
	
	public void exemploBuscaDeVariosRegistros(BigDecimal codParc, Timestamp dtVenc) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
			FinderWrapper finderWrapper = new FinderWrapper(DynamicEntityNames.FINANCEIRO, "this.CODPARC = ? AND this.DTVENC > ?", new Object[]{codParc, dtVenc});
			Collection<DynamicVO> colletion = dwfFacade.findByDynamicFinderAsVO(finderWrapper);
			
			for(DynamicVO dynamicVO : colletion) {
				
				/*
					Dentro da variavel dynamicVO esta todas as colunas de cada registro que voce consultou.
					vc pode acessar da seguinte forma :

					dynamicVO.asBigDecimal("NOMEDACOLUNA");
					dynamicVO.asString("NOMEDACOLUNA");
					dynamicVO.asTimestamp("NOMEDACOLUNA");
			
				 */
				
			}
			
		
		} finally {
			JapeSession.close(hnd);
		}
		
	}
	
	//UPDATE
	
	public void exemploAtualizacaoDeRegistroUnico(BigDecimal nufin) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

			PersistentLocalEntity persistentLocalEntity = dwfFacade.findEntityByPrimaryKey (DynamicEntityNames.FINANCEIRO, new Object[]{nufin});
			DynamicVO dynamicVO = (DynamicVO) persistentLocalEntity.getValueObject();
			
			
			/*
				Dentro da variavel dynamicVO esta todas as colunas do registro que voce consultou.
				vc pode atualizar da seguinte forma :
				
				dynamicVO.setProperty("NOMEDACOLUNA", valorDaVarriavel);
				
				EX:
				
				dynamicVO.setProperty("CODTIPTIT", codTipTit);
				dynamicVO.setProperty("DTVENC", dtVenc);
	
			 */

			//Executa a Atualizacao
			persistentLocalEntity.setValueObject((EntityVO) dynamicVO);
		
		} finally {
			JapeSession.close(hnd);
		}
		
	}
	
	//UPDATE
	
	public void exemploAtualizacaoDeVariosRegistros(BigDecimal codParc, Timestamp dtVenc) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

			FinderWrapper finderWrapper = new FinderWrapper(DynamicEntityNames.FINANCEIRO, "this.CODPARC = ? AND this.DTVENC > ?", new Object[]{codParc, dtVenc});
			Collection<PersistentLocalEntity> persistentLocalEntityCollletion = dwfFacade.findByDynamicFinder(finderWrapper);
			
			for(PersistentLocalEntity persistentLocalEntity : persistentLocalEntityCollletion) {
				
				DynamicVO dynamicVO = (DynamicVO) persistentLocalEntity.getValueObject();
				
				/*
					Dentro da variavel dynamicVO esta todas as colunas de cada registro que voce consultou.
					vc pode atualizar da seguinte forma :
					
					dynamicVO.setProperty("NOMEDACOLUNA", valorDaVarriavel);
					
					EX:
					
					dynamicVO.setProperty("CODTIPTIT", codTipTit);
					dynamicVO.setProperty("DTVENC", dtVenc);
	
				 */
				
				//Executa a Atualizacao um a um
				persistentLocalEntity.setValueObject((EntityVO) dynamicVO);
			}
			
		} finally {
			JapeSession.close(hnd);
		}
		
	}
	
	//DELETE
	
	public void exemploExclusaoDeRegistroUnico(BigDecimal nuFin) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
			dwfFacade.removeEntity(DynamicEntityNames.FINANCEIRO, new Object[]{nuFin});

		} finally {
			JapeSession.close(hnd);
		}
		
	}
	
	//DELETE
	
	public void exemploExclusaoDeVariosRegistro(BigDecimal codParc,Timestamp dtVenc) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
			FinderWrapper finder = new FinderWrapper(DynamicEntityNames.FINANCEIRO, "this.CODPARC = ? AND this.DTIVENC >= ?", new Object[]{codParc, dtVenc});
			dwfFacade. removeByCriteria(finder);

		} finally {
			JapeSession.close(hnd);
		}
		
	}


}
