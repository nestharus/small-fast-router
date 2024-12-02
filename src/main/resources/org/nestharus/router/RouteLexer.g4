lexer grammar RouteLexer;

tokens {
    IDENTIFIER,
    COMMA
}

SLASH: '/';
GLOB: '**';
WILDCARD: '*';
OPTIONAL: '?';
STATIC_TEXT: [a-zA-Z0-9._~!$&'()+,;=:%-]+;

LBRACE: '{' -> pushMode(VARNAME_MODE);
LBRACKET: '[' -> pushMode(LIST_MODE);

mode VARNAME_MODE;
    VARNAME_IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]* -> type(IDENTIFIER);
    VARNAME_WS: [ \t\r\n]+ -> skip;
    RBRACE: '}' -> popMode;

mode LIST_MODE;
    LIST_IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]* -> type(IDENTIFIER);
    LIST_COMMA: ',' -> type(COMMA);
    LIST_WILDCARD: '*' -> type(WILDCARD);
    LIST_WS: [ \t\r\n]+ -> skip;
    LIST_LBRACE: '{' -> pushMode(VARNAME_MODE), type(LBRACE);
    LIST_OPTIONAL: '?' -> type(OPTIONAL);
    RBRACKET: ']' -> popMode;