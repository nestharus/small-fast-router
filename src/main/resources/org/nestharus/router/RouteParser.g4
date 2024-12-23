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
    | static_segment
    ;

static_segment
    : STATIC_TEXT OPTIONAL?
    ;

wildcard_segment
    : static_segment? wildcard static_segment?
    ;

glob_segment
    : STATIC_TEXT? GLOB varname? varname_group?
    ;

varname
    : LBRACE IDENTIFIER RBRACE
    ;

wildcard
    : WILDCARD varname? OPTIONAL?
    ;

unnamed_wildcard
    : WILDCARD OPTIONAL?
    ;

named_wildcard
    : WILDCARD varname OPTIONAL?
    ;

varname_group
    : LBRACKET var_list RBRACKET
    ;

var_list
    : unnamed_wildcard (COMMA unnamed_wildcard)*
    | named_wildcard (COMMA named_wildcard)* (COMMA unnamed_wildcard)*
    ;
