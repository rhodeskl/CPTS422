package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class MethodCounter extends AbstractCheck 
{
  
  //External Method references are the number of methods called from an external class
  private int numExternalReferences;
  
  //Local method references are the number of methods called from the same class 
  private int numLocalReferences;
  
  public MethodCounter()
  {
    this.numExternalReferences = 0;
    this.numLocalReferences = 0;
  }
  
  public int getNumExternalReferences( ) 
  {
    return numExternalReferences;
  }
  
  public int getNumLocalReferences() 
  {
    return numLocalReferences;
  }
  
  @Override
  public void beginTree(DetailAST rootAST)
  {
    numExternalReferences = 0;
    numLocalReferences = 0;
  }

  @Override
  public int[] getDefaultTokens() 
  {
    return new int[] { TokenTypes.METHOD_CALL, TokenTypes.DOT};
  }
  
  @Override
  public int[] getAcceptableTokens() 
  {
    return getDefaultTokens();
  }

  @Override
  public int[] getRequiredTokens() 
  {
    return getDefaultTokens();
  }
  
  public void visitToken(DetailAST ast) 
  {
    switch(ast.getType())
    {
      case TokenTypes.METHOD_CALL:
        if(ast.getFirstChild().getType() == TokenTypes.DOT)
        {
          numExternalReferences  += 1;
        }
        else
        {
          numLocalReferences +=1;
        }
    }
  }
  
  @Override
  public void finishTree(DetailAST rootAST) 
  {
    log(rootAST, "externalReferences", numExternalReferences);
    log(rootAST, "localReferences", numLocalReferences);
  }
}
