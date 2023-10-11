package br.com.overflow.intermediario.persistencia;

import java.math.BigDecimal;
import java.sql.ResultSet;

import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class PersistenciaCamadaDeBancoDeDados {


	//READ
	
	public void ExemploBuscaDeRegistrosSQL( BigDecimal nunota) throws Exception {
		JdbcWrapper jdbc = null;
	    try {
	      jdbc = EntityFacadeFactory.getDWFFacade().getJdbcWrapper();
	      NativeSql sql = new NativeSql(jdbc);
	      
	      	/*
	       
		        sql.appendSql("SUA QUERY AQUI");
		       
		        OBS: NUNCA utilizar SELECT * nas queries desse metodo, pois ele ira carregar todas as Colunas da tabela sem necessidade.
		       
		        sql.setNamedParameter("NOME_VARIAVEL_NA_QUERY", variavel);
	        
	        
	        */
	      sql.appendSql("SELECT COUNT(*) AS QTD TGFITE WHERE NUNOTA = :NUNOTA");
	      
	      sql.setNamedParameter("NUNOTA", nunota);
	      

	      ResultSet resultSet = sql.executeQuery();
	      while (resultSet.next()) {
	    	  
	    	  /*
				
				vc pode acessar os valoraes das colunas da seguinte forma:
				
				resultSet.getBigDecimal("NOMEDACOLUNA");
	    	  	resultSet.getString("NOMEDACOLUNA");
	    	  	resultSet.getTimestamp("NOMEDACOLUNA");
		
	    	   */

	      } 
	    } finally {
	      jdbc.closeSession();
	    }
	}
	
	//CRATE - UPDATE - DELETE
	
	public void ExemploUpdateInsertDeleteSQL( BigDecimal nunota) throws Exception {
		JdbcWrapper jdbc = null;
	    try {
	      jdbc = EntityFacadeFactory.getDWFFacade().getJdbcWrapper();
	      NativeSql sql = new NativeSql(jdbc);
	      
	      	/*
	       
		        sql.appendSql("SEU UPDATE, INSERT ou DELETE AQUI");
		        
		        sql.setNamedParameter("NOME_VARIAVEL_NA_QUERY", variavel);
	        
	        
	        */
	      sql.appendSql("UPDATE TGFCAB SET VLRNOTA = 123 WHERE NUNOTA = :NUNOTA");
	      
	      sql.setNamedParameter("NUNOTA", nunota);
	      
	      //Executa Update ou o Delete, dependendo da instrucao
	      sql.executeUpdate();

	    } finally {
	      jdbc.closeSession();
	    }
	}
	
	//READ
	
	public void ExemploBuscaComQueryGrande( BigDecimal nunota) throws Exception {
		JdbcWrapper jdbc = null;
	    try {
	      jdbc = EntityFacadeFactory.getDWFFacade().getJdbcWrapper();
	      NativeSql sql = new NativeSql(jdbc);
	      
	      	/*
	       
		        sql.appendSql("SUA QUERY AQUI");
		       
		        OBS: NUNCA utilizar SELECT * nas queries desse metodo, pois ele ira carregar todas as Colunas da tabela sem necessidade.
		       
		        sql.setNamedParameter("NOME_VARIAVEL_NA_QUERY", variavel);
	        
	        
	        */
	      sql.loadSql(PersistenciaCamadaDeBancoDeDados.class, "exemploQueryGrande.sql");
	      
	      sql.setNamedParameter("NUNOTA", nunota);
	      

	      ResultSet resultSet = sql.executeQuery();
	      while (resultSet.next()) {
	    	  
	    	  /*
				
				vc pode acessar os valoraes das colunas da seguinte forma:

				resultSet.getBigDecimal("NOMEDACOLUNA");
	    	  	resultSet.getString("NOMEDACOLUNA");
	    	  	resultSet.getTimestamp("NOMEDACOLUNA");
		
	    	   */

	      } 
	    } finally {
	      jdbc.closeSession();
	    }
	}
	

	//READ
	
	public BigDecimal ExemploBuscaSimplificadaSQL( BigDecimal nuNota) throws Exception {
		
		return NativeSql.getBigDecimal("COUNT(*)", "TGFITE", "NUNOTA = ?", new Object[] {nuNota});
		
	}
	
	
}
