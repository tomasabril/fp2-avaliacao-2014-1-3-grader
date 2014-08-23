package utfpr.ct.dainf.if62c.avaliacao;

import java.util.Comparator;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class CredorComparator implements Comparator<Credor> {
    
    @Override
    public int compare(Credor o1, Credor o2) {
        return o1.getData().compareTo(o2.getData());
    }
    
}
