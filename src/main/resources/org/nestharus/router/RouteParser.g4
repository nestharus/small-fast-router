parser grammar RouteParser;

options {
    tokenVocab = RouteLexer;
}

main: url EOF;

url
    : SLASH
    | (SLASH segment)+ SLASH?
    | segment (SLASH segment)* SLASH?
    ;

segment
    : wildcard_segment
    | glob_segment
    | STATIC_TEXT
    ;

wildcard_segment
    : wildcard
    | STATIC_TEXT wildcard
    | wildcard STATIC_TEXT
    ;

wildcard
    : WILDCARD varname?
    ;

glob_segment
    : GLOB
    | GLOB varname
    | GLOB varname_group
    | GLOB varname varname_group
    ;

varname
    : LBRACE IDENTIFIER RBRACE
    ;

varname_group
    : LBRACKET var_list RBRACKET
    ;

var_list
    : wildcard_list
    | identifier_list (COMMA wildcard_list)?
    ;

identifier_list
    : IDENTIFIER (COMMA IDENTIFIER)*
    ;

wildcard_list
    : WILDCARD (COMMA WILDCARD)*;
