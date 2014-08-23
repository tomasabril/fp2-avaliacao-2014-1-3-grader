package utfpr.ct.dainf.grader;

import java.util.Comparator;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class CredorComparatorTest implements Comparator<CredorTest> {
    
    @Override
    public int compare(CredorTest o1, CredorTest o2) {
        return o1.getData().compareTo(o2.getData());
    }
    
}
