package utfpr.ct.dainf.grader;

import java.text.SimpleDateFormat;
import java.util.Date;
import utfpr.ct.dainf.if62c.avaliacao.Credor;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class CredorTest implements Comparable<CredorTest> {
    private Long cpf;
    private String nome;
    private Double valor;
    private Date data;
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public CredorTest(Long cpf, String nome, Double valor, Date data) {
        this.cpf = cpf;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public boolean equals(Credor credor) {
        return cpf.equals(credor.getCpf())
                && nome.equals(credor.getNome())
                && valor.equals(credor.getValor())
                && data.equals(credor.getData());
    }

    public boolean equalsLenient(Credor credor) {
        return cpf.equals(credor.getCpf())
                && nome.startsWith(credor.getNome())
                && valor.equals(credor.getValor())
                && data.equals(credor.getData());
    }

    @Override
    public String toString() {
        return String.format("%011d%60s%012d%s", cpf, nome,
                Math.round(valor * 100), dateFormat.format(data));
    }    

    @Override
    public int compareTo(CredorTest credor) {
        return cpf.compareTo(credor.cpf);
    }  
    
}
