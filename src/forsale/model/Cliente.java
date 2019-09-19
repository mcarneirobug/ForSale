package forsale.model;

public class Cliente {
	private Long cliente_id;
	
	private String nome;
	private String telefone;
	private String rg;
	private String cpf;
	
	private String rua;
	private Integer numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String cidade;
	private String uf;
	
	public Cliente() {}
	
	public Cliente(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	
	public Cliente(String nome, 
			String telefone,
			String rg, 
			String cpf, 
			String rua, 
			Integer numero,
			String complemento, 
			String cep, 
			String bairro,
			String cidade, 
			String uf) {
		
		this.nome = nome;
		this.telefone = telefone;
		this.rg = rg;
		this.cpf = rg;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}
	
	public Long getCliente_id() {
		return cliente_id;
	}
	public String getNome() {
		return nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getRg() {
		return rg;
	}
	public String getCpf() {
		return cpf;
	}
	public String getRua() {
		return rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getCep() {
		return cep;
	}
	public String getBairro() {
		return bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setUf(String uf) {
		this.uf = uf;
	} 
}
