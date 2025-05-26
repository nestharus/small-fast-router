parser grammar RouteParser;

options { tokenVocab = RouteLexer; }

main
    : (slash element+)+ slash? EOF
    | slash branchExpression slash? EOF
    | slash EOF
    ;

element
    : starPattern
    | textExpression
    | groupExpression
    | starExpression
    ;

branch
    : element+
    ;

branchExpression
    : branch (PIPE branch)+
    ;

textExpression
    : prefix* STATIC_TEXT postfix*
    ;

groupExpression
    : prefix* group postfix*
    ;

starPattern
    : prefix* group star postfix*
    ;

starExpression
    : star postfix*
    ;

prefix
    : BANG
    ;

postfix
    : capture
    | quantifier
    | QMARK
    ;

group
    : LPAREN ((element+)|branchExpression) RPAREN
    ;

capture
    : LT IDENTIFIER GT
    ;

quantifier
    : LBRACK quantifierElement RBRACK
    | LBRACK quantifierBranchExpression RBRACK
    ;

quantifierBranchExpression
    : quantifierElement (PIPE quantifierElement)+
    ;

quantifierElement
    : quantifierSingleElement
    | quantifierBoundedElement
    | quantifierBoundedUpperElement
    | quantifierBoundedLowerElement
    ;

quantifierBoundedUpperElement
    : COMMA INTEGER
    ;

quantifierBoundedLowerElement
    : INTEGER COMMA
    ;

quantifierBoundedElement
    : quantifierLeftElement COMMA quantifierRightElement
    ;

quantifierLeftElement
    : INTEGER
    ;

quantifierRightElement
    : INTEGER
    ;

quantifierSingleElement
    : INTEGER
    ;

star
    : STAR
    | DOUBLESTAR
    ;

slash
    : SLASH
    ;