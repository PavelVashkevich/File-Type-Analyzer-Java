package main.java.analyzer.validators;

import main.java.analyzer.miscellaneous.MessageResourceBundler;
import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class InputAlgorithm implements IParameterValidator {
    @Override
    public void validate(String name, String param) throws ParameterException {
        if (!param.equals("naive") && !param.equals("robin-karp") && !param.equals("knuth-morris-pratt"))
            throw new ParameterException(MessageResourceBundler.WRONG_ALGORITHM);
    }
}
