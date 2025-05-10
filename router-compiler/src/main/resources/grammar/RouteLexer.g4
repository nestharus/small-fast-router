lexer grammar RouteLexer;

tokens {
  IDENTIFIER,
  INTEGER,
  COMMA,
  RBRACK,
  GT,
  INVALID_VAR_CHAR  // emitted when <…> contains an illegal character
}

// ───────────────────────── Default mode ─────────────────────────
SLASH       : '/' ;
DOUBLESTAR  : '**' ;                  // must precede STAR
STAR        : '*' ;
QMARK       : '?' ;
BANG        : '!' ;
LPAREN      : '(' ;
RPAREN      : ')' ;
LBRACK      : '['  -> pushMode(QUANT) ;   // enter [quantifier] mode
PIPE        : '|' ;
LT          : '<'  -> pushMode(VARNAME) ;
STATIC_TEXT : [a-zA-Z0-9\-._~$&'+,;=:@]+ ;   // literal segments

// ───────────────────── Var‑name mode  < … > ─────────────────────
mode VARNAME;
  VARNAME_IDENTIFIER : [A-Za-z_][A-Za-z0-9_]*  -> type(IDENTIFIER) ;
  VARNAME_WS         : [ \t\r\n]+              -> skip ;
  VARNAME_GT         : '>'                     -> popMode, type(GT) ;
  VARNAME_INVALID_VAR_CHAR   : .               -> type(INVALID_VAR_CHAR) ;

// ───────────────────── Quantifier mode  [ … ] ───────────────────
mode QUANT;
  Q_INT     : [0-9]+ -> type(INTEGER) ;
  Q_COMMA   : ','    -> type(COMMA) ;
  Q_RBRACK  : ']'    -> popMode, type(RBRACK) ;
  Q_WS      : [ \t\r\n]+ -> skip ;
  QUANT_INVALID_VAR_CHAR   : . -> type(INVALID_VAR_CHAR) ;
