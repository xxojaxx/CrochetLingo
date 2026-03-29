// Generated from C:/Users/jk123/Desktop/pliki_studia/CrochetLingo/grammar/src/grammar/Crochet.g4 by ANTLR 4.13.2
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CrochetParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CrochetVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CrochetParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(CrochetParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrochetParser#round}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRound(CrochetParser.RoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrochetParser#elementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementList(CrochetParser.ElementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrochetParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(CrochetParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrochetParser#stitch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStitch(CrochetParser.StitchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrochetParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(CrochetParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrochetParser#repeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat(CrochetParser.RepeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrochetParser#contextOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextOperation(CrochetParser.ContextOperationContext ctx);
}