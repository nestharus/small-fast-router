parser grammar RouteParser;

options {
    tokenVocab = RouteLexer;
}

main
    : url EOF
    ;

url
    : SLASH
    | segment? (SLASH segment)* (SLASH glob_segment)? (SLASH segment)* SLASH?
    | glob_segment (SLASH segment)* SLASH?
    ;

segment
    : wildcard_segment
    | STATIC_TEXT
    ;

wildcard_segment
    : STATIC_TEXT? WILDCARD varname? STATIC_TEXT?
    ;

glob_segment
    : GLOB varname? varname_group?
    ;

varname
    : LBRACE IDENTIFIER RBRACE
    ;

varname_group
    : LBRACKET var_list RBRACKET
    ;

var_list
    : WILDCARD (COMMA WILDCARD)*
    | WILDCARD varname (COMMA WILDCARD varname)* (COMMA WILDCARD)*
    ;
