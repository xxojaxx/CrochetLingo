# CrochetLingo

CrochetLingo tlumaczy wzory szydelkowe z angielskiego DSL na polski DSL. Backend waliduje wejscie parserem ANTLR i wykonuje translacje przez AST/visitor, bez prostego `replace`.

## DSL v1

`DSL v1` jest zdefiniowany przez gramatyke w `backend/src/main/antlr4/com/example/crochetLingo/antlr/Crochet.g4` i to ona jest zrodlem prawdy.

### Gramatyka

```ebnf
pattern := round+
round := "rnd" NUMBER ":" elementList ";"
elementList := element ("," element)*
element := stitch | action | repeat | contextOperation
stitch := STITCH NUMBER?
action := "mr" | "fo"
repeat := "(" elementList ")" "x" NUMBER
contextOperation := "in_next_st" | "in_same_st" | "skip" NUMBER? | "turn"
```

### Obslugiwane tokeny EN -> PL

| EN | PL |
| --- | --- |
| `rnd` | `okr` |
| `sc` | `ps` |
| `hdc` | `psn` |
| `dc` | `s` |
| `ch` | `ł` |
| `slst` | `oś` |
| `inc` | `zw` |
| `dec` | `zm` |
| `flo` | `po` |
| `blo` | `to` |
| `mr` | `mk` |
| `fo` | `zr` |
| `in_next_st` | `w nast. ocz` |
| `in_same_st` | `w to samo ocz` |
| `skip N` | `omiń N oczek` |
| `turn` | `obróć` |

## Przykladowe wejscie i wyjscie

### Wejscie EN

```text
rnd 1: mr, sc 8;
rnd 2: inc 8;
rnd 3: (sc 1, inc) x 8;
```

### Wyjscie PL

```text
okr 1: mk, ps 8;
okr 2: zw 8;
okr 3: (ps 1, zw) x 8;
```

### Przyklad z operacjami kontekstowymi

```text
rnd 4: flo 6, in_next_st, skip 2, turn;
rnd 5: (dc 1, in_same_st, dec) x 3, fo;
```

```text
okr 4: po 6, w nast. ocz, omiń 2 oczek, obróć;
okr 5: (s 1, w to samo ocz, zm) x 3, zr;
```

## Walidacja

Parser ANTLR waliduje skladnie przed translacja. Niepoprawny request albo niepoprawny `DSL v1` zwracaja `400` z backendu.

## Czego DSL v1 nie obsluguje

Ponizsze formy sa celowo odrzucane:

- `8 inc`
- `mr 8 sc`
- koncowe liczniki typu `(24)`

## Testy i fixture'y

Test translacji znajduje sie w `backend/src/test/java/com/example/crochetLingo/service/TranslatorServiceTest.java`.

Fixture'y expected vs actual znajduja sie w:

- `backend/src/test/resources/fixtures/basic.en.dsl`
- `backend/src/test/resources/fixtures/basic.pl.dsl`
- `backend/src/test/resources/fixtures/context.en.dsl`
- `backend/src/test/resources/fixtures/context.pl.dsl`

## Uruchamianie

### Backend

W katalogu `backend`:

```powershell
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-21.0.8.9-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
./mvnw spring-boot:run
```

### Testy

W katalogu `backend`:

```powershell
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-21.0.8.9-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
./mvnw test
```

### Frontend

W katalogu `website`:

```powershell
npm run dev
```
