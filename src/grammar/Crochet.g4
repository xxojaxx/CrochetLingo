grammar Crochet;

// regula glowna
pattern : round+;


// runda z numerem + wszystkie instrukcje w danej rundzie
round
    : 'rnd' NUMBER ':' elementList ';'
    ;

// lista elementow w konkretnej rundzie
elementList
    : element (',' element)*
    ;

// pojedyncza instrukcja: scieg, akcja, powtorzenie
element
    : stitch
    | action
    | repeat
    | contextOperation
    ;

// definicja sciegu z opcjonalnie ustawiona liczba
stitch
    : STITCH NUMBER?
    ;

// definicja akcji
action
    : FO // fasten off
    | MR // magic ring
    ;

// powtorzenie bloku o konkretna ilosc razy
repeat
    : '(' elementList ')' 'x' NUMBER
    ;

contextOperation
    : IN_NEXT_STITCH
    | IN_SAME_STITCH
    | SKIP_STICHES NUMBER?
    | TURN
    ;

STITCH
    : 'sc' // single crochet
    | 'hdc' // half double crochet
    | 'dc' // double crochet
    | 'ch' // chain
    | 'slst' // slip stitch
    | 'inc' // increase
    | 'dec' // decrease
    | 'flo' // front loop only
    | 'blo' // back loop only
    ;

FO : 'fo';
MR : 'mr';

IN_NEXT_STITCH : 'in_next_st'; // in next stitch
IN_SAME_STITCH : 'in_same_st'; // in same stitch
SKIP_STICHES : 'skip'; // skip N stitches
TURN : 'turn'; // turn

NUMBER : [0-9]+;
WS : [ \t\r\n]+ -> skip;