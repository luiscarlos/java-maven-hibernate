package javamavenhibernate;



import java.awt.List;

import org.junit.jupiter.api.Test;

import dao.DaoGeneric;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeConexaoBanco() {
		HibernateUtil.geteEntityManager();
	}
	
	@Test
	public void testePersistencia() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setIdade(46);
		pessoa.setLogin("java");
		pessoa.setNome("andre antonio");
		pessoa.setSenha("123");
		pessoa.setSobrenome("antonio");
		pessoa.setEmail("pedrolucas8pe@gmail.com");
		
		
		daoGeneric.salvar(pessoa);
	}
	
	@Test
	public void testeHibernateBuscarId() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGeneric.pesquisar(pessoa);
		System.out.println(pessoa);
		
		pessoa.setIdade(99);
		pessoa.setNome("Nome atualizado hibernate");
		
		daoGeneric.updateMerge(pessoa);
		
		pessoa.setId(2L);
		pessoa = daoGeneric.pesquisar(pessoa);
		System.out.println(pessoa);
	}
	
	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGeneric.pesquisar(pessoa);
		
		
		System.out.println(pessoa);
		pessoa.setNome("atualizado");
		
		System.out.println("------------------");
		
		daoGeneric.updateMerge(pessoa);
		System.out.println(pessoa);
	}
	
	/*
	 * @Test public void testeHibernateBuscarId2() { DaoGeneric<UsuarioPessoa>
	 * daoGeneric = new DaoGeneric<UsuarioPessoa>();
	 * 
	 * UsuarioPessoa pessoa = daoGeneric.pesquisar1(2L, UsuarioPessoa.class);
	 * System.out.println(pessoa); }
	 */

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGeneric.pesquisar(pessoa);
		System.out.println(pessoa);
	}
	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(2L, UsuarioPessoa.class);
		System.out.println(pessoa);
	}
	
	@Test
	public void testeDeletar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(3L, UsuarioPessoa.class);	
		daoGeneric.deletarPorId(pessoa);
	
	}
	
	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		java.util.List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		for(UsuarioPessoa usuarioPessoa: list) {
			System.out.println(usuarioPessoa);
		}
	
	}
	
	@Test
	public void testQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		java.util.List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = 'Luis Carlos'").getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		java.util.List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by nome desc")
				.setMaxResults(2)
				.getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQueryListerParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		java.util.List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = :nome or sobrenome= :sobrenome")
				.setParameter("nome", "Luis Carlos")
				.setParameter("sobrenome", "Oliveira")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		Long somaIdade = (Long) daoGeneric.getEntityManager().
				createQuery(" select sum(u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println("A soma das idades -->" + somaIdade);
	}
}
