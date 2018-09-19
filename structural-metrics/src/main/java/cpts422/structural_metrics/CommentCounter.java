package cpts422.structural_metrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import java.util.ArrayList;

public class CommentCounter extends AbstractCheck {
	private int numComments;
	private int numCommentLines;
	private ArrayList<Integer> beginBlockCommentLines;
	private ArrayList<Integer> endBlockCommentLines;
	
	public CommentCounter() {
		this.numCommentLines = 0;
		this.numComments = 0;
		this.beginBlockCommentLines = new ArrayList<Integer>();
		this.endBlockCommentLines = new ArrayList<Integer>();
	}
	
	public int getNumComments() {
		return numComments;
	}
	
	public int getCommentLines() {
		return numCommentLines;
	}
	
	@Override
	public void beginTree(DetailAST rootAST) {
		numComments = 0;
		numCommentLines = 0;
		this.beginBlockCommentLines = new ArrayList<Integer>();
		this.endBlockCommentLines = new ArrayList<Integer>();
	}

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.SINGLE_LINE_COMMENT };
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		switch (ast.getType()) {
		case TokenTypes.BLOCK_COMMENT_BEGIN:
			numComments++;
			beginBlockCommentLines.add(ast.getLineNo());
		case TokenTypes.BLOCK_COMMENT_END:
			endBlockCommentLines.add(ast.getLineNo());
		case TokenTypes.SINGLE_LINE_COMMENT:
			numComments++;
			numCommentLines++;
		}
	}
	
	@Override
	public void finishTree(DetailAST rootAST) {
		for(int i = 0; i < beginBlockCommentLines.size(); i++) {
			Integer lineNoBegin = beginBlockCommentLines.get(i);
			Integer lineNoEnd = endBlockCommentLines.get(i);
			Integer numLines = lineNoEnd - lineNoBegin + 1;
			numCommentLines = numLines + numCommentLines;
		}
		log(rootAST, "Number of comments = " + numComments);
		log(rootAST, "Number of lines of comments = " + numCommentLines);
	}
}
