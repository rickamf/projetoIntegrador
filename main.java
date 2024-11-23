import java.util.HashMap;
import java.util.Map;

abstract class Pessoa {
private String nome;
private String email;


public Pessoa(String nome, String email) {
    this.nome = nome;
    this.email = email;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

@Override
public String toString() {
    return "Nome: " + nome + ", Email: " + email;
}

}


public PessoaFisica(String nome, String email, String cpf) {
    super(nome, email);
    this.cpf = cpf;
}

public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    this.cpf = cpf;
}

@Override
public String toString() {
    return super.toString() + ", CPF: " + cpf;
}


public PessoaJuridica(String nome, String email, String cnpj) {
    super(nome, email);
    this.cnpj = cnpj;
}

public String getCnpj() {
    return cnpj;
}

public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
}

@Override
public String toString() {
    return super.toString() + ", CNPJ: " + cnpj;
}


public Professor(String nome, String email, String matricula) {
    super(nome, email);
    this.matricula = matricula;
}

public String getMatricula() {
    return matricula;
}

public void setMatricula(String matricula) {
    this.matricula = matricula;
}

@Override
public String toString() {
    return super.toString() + ", Matrícula: " + matricula;
}


public Fornecedor(String nome, String email, String produto) {
    super(nome, email);
    this.produto = produto;
}

public String getProduto() {
    return produto;
}

public void setProduto(String produto) {
    this.produto = produto;
}

@Override
public String toString() {
    return super.toString() + ", Produto: " + produto;
}


public Aluno(String nome, String email, String curso) {
    super(nome, email);
    this.curso = curso;
}

public String getCurso() {
    return curso;
}

public void setCurso(String curso) {
    this.curso = curso;
}

@Override
public String toString() {
    return super.toString() + ", Curso: " + curso;
}


class PessoaServiceImpl implements PessoaService {
private Map<String, Pessoa> registros = new HashMap<>();


@Override
public void cadastrar(Pessoa pessoa) {
    registros.put(pessoa.getNome(), pessoa);
    enviarEmail(pessoa.getEmail(), "Cadastro realizado com sucesso!");
}

@Override
public Pessoa buscar(String nome) {
    return registros.get(nome);
}

@Override
public void editar(String nome, Pessoa pessoaAtualizada) {
    if (registros.containsKey(nome)) {
        registros.put(nome, pessoaAtualizada);
    } else {
        System.out.println("Pessoa não encontrada!");
    }
}

@Override
public void excluir(String nome) {
    registros.remove(nome);
}

private void enviarEmail(String email, String mensagem) {
    
    System.out.println("E-mail enviado para " + email + ": " + mensagem);
}


}

public class Main {
public static void main(String[] args) {
PessoaService service = new PessoaServiceImpl();

    PessoaFisica pf = new PessoaFisica("Henrique Martins", "henrique@email.com", "123.456.789-00");
    PessoaJuridica pj = new PessoaJuridica("Empresa XYZ", "contato@xyz.com", "12.345.678/0001-90");
    Professor prof = new Professor("Maria Oliveira", "maria@univ.com", "PROF1234");
    Fornecedor forn = new Fornecedor("Eduardo Freitas", "eduardo@fornecedor.com", "Livros");
    Aluno aluno = new Aluno("Ana Costa", "ana@aluno.com", "Engenharia");

    service.cadastrar(pf);
    service.cadastrar(pj);
    service.cadastrar(prof);
    service.cadastrar(forn);
    service.cadastrar(aluno);

    System.out.println(service.buscar("João Silva"));
    System.out.println(service.buscar("Empresa XYZ"));

    service.editar("Henrique Martins", new PessoaFisica("Henrique Martins", "henrique@novoemail.com", "123.456.789-00"));
    service.excluir("Empresa XYZ");

    System.out.println(service.buscar("Henrique Martins"));
    System.out.println(service.buscar("Empresa XYZ"));
}

}