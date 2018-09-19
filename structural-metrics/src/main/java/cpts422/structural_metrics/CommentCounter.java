package cpts422.structural_metrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CommentCounter extends AbstractCheck {
	private int numComments;
	private int numCommentLines;
	
	public CommentCounter() {
		this.numCommentLines = 0;
		this.numComments = 0;
	}
	
	public void countComments(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
		int numSingleComments = objBlock.getChildCount(TokenTypes.SINGLE_LINE_COMMENT);
		int numBlockComments = objBlock.getChildCount(TokenTypes.BLOCK_COMMENT_BEGIN);
		numComments = numSingleComments + numBlockComments;
		log(ast, "Number of Comments = " + numComments);
	}
	
	public void countLines(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
	}
	
	public int getNumComments() {
		return numComments;
	}
	
	public int getCommentLines() {
		return numCommentLines;
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		countComments(ast);
		countLines(ast);
	}
}
