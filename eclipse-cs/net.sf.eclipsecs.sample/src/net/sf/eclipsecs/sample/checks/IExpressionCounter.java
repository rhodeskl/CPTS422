package net.sf.eclipsecs.sample.checks;

import java.util.ArrayList;
import java.util.List;

import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public interface ExpressionCounter {
   
  public int getNumExpressions();
  public int getNumOperators();
  public int getNumOperands();
  public int getNumUniqueOperators();
  public int getNumUniqueOperands();
}
