lexer grammar RouteLexer;

tokens {
    WILDCARD,
    IDENTIFIER,
    COMMA
}

// General Tokens
SLASH: '/';
GLOB: '**';
STATIC_TEXT: [a-zA-Z0-9._~!$&'()+,;=:%-]+;

// WILDCARD in DEFAULT_MODE
DEFAULT_WILDCARD: '*' -> type(WILDCARD);

// Transition Tokens
LBRACE: '{' -> pushMode(VARNAME_MODE);
LBRACKET: '[' -> pushMode(LIST_MODE);

// VARNAME_MODE for {varname}
mode VARNAME_MODE;
    VARNAME_IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]* -> type(IDENTIFIER);
    VARNAME_WS: [ \t\r\n]+ -> skip;         // Skip whitespace in VARNAME_MODE
    RBRACE: '}' -> popMode;                 // Exit VARNAME_MODE on `}`

// LIST_MODE for [var1, var2, *]
mode LIST_MODE;
    LIST_IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]* -> type(IDENTIFIER); // Map to IDENTIFIER
    LIST_COMMA: ',' -> type(COMMA);         // Map to COMMA
    LIST_WILDCARD: '*' -> type(WILDCARD);   // Map to WILDCARD
    LIST_WS: [ \t\r\n]+ -> skip;            // Skip whitespace in LIST_MODE
    RBRACKET: ']' -> popMode;               // Exit LIST_MODE on `]`