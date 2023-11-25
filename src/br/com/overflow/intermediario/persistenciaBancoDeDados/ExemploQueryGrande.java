package br.com.overflow.intermediario.persistenciaBancoDeDados;

import java.math.BigDecimal;
import java.sql.ResultSet;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.util.JapeSessionContext;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class ExemploQueryGrande implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {
		
		String stringRetorno = exemploBuscaComQueryGrande();
		
		BigDecimal codusu = (BigDecimal)JapeSessionContext.getProperty("usuario_logado");
		
		String nomeUsuarioLogado = buscaNomeUsuarioLogado(codusu);
		
		contexto.setMensagemRetorno(stringRetorno + " Parabens " + nomeUsuarioLogado + " Voce concluiu o Modulo de persisitencia do Java no Sankhya.");

	}
	


	public String exemploBuscaComQueryGrande( ) throws Exception {
		
		String string = "";
		JdbcWrapper jdbc = null;
	    try {
	      jdbc = EntityFacadeFactory.getDWFFacade().getJdbcWrapper();
	      NativeSql sql = new NativeSql(jdbc);

	      sql.loadSql(ExemploQueryGrande.class, "exemploQueryGrande.sql");
	      
	      sql.setNamedParameter("VALOR", new BigDecimal(5000));
	      
	      ResultSet resultSet = sql.executeQuery();
	      while (resultSet.next()) {
	    	  
	    	  string = string + " NOME:" + resultSet.getString("NOME") + " VALOR:"+resultSet.getBigDecimal("VALOR");

	      } 
	      
	      return string;
	    } finally {
	      jdbc.closeSession();
	    }
	}
	
	
	private String buscaNomeUsuarioLogado(BigDecimal codusu) throws Exception {	
		
		return NativeSql.getString("NOMEUSU", "TSIUSU", "CODUSU = ?", new Object[]{codusu});
		
	}

}
