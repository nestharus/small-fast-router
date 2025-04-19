lexer grammar RouteLexer;

tokens {
  IDENTIFIER, // Used inside <>
  INTEGER,    // Used inside []
  COMMA       // Used inside []
  // Implicit: EOF, WS, SLASH, GLOB, WILDCARD, OPTIONAL, STATIC_TEXT,
  // LPAREN, RPAREN, PIPE, BANG, LBRACK, RBRACK, LT, GT
}

// --- Default Mode Tokens ---
SLASH     : '/' ;
GLOB      : '**' ; // Must be defined before WILDCARD
WILDCARD  : '*' ;
OPTIONAL  : '?' ;
LPAREN    : '(' ;
RPAREN    : ')' ;
PIPE      : '|' ;
BANG      : '!' ;
LBRACK    : '[' ;
RBRACK    : ']' ;
COMMA     : ',' ; // For quantifiers

// Static text: Allows letters, numbers, and URL-safe symbols NOT used as operators.
// Excludes: / * ? ( ) | ! [ ] , < >
// Includes common safe chars like: _ ~ $ . & ' ; = : % -
STATIC_TEXT : [a-zA-Z0-9_~$.&';=:%-]+ ;

LT        : '<' -> pushMode(VARNAME_MODE) ; // Enter variable name mode
INTEGER   : [0-9]+ ; // For quantifiers

WS        : [ \t\r\n]+ -> skip ; // Skip whitespace

// --- VarName Mode (inside <>) ---
mode VARNAME_MODE;
  VARNAME_IDENTIFIER : [a-zA-Z_][a-zA-Z0-9_]* -> type(IDENTIFIER) ;
  VARNAME_WS         : [ \t\r\n]+ -> skip ;
  GT                 : '>' -> popMode ;       // Exit variable name mode