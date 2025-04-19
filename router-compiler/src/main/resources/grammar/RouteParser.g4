parser grammar RouteParser;

options { tokenVocab = RouteLexer; }

// Entry rule: matches the entire route string, handles empty string implicitly via '?'
main: url EOF;

// A URL definition allowing root '/', paths starting with '/', paths starting without '/', and trailing '/'
// Also allow routes starting with negation '!'
// Examples: "", "/", "/a", "a", "a/b", "/a/b/", "!a", "!(a)"
url: (SLASH | BANG)? (segment (SLASH segment)*)? SLASH?; // An optional initial slash or negation, optionally followed by segments, optionally followed by a trailing slash.

// path_expression rule is no longer needed as the entry point, keeping segment structure
// path_expression: SLASH? segment (SLASH segment)* SLASH?; // Remove or comment out if no longer referenced

// A segment consists of one or more alternative sequences (separated by '|')
segment: alternative;

// An alternative is one or more sequences separated by '|'
alternative: sequence (PIPE sequence)*;

// A sequence is one or more elements concatenated together
sequence: element+;

// An element is the core building block: optionally negated, optionally prefixed with '?',
// followed by a primary item, and then zero or more suffix modifiers (including '?')
element: BANG? OPTIONAL? primary (modifier | OPTIONAL)*;

// Primary items are the base units that can be modified
primary
    : STATIC_TEXT
    | group
    | WILDCARD // Standalone wildcard *
    | GLOB     // Standalone glob **
    | name     // <<< Added name here to allow <var> as a primary element
    ;

// Modifiers that can follow a primary item
modifier
    : repetition
    | name
    | quantifier
    // OPTIONAL ('?') is handled directly in the 'element' rule structure
    ;

// A group is a parenthesized alternative
// Allow slashes within groups for patterns like /(a/b)*
group: LPAREN (alternative | url | segment (SLASH segment)*) RPAREN;

// Repetition operators that can act as modifiers
repetition: WILDCARD | GLOB; // '*' or '**' following a primary (like group or static text)

// Variable name modifier like <id>
name: LT IDENTIFIER GT;

// Quantifier modifier like [1,3]
// Allow all formats: [1,3], [1,], [,3], [,], etc.
quantifier: LBRACK (INTEGER? COMMA INTEGER? | INTEGER) RBRACK;