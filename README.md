# 🧶 CrochetLingo

CrochetLingo is a Java-based tool for translating crochet patterns from English to Polish. The project uses ANTLR to define and parse a simple domain-specific language (DSL) for crochet instructions, enabling structured and accurate translation of stitches, patterns, and control constructs.

---

## ✨ Features

* Translate English crochet patterns into Polish
* Understand common crochet abbreviations (e.g. sc, dc, ch)
* Parse structured patterns using ANTLR
* Extensible language definition for future features

---

## 🧠 Language Definition (DSL)

The CrochetLingo DSL is designed to represent crochet instructions in a structured way.

### 🧵 Basic Operations (Stitches)

| Full English Name   | EN Abbreviation | PL Abbreviation | Full Polish Name          |
| ------------------- | --------------- | --------------- | ------------------------- |
| single crochet      | sc              | ps              | półsłupek                 |
| half double crochet | hdc             | psn             | półsłupek nawijany        |
| double crochet      | dc              | s               | słupek                    |
| chain               | ch              | ł               | łańcuszek                 |
| slip stitch         | sl st           | oś              | oczko ścisłe              |
| fasten off          | fo              | zr              | zakończ robótkę           |
| magic ring          | mr              | mk              | magiczne kółko            |
| increase            | inc             | zw              | zwiększenie ilości oczek  |
| decrease            | dec             | zm              | zmniejszenie ilości oczek |
| front loop only     | flo             | po              | tylko przednie oczka      |
| back loop only      | blo             | to              | tylko tylne oczka         |

---

### 🔁 Control Structures

| Full English Name        | EN Abbreviation    | PL Abbreviation         | Full Polish Name          |
| ------------------------ | ------------------ | ----------------------- | ------------------------- |
| repeat N times: ...      | (... ) x N         | (... ) x N              | powtórz N razy            |
| round X                  | rnd X              | okr X                   | okrąg X                   |

---

### 🔗 Context Operations

| Full English Name | EN Short   | PL Short        | Full Polish Name |
| ----------------- | ---------- | --------------- | ---------------- |
| in next stitch    | in next st | do nast. ocz    | w następne oczko |
| in same stitch    | in same st | w to samo ocz   | w to samo oczko  |
| skip N stitches   | skip N st  | omiń N oczek    | omiń N oczek     |
| turn              | turn       | obróć           | obróć robótkę    |

---

---

## 🔄 Example Translation

**Input (EN):**

```
rnd 1: mr 8 sc (8);
rnd 2: 8 inc (16);
rnd 3: (1 sc, 1 inc) x 8 (24);
```

**Output (PL):**

```
okr 1: mk 8 ps (8);
okr 2: 8 zw (16);
okr 3: (1 ps, 1 zw) x 8 (24);
```

---

## 🚀 Future Development

* Support for additional languages (e.g. German, Spanish)
* Conversion between right-handed and left-handed crochet patterns
* Pattern validation and error detection
* GUI or web interface for easier use

---

## ⚙️ Tech Stack

* Java
* ANTLR (for parsing and language definition)

---

## 📌 Goal

The goal of CrochetLingo is to simplify working with international crochet patterns and make them accessible to Polish-speaking users.
