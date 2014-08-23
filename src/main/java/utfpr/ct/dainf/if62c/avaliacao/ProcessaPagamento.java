package utfpr.ct.dainf.if62c.avaliacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class ProcessaPagamento {
    private BufferedReader reader;

    public ProcessaPagamento(File arquivo) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(arquivo));
    }

    public ProcessaPagamento(String path) throws FileNotFoundException {
        this(new File(path));
    }
    
    private String getNextLine() throws IOException {
        return reader.readLine();
    }
    
    private Credor processaLinha(String linha) {
        Long cpf = Long.valueOf(linha.substring(0, 11));
        String nome = linha.substring(11, 71).trim();
        Double valor = Long.valueOf(linha.substring(71, 83)) / 100.0;
        GregorianCalendar gc = new GregorianCalendar(
                Integer.parseInt(linha.substring(83, 87)),
                Integer.parseInt(linha.substring(87, 89)),
                Integer.parseInt(linha.substring(89, 91)));
        Date data = gc.getTime();
        return new Credor(cpf, nome, valor, data);
    }
    
    private Credor getNextCredor() throws IOException {
        String linha = getNextLine();
        return linha == null ? null : processaLinha(linha);
    }
    
    public List<Credor> getCredorList() throws IOException {
        List<Credor> credores = new ArrayList<>();
        Credor c;
        try {
            while ((c = getNextCredor()) != null) {
                credores.add(c);
            }
        } finally {
            reader.close();
        }
        Collections.sort(credores);
        return credores;
    }
    
    public Map<Long, Credor> getCredorMap() throws IOException {
        HashMap<Long, Credor> credores = new HashMap<>();
        Credor c;
        try {
            while ((c = getNextCredor()) != null) {
                credores.put(c.getCpf(), c);
            }
        } finally {
            reader.close();
        }
        return credores;
    }
}
