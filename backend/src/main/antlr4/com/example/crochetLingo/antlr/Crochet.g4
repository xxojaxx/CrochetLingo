grammar Crochet;

pattern
    : round+ EOF
    ;

round
    : 'rnd' NUMBER ':' elementList ';'
    ;

elementList
    : element (',' element)*
    ;

element
    : stitch
    | action
    | repeat
    | contextOperation
    ;

stitch
    : STITCH NUMBER?
    ;

action
    : 'mr'
    | 'fo'
    ;

repeat
    : '(' elementList ')' 'x' NUMBER
    ;

contextOperation
    : 'in_next_st'
    | 'in_same_st'
    | 'skip' NUMBER?
    | 'turn'
    ;

STITCH
    : 'sc'
    | 'hdc'
    | 'dc'
    | 'ch'
    | 'slst'
    | 'inc'
    | 'dec'
    | 'flo'
    | 'blo'
    ;

NUMBER
    : [0-9]+
    ;

WS
    : [ \t\r\n]+ -> skip
    ;
