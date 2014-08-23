/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utfpr.ct.dainf.grader.support;

import org.testng.Reporter;

/**
 *
 * @author Wilson
 */
public class TestSecurityManager extends SecurityManager {

    public TestSecurityManager() {
    }

    @Override
    public void checkExit(int status) {
        Reporter.log(String.format("Attempt to call exit(%d) blocked", status), true);
        throw new SecurityException(String.format("Attempt to call exit(%d) blocked", status));
    }
    
}
