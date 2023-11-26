package br.com.overflow.intermediario.persistenciaBancoDeDados;

import java.sql.ResultSet;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class BuscaRegistrosBancoDeDados implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {
		
		String setor = (String) contexto.getParam("SETOR");

		String stringRetorno = exemploBuscaDeRegistrosSQL(setor);
		
		contexto.setMensagemRetorno(stringRetorno);

	}
	
	public String exemploBuscaDeRegistrosSQL(String setor) throws Exception {
		
		String string = "";
		
		JdbcWrapper jdbc = null;
	    try {
	      jdbc = EntityFacadeFactory.getDWFFacade().getJdbcWrapper();
	      NativeSql sql = new NativeSql(jdbc);

	      sql.appendSql("SELECT NOME, VALOR FROM AD_EQUIP WHERE SETOR = :SETOR");
	      
	      sql.setNamedParameter("SETOR", setor);
	

	      ResultSet resultSet = sql.executeQuery();
	      while (resultSet.next()) {
	    	  
	    	  string = string + " NOME:" + resultSet.getString("NOME") + " VALOR:"+resultSet.getBigDecimal("VALOR");
	    	  
	
	      } 
	      
	      return string;
	    } finally {
	      jdbc.closeSession();
	    }
	}

}
