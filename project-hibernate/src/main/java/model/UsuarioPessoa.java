package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	/*Nameds querys podem ser chamados em qualquer lugar do sistema de uma maneira muito simples
	 * são usados as querys mais simples ou que englobam todos os dados do banco ex: select u */
	@NamedQuery(name = "UsuarioPessoa.Listar", query = "select u from UsuarioPessoa u"),
	@NamedQuery(name = "UsuarioPessoa.buscaPorNome", query = "select u from UsuarioPessoa u where u.nome = :nome")
})
public class UsuarioPessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)/*Faz o hibernate incrementar automaticamente o id no banco*/
	private Long id;
	
	private String nome;
	private String sobrenome;
	private String email;
	private String login,senha;
	
	/*@OneToMany: Um para muitos
	 * Do lado dos telefones, qual é o atributo responsável por identificar os telefones?
	 * o atributo usuarioPessoa, então colocamos no mapeamento
	 * fetch = FetchType.EAGER: Serve para trazer o telefone sempre que consultar o usuario
	 * ou seja, amarra as tabelas tambem na consulta*/
	@OneToMany(mappedBy = "usuarioPessoa", fetch = FetchType.EAGER)
	private List<TelefoneUser> telefoneuser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
		
	public List<TelefoneUser> getTelefoneuser() {
		return telefoneuser;
	}
	public void setTelefoneuser(List<TelefoneUser> telefoneuser) {
		this.telefoneuser = telefoneuser;
	}
	@Override
	public String toString() {
		return "UsuarioPessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
				+ ", login=" + login + ", senha=" + senha + "]";
	}
	
}
