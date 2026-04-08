// Generated from C:/Users/jk123/Desktop/pliki_studia/CrochetLingo/backend/src/main/antlr4/com/example/crochetLingo/antlr/Crochet.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CrochetParser}.
 */
public interface CrochetListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CrochetParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(CrochetParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrochetParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(CrochetParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrochetParser#round}.
	 * @param ctx the parse tree
	 */
	void enterRound(CrochetParser.RoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrochetParser#round}.
	 * @param ctx the parse tree
	 */
	void exitRound(CrochetParser.RoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrochetParser#elementList}.
	 * @param ctx the parse tree
	 */
	void enterElementList(CrochetParser.ElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrochetParser#elementList}.
	 * @param ctx the parse tree
	 */
	void exitElementList(CrochetParser.ElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrochetParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(CrochetParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrochetParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(CrochetParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrochetParser#stitch}.
	 * @param ctx the parse tree
	 */
	void enterStitch(CrochetParser.StitchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrochetParser#stitch}.
	 * @param ctx the parse tree
	 */
	void exitStitch(CrochetParser.StitchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrochetParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(CrochetParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrochetParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(CrochetParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrochetParser#repeat}.
	 * @param ctx the parse tree
	 */
	void enterRepeat(CrochetParser.RepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrochetParser#repeat}.
	 * @param ctx the parse tree
	 */
	void exitRepeat(CrochetParser.RepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrochetParser#contextOperation}.
	 * @param ctx the parse tree
	 */
	void enterContextOperation(CrochetParser.ContextOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrochetParser#contextOperation}.
	 * @param ctx the parse tree
	 */
	void exitContextOperation(CrochetParser.ContextOperationContext ctx);
}