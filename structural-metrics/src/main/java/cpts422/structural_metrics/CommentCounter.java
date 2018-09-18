package cpts422.structural_metrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

public class CommentCounter extends AbstractCheck {
	private int numComments;
	private int numCommentLines;
	
	public void countComments(String code) {
		
	}
	
	public void countLines(String code) {
		
	}
	
	public int getNumComments() {
		return numComments;
	}
	
	public int getCommentLines() {
		return numCommentLines;
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return null;
	}
}
