package utfpr.ct.dainf.grader.support;

import bsh.EvalError;
import bsh.Interpreter;

/**
 *
 * @author Wilson
 */
public class InterpreterRunner extends Thread {

    private final Interpreter interpreter;
    private final String script;
    private EvalError evalError;
    private Object result;

    public InterpreterRunner(Interpreter bsh, String script) {
        this.interpreter = bsh;
        this.script = script;
        this.setName("interpreter-runner");
    }
    
    @Override
    public void run() {
        try {
            result = interpreter.eval(script);
        } catch (EvalError ex) {
            evalError = ex;
            throw new RuntimeException(ex);
        }
    }

    public Object getResult() {
        return result;
    }

    public EvalError getEvalError() {
        return evalError;
    }
    
    public boolean isError() {
        return evalError != null;
    }
    
}
