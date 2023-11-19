package br.com.overflow.intermediario.persistenciaEquipamento;

import java.util.Collection;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.bmp.PersistentLocalEntity;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.util.FinderWrapper;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class AtualizacaoVariosRegistros implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {


		String nomeEquipamento = (String) contexto.getParam("NOMEEQUIP");
		
		exemploAtualizacaoDeVariosRegistros(nomeEquipamento);

	}
	
	
	public void exemploAtualizacaoDeVariosRegistros(String nomeEquipamento) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

			FinderWrapper finderWrapper = new FinderWrapper("AD_EQUIP", "SETOR = ?", new Object[]{"C"});
			Collection<PersistentLocalEntity> persistentLocalEntityCollletion = dwfFacade.findByDynamicFinder(finderWrapper);
			
			for(PersistentLocalEntity persistentLocalEntity : persistentLocalEntityCollletion) {
				
				DynamicVO dynamicVO = (DynamicVO) persistentLocalEntity.getValueObject();
				

				dynamicVO.setProperty("NOME", nomeEquipamento);

				persistentLocalEntity.setValueObject((EntityVO) dynamicVO);
			}
			
		} finally {
			JapeSession.close(hnd);
		}
		
	}

}
