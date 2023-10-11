package br.com.overflow.intermediario.persistenciaEquipamento;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.bmp.PersistentLocalEntity;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class AtualizaEquipamento implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {


		String nomeEquipamento = (String) contexto.getParam("NOMEEQUIP");
		
		Registro[] linhas = contexto.getLinhas();
		
		for(Registro linha : linhas) {
			
			BigDecimal idEquipamento = (BigDecimal) linha.getCampo("IDEQUIP");
			
			atualizaEquipamento(idEquipamento,nomeEquipamento);
			
		}

	}
	
	
	public void atualizaEquipamento(BigDecimal idEquipamento, String nome) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

			PersistentLocalEntity persistentLocalEntity = dwfFacade.findEntityByPrimaryKey ("AD_EQUIP", new Object[]{idEquipamento});
			DynamicVO dynamicVO = (DynamicVO) persistentLocalEntity.getValueObject();

			dynamicVO.setProperty("NOME", nome);
		
			persistentLocalEntity.setValueObject((EntityVO) dynamicVO);
		
		} finally {
			JapeSession.close(hnd);
		}
		
	}

}
