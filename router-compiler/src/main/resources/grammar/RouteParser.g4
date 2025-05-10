parser grammar RouteParser;

options { tokenVocab = RouteLexer; }

main
    : slash element* EOF
    | slash branch slash? EOF
    ;

element
    : starPattern
    | textExpression
    | groupExpression
    | starExpression
    | slash
    ;

branchElement
    : starPattern
    | textExpression
    | groupExpression
    | starExpression
    ;

branch
    : branchElement+ (PIPE branchElement+)+
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
    : LPAREN ((element+)|branch) RPAREN
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