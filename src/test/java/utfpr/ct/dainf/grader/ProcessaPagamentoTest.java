package utfpr.ct.dainf.grader;

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
import utfpr.ct.dainf.if62c.avaliacao.Credor;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class ProcessaPagamentoTest {
    private BufferedReader reader;

    public ProcessaPagamentoTest(File arquivo) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(arquivo));
    }

    public ProcessaPagamentoTest(String path) throws FileNotFoundException {
        this(new File(path));
    }
    
    public String getNextLine() throws IOException {
        return reader.readLine();
    }
    
    public CredorTest processaLinha(String linha) {
        Long cpf = Long.valueOf(linha.substring(0, 11));
        String nome = linha.substring(11, 71).trim();
        Double valor = Long.valueOf(linha.substring(71, 83)) / 100.0;
        GregorianCalendar gc = new GregorianCalendar(
                Integer.parseInt(linha.substring(83, 87)),
                Integer.parseInt(linha.substring(87, 89))-1,
                Integer.parseInt(linha.substring(89, 91)));
        Date data = gc.getTime();
        return new CredorTest(cpf, nome, valor, data);
    }
    
    public CredorTest getNextCredor() throws IOException {
        String linha = getNextLine();
        return linha == null ? null : processaLinha(linha);
    }
    
    public List<CredorTest> getUnorderedCredorList() throws IOException {
        List<CredorTest> credores = new ArrayList<>();
        CredorTest c;
        try {
            while ((c = getNextCredor()) != null) {
                credores.add(c);
            }
        } finally {
            reader.close();
        }
        return credores;
    }
    
    public List<CredorTest> getOrderedCredorList() throws IOException {
        List<CredorTest> credores = new ArrayList<>();
        CredorTest c;
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
    
    public static boolean isSameCredorList(List<CredorTest> testList, List<Credor> credorList) {
        boolean same = testList.size() == credorList.size();
        int i = 0;
        while (same && i < testList.size()) {
            same = testList.get(i).equalsLenient(credorList.get(i));
            i++;
        }
        return same;
    }
    
    public static boolean isSameCredorMap(List<CredorTest> testList, Map<Long, Credor> credorMap) {
        boolean same = testList.size() == credorMap.size();
        int i = 0;
        while (same && i < testList.size()) {
            Credor c = credorMap.get(testList.get(i).getCpf());
            same = c != null && testList.get(i).equalsLenient(c);
            i++;
        }
        return same;
    }
    
    public Map<Long, CredorTest> getCredorMap() throws IOException {
        HashMap<Long, CredorTest> credores = new HashMap<>();
        CredorTest c;
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
