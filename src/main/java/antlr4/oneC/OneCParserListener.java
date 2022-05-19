package antlr4.oneC;// Generated from OneCParser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link OneCParser}.
 */
public interface OneCParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link OneCParser#startFile}.
	 * @param ctx the parse tree
	 */
	void enterStartFile(OneCParser.StartFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#startFile}.
	 * @param ctx the parse tree
	 */
	void exitStartFile(OneCParser.StartFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#fileBody}.
	 * @param ctx the parse tree
	 */
	void enterFileBody(OneCParser.FileBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#fileBody}.
	 * @param ctx the parse tree
	 */
	void exitFileBody(OneCParser.FileBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(OneCParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(OneCParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(OneCParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(OneCParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#functionHeader}.
	 * @param ctx the parse tree
	 */
	void enterFunctionHeader(OneCParser.FunctionHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#functionHeader}.
	 * @param ctx the parse tree
	 */
	void exitFunctionHeader(OneCParser.FunctionHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(OneCParser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(OneCParser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#procedureDecl}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDecl(OneCParser.ProcedureDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#procedureDecl}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDecl(OneCParser.ProcedureDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#procedureHeader}.
	 * @param ctx the parse tree
	 */
	void enterProcedureHeader(OneCParser.ProcedureHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#procedureHeader}.
	 * @param ctx the parse tree
	 */
	void exitProcedureHeader(OneCParser.ProcedureHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#procedureBody}.
	 * @param ctx the parse tree
	 */
	void enterProcedureBody(OneCParser.ProcedureBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#procedureBody}.
	 * @param ctx the parse tree
	 */
	void exitProcedureBody(OneCParser.ProcedureBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(OneCParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(OneCParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(OneCParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(OneCParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(OneCParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(OneCParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#variableListDecl}.
	 * @param ctx the parse tree
	 */
	void enterVariableListDecl(OneCParser.VariableListDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#variableListDecl}.
	 * @param ctx the parse tree
	 */
	void exitVariableListDecl(OneCParser.VariableListDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void enterVariableDecl(OneCParser.VariableDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void exitVariableDecl(OneCParser.VariableDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#variableList}.
	 * @param ctx the parse tree
	 */
	void enterVariableList(OneCParser.VariableListContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#variableList}.
	 * @param ctx the parse tree
	 */
	void exitVariableList(OneCParser.VariableListContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(OneCParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(OneCParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#basicForStatement}.
	 * @param ctx the parse tree
	 */
	void enterBasicForStatement(OneCParser.BasicForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#basicForStatement}.
	 * @param ctx the parse tree
	 */
	void exitBasicForStatement(OneCParser.BasicForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(OneCParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(OneCParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(OneCParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(OneCParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftHandSide(OneCParser.LeftHandSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftHandSide(OneCParser.LeftHandSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#fieldAccess}.
	 * @param ctx the parse tree
	 */
	void enterFieldAccess(OneCParser.FieldAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#fieldAccess}.
	 * @param ctx the parse tree
	 */
	void exitFieldAccess(OneCParser.FieldAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(OneCParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(OneCParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primaryNoNewArray_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNoNewArray_lfno_primary(OneCParser.PrimaryNoNewArray_lfno_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primaryNoNewArray_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNoNewArray_lfno_primary(OneCParser.PrimaryNoNewArray_lfno_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#arrayAccess_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess_lfno_primary(OneCParser.ArrayAccess_lfno_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#arrayAccess_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess_lfno_primary(OneCParser.ArrayAccess_lfno_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(OneCParser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(OneCParser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(OneCParser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(OneCParser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#classInstanceCreationExpression_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void enterClassInstanceCreationExpression_lfno_primary(OneCParser.ClassInstanceCreationExpression_lfno_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#classInstanceCreationExpression_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void exitClassInstanceCreationExpression_lfno_primary(OneCParser.ClassInstanceCreationExpression_lfno_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#methodInvocation_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void enterMethodInvocation_lfno_primary(OneCParser.MethodInvocation_lfno_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#methodInvocation_lfno_primary}.
	 * @param ctx the parse tree
	 */
	void exitMethodInvocation_lfno_primary(OneCParser.MethodInvocation_lfno_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#functionOrProcedureName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionOrProcedureName(OneCParser.FunctionOrProcedureNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#functionOrProcedureName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionOrProcedureName(OneCParser.FunctionOrProcedureNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(OneCParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(OneCParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(OneCParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(OneCParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#expressionName}.
	 * @param ctx the parse tree
	 */
	void enterExpressionName(OneCParser.ExpressionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#expressionName}.
	 * @param ctx the parse tree
	 */
	void exitExpressionName(OneCParser.ExpressionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#ambiguousName}.
	 * @param ctx the parse tree
	 */
	void enterAmbiguousName(OneCParser.AmbiguousNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#ambiguousName}.
	 * @param ctx the parse tree
	 */
	void exitAmbiguousName(OneCParser.AmbiguousNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(OneCParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(OneCParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#arrayCreationExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreationExpression(OneCParser.ArrayCreationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#arrayCreationExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreationExpression(OneCParser.ArrayCreationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primaryNoNewArray_lf_primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNoNewArray_lf_primary(OneCParser.PrimaryNoNewArray_lf_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primaryNoNewArray_lf_primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNoNewArray_lf_primary(OneCParser.PrimaryNoNewArray_lf_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#fieldAccess_lf_primary}.
	 * @param ctx the parse tree
	 */
	void enterFieldAccess_lf_primary(OneCParser.FieldAccess_lf_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#fieldAccess_lf_primary}.
	 * @param ctx the parse tree
	 */
	void exitFieldAccess_lf_primary(OneCParser.FieldAccess_lf_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(OneCParser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(OneCParser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#methodInvocation_lf_primary}.
	 * @param ctx the parse tree
	 */
	void enterMethodInvocation_lf_primary(OneCParser.MethodInvocation_lf_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#methodInvocation_lf_primary}.
	 * @param ctx the parse tree
	 */
	void exitMethodInvocation_lf_primary(OneCParser.MethodInvocation_lf_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#arrayAccess_lf_primary}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess_lf_primary(OneCParser.ArrayAccess_lf_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#arrayAccess_lf_primary}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess_lf_primary(OneCParser.ArrayAccess_lf_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(OneCParser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(OneCParser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(OneCParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(OneCParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalOrExpression(OneCParser.ConditionalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalOrExpression(OneCParser.ConditionalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalAndExpression(OneCParser.ConditionalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalAndExpression(OneCParser.ConditionalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(OneCParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(OneCParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(OneCParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(OneCParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(OneCParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(OneCParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(OneCParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(OneCParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(OneCParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(OneCParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#methodName}.
	 * @param ctx the parse tree
	 */
	void enterMethodName(OneCParser.MethodNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#methodName}.
	 * @param ctx the parse tree
	 */
	void exitMethodName(OneCParser.MethodNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(OneCParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(OneCParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(OneCParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(OneCParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#enhancedForStatement}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForStatement(OneCParser.EnhancedForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#enhancedForStatement}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForStatement(OneCParser.EnhancedForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(OneCParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(OneCParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#ifThenElseStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElseStatement(OneCParser.IfThenElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#ifThenElseStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElseStatement(OneCParser.IfThenElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(OneCParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(OneCParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#emptyStatement_}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement_(OneCParser.EmptyStatement_Context ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#emptyStatement_}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement_(OneCParser.EmptyStatement_Context ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#elseIfStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseIfStatement(OneCParser.ElseIfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#elseIfStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseIfStatement(OneCParser.ElseIfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(OneCParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(OneCParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(OneCParser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(OneCParser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#methodInvocation}.
	 * @param ctx the parse tree
	 */
	void enterMethodInvocation(OneCParser.MethodInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#methodInvocation}.
	 * @param ctx the parse tree
	 */
	void exitMethodInvocation(OneCParser.MethodInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(OneCParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(OneCParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(OneCParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(OneCParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(OneCParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(OneCParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void enterThrowStatement(OneCParser.ThrowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void exitThrowStatement(OneCParser.ThrowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#tryStatement}.
	 * @param ctx the parse tree
	 */
	void enterTryStatement(OneCParser.TryStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#tryStatement}.
	 * @param ctx the parse tree
	 */
	void exitTryStatement(OneCParser.TryStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#handlerStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerStatement(OneCParser.HandlerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#handlerStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerStatement(OneCParser.HandlerStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void enterGotoStatement(OneCParser.GotoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void exitGotoStatement(OneCParser.GotoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#tagStatement}.
	 * @param ctx the parse tree
	 */
	void enterTagStatement(OneCParser.TagStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#tagStatement}.
	 * @param ctx the parse tree
	 */
	void exitTagStatement(OneCParser.TagStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess(OneCParser.ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess(OneCParser.ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primaryNoNewArray_lfno_arrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNoNewArray_lfno_arrayAccess(OneCParser.PrimaryNoNewArray_lfno_arrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primaryNoNewArray_lfno_arrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNoNewArray_lfno_arrayAccess(OneCParser.PrimaryNoNewArray_lfno_arrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link OneCParser#primaryNoNewArray_lf_arrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNoNewArray_lf_arrayAccess(OneCParser.PrimaryNoNewArray_lf_arrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link OneCParser#primaryNoNewArray_lf_arrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNoNewArray_lf_arrayAccess(OneCParser.PrimaryNoNewArray_lf_arrayAccessContext ctx);
}