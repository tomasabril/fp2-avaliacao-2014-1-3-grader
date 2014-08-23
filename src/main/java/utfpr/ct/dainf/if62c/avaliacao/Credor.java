package utfpr.ct.dainf.if62c.avaliacao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class Credor implements Comparable<Credor> {
    private Long cpf;
    private String nome;
    private Double valor;
    private Date data;
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public Credor(Long cpf, String nome, Double valor, Date data) {
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

    @Override
    public String toString() {
        return String.format("%011d%60s%012d%s", cpf, nome,
                Math.round(valor * 100), dateFormat.format(data));
    }    

    @Override
    public int compareTo(Credor credor) {
        return cpf.compareTo(credor.cpf);
    }  
    
}
