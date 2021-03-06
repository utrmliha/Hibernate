package project.hibernate;



import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class AppTest 
{
    @Test
    public void testeSalvar()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        
        UsuarioPessoa pessoa = new UsuarioPessoa();
        
        pessoa.setNome("Igor");
        pessoa.setSobrenome("vinicius");
        pessoa.setLogin("Igor");
        pessoa.setSenha("Igor");
        pessoa.setEmail("emailficticio@gmail.com");
        
        daoGeneric.salvar(pessoa);
    }
    
    @Test
    public void testeBuscar()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = new UsuarioPessoa();
        
        pessoa.setId(2L);
        
        pessoa = daoGeneric.pesquisar(pessoa);
        System.out.println(pessoa);
    }
    
    @Test
    public void testeBuscar2()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
        System.out.println(pessoa);
    }
    
    @Test
    public void testeUpdate()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
        
        pessoa.setNome("Administrador Editado");
        pessoa.setEmail("EmailEditado@gmail.com");
        
        pessoa = daoGeneric.updateMerge(pessoa);
        
        System.out.println(pessoa);
    }
    
    @Test
    public void testeDeletar()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
        
        daoGeneric.deletarPorId(pessoa);
    }
    
    @Test
    public void testeDeletar2()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        UsuarioPessoa pessoa = new UsuarioPessoa();
        
        daoGeneric.deletarPorId(1L, pessoa);
    }

    @Test
    public void testeConsultar()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
        
        for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------");
		}
    }
    
    @Test
    public void testeQueryListMaxResult()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        List<UsuarioPessoa> list = daoGeneric.getEntityManager().
        		createQuery(" from UsuarioPessoa order by id").
        		setMaxResults(2)
        		.getResultList();
        
        for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------");
		}
    }
    
    @Test
    public void testeQueryListParameter()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        List<UsuarioPessoa> list = daoGeneric.getEntityManager().
        		createQuery(" from UsuarioPessoa where nome = :nome").
        		setParameter("nome", "Administrador2")
        		.getResultList();
        
        for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------");
		}
    }
    
    @Test
    public void testeNamedQuery()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        List<UsuarioPessoa> list = daoGeneric.getEntityManager().
        		createNamedQuery("UsuarioPessoa.Listar").
        		getResultList();
        
        for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------");
		}
    }
    
    @Test
    public void testeNamedQuery2()
    {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
       
        List<UsuarioPessoa> list = daoGeneric.getEntityManager().
        		createNamedQuery("UsuarioPessoa.buscaPorNome").
        		setParameter("nome", "Igor").
        		getResultList();
        
        for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------");
		}
    }
    
    @Test
    public void testeGravaTelefone() {
    	DaoGeneric daoGeneric = new DaoGeneric();
    	
    	UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(6L, UsuarioPessoa.class);
    	
    	TelefoneUser telefoneUser = new TelefoneUser();
    	telefoneUser.setTipo("Celular");
    	telefoneUser.setNumero("894984615");
    	telefoneUser.setUsuarioPessoa(pessoa);
    	
    	daoGeneric.salvar(telefoneUser);
    }
    
    @Test
    public void testeConsultaTelefone() {
    	DaoGeneric daoGeneric = new DaoGeneric();
    	
    	UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(4L, UsuarioPessoa.class);
    	
    	for (TelefoneUser fone : pessoa.getTelefoneuser()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("----------------");
		}
    }
}
