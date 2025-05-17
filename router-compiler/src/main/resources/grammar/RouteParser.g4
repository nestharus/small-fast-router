parser grammar RouteParser;

options { tokenVocab = RouteLexer; }

main
    : (slash element)+ slash? EOF
    | slash branchExpression slash? EOF
    | slash
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
    : BANG? STATIC_TEXT capture? QMARK?
    ;

groupExpression
    : BANG? group capture? QMARK?
    ;

starPattern
    : BANG? group star capture? quantifier? QMARK?
    ;

starExpression
    : star capture? quantifier? QMARK?
    ;

group
    : LPAREN ((element+)|branchExpression) RPAREN
    ;

capture
    : LT IDENTIFIER GT
    ;

quantifier
    : LBRACK quantifierElement RBRACK
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