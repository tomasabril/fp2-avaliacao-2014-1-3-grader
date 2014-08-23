
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import utfpr.ct.dainf.if62c.avaliacao.Credor;
import utfpr.ct.dainf.if62c.avaliacao.CredorComparator;
import utfpr.ct.dainf.if62c.avaliacao.ProcessaPagamento;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class Avaliacao3 {
 
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Obtem o caminho para o arquivo de teste dentro da estrutura de diretórios
        // da aplicação.
        String arquivo = ClassLoader.getSystemClassLoader().getResource("credores.txt").getFile();
        System.out.println("LISTA DE CREDORES");
        ProcessaPagamento pp1 = new ProcessaPagamento(arquivo);
        List<Credor> cList = pp1.getCredorList();
        cList.sort(new CredorComparator());
        for (Credor c: cList) {
            System.out.println(c);
        }
        
        System.out.println("\nBUSCA DE CREDORES");
        ProcessaPagamento pp2 = new ProcessaPagamento(arquivo);
        Map<Long, Credor> cMap = pp2.getCredorMap();
        Long cpf = 0L;
        Scanner scn = new Scanner(System.in);
        do {
            System.out.print("CPF do credor: ");
            if (scn.hasNextLong()) {
                cpf = scn.nextLong();
                Credor c = cMap.get(cpf);
                if (c == null)
                    System.out.println("CPF inexistente");
                else
                    System.out.println(c);
            } else {
                scn.next();
                System.out.println("Por favor, informe um valor numérico");
            }
        } while (cpf > 0); 
    }
 
}