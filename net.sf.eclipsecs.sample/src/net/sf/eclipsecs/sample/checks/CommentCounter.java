package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import java.util.ArrayList;

public class CommentCounter extends AbstractCheck {
	private int numComments;
	private int numCommentLines;
	
	public CommentCounter() {
		this.numCommentLines = 0;
		this.numComments = 0;
	}
	
	public int getNumComments() {
		return numComments;
	}
	
	public int getCommentLines() {
		return numCommentLines;
	}
	
	@Override
	public boolean isCommentNodesRequired() {
	  return true;
	}
	
	@Override
	public void beginTree(DetailAST rootAST) {
		numComments = 0;
		numCommentLines = 0;
	}

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		switch (ast.getType()) {
		case TokenTypes.BLOCK_COMMENT_BEGIN:
		  int lineNoStart = ast.getLineNo();
			DetailAST astTemp = ast.findFirstToken(TokenTypes.BLOCK_COMMENT_END);
			if(astTemp != null) {
			  int lineNoEnd = astTemp.getLineNo();
			  int numLines = lineNoEnd - lineNoStart;
			  numCommentLines = numCommentLines + numLines;
			}
		case TokenTypes.SINGLE_LINE_COMMENT:
		  numComments++;
			numCommentLines++;
		}
	}
	
	@Override
	public void finishTree(DetailAST rootAST) {
		log(rootAST, "commentCounter", numComments);
		log(rootAST, "commentLines", numCommentLines);
	}
}
