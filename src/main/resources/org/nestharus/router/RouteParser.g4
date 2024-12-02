parser grammar RouteParser;

options {
    tokenVocab = RouteLexer;
}

main: url EOF;

url
    : SLASH
    | segment? segments (SLASH glob_segment)? segments SLASH?
    | glob_segment segments SLASH?
    ;

segments
    : (SLASH segment)*
    ;

segment
    : wildcard_segment
    | STATIC_TEXT
    ;

wildcard_segment
    : STATIC_TEXT? wildcard STATIC_TEXT?
    ;

wildcard
    : WILDCARD varname?
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
