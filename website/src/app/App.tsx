import { useState } from 'react';
import { Copy, Check } from 'lucide-react';

interface PatternData {
  pattern_en: { text: string };
  pattern_pl: { text: string };
}

export default function App() {
  const [inputText, setInputText] = useState('');
  const [patternData, setPatternData] = useState<PatternData | null>(null);
  const [copied, setCopied] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const abbreviations = [
    { en: 'rnd',        pl: 'okr',           full: 'okrąg' },
    { en: 'sc',         pl: 'ps',            full: 'półsłupek' },
    { en: 'hdc',        pl: 'psn',           full: 'półsłupek nawijany' },
    { en: 'dc',         pl: 's',             full: 'słupek' },
    { en: 'ch',         pl: 'ł',             full: 'łańcuszek' },
    { en: 'slst',       pl: 'oś',            full: 'oczko ścisłe' },
    { en: 'inc',        pl: 'zw',            full: 'zwiększenie ilości oczek' },
    { en: 'dec',        pl: 'zm',            full: 'zmniejszenie ilości oczek' },
    { en: 'flo',        pl: 'po',            full: 'tylko przednie oczka' },
    { en: 'blo',        pl: 'to',            full: 'tylko tylne oczka' },
    { en: 'mr',         pl: 'mk',            full: 'magiczne kółko' },
    { en: 'fo',         pl: 'zr',            full: 'zakończ robótkę' },
    { en: 'in_next_st', pl: 'w nast. ocz',   full: 'w następne oczko' },
    { en: 'in_same_st', pl: 'w to samo ocz', full: 'w to samo oczko' },
    { en: 'skip N',     pl: 'omiń N oczek',  full: 'opuść N oczek' },
    { en: 'turn',       pl: 'obróć',         full: 'obróć pracę' },
  ];

// słownik do tooltipów (klucz: skrót pl → pełna nazwa)
  const crochetAbbreviations = Object.fromEntries(
      abbreviations.map(({ pl, full }) => [pl, full])
  );

  function mapDslError(error: any): string {
    const { line, column, offendingToken, expected } = error;

    // brak średnika
    if (expected?.includes("';'")) {
      return `Linia ${line}: Brakuje średnika ";" na końcu rundy.`;
    }

    //  brak przecinka
    if (expected?.includes("','")) {
      return `Linia ${line}: Brakuje przecinka między elementami (np. "mr, sc 8").`;
    }

    //  liczba w złym miejscu
    if (/^\d+$/.test(offendingToken)) {
      return ` Linia ${line}: Liczba w złym miejscu. Użyj np. "sc 8", nie "8 sc".`;
    }

    //  nieznany token
    if (offendingToken && !expected?.length) {
      return ` Linia ${line}: Nieznany element "${offendingToken}".`;
    }

    return `Nieznany błąd. Sprawdź, czy wzór wpisany zgodnie ze schematem.`;
  }

  const translatePattern = async () => {
    setErrorMessage(null);

    try {
      const response = await fetch('http://localhost:8080/api/translate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          pattern_en: { text: inputText.toLowerCase() }
        })
      });

      if (!response.ok) {
        const fallbackMessage = 'Nie udało się przetłumaczyć wzoru. Sprawdź, czy wejście jest zgodne z DSL v1.';
        try {
          const errorBody = await response.json();
          setErrorMessage(mapDslError(errorBody));
        } catch {
          setErrorMessage(fallbackMessage);
        }
        setPatternData(null);
        return;
      }

      const data = await response.json();
      setPatternData(data);
    } catch (error) {
      console.error('Failed to translate:', error);
      setPatternData(null);
      setErrorMessage('Backend jest niedostępny. Upewnij się, że Spring Boot działa na porcie 8080.');
    }
  };

  const copyToClipboard = async () => {
    try {
      if (patternData) {
        const textToCopy = patternData.pattern_pl.text;

        // Try modern Clipboard API first
        if (navigator.clipboard && navigator.clipboard.writeText) {
          try {
            await navigator.clipboard.writeText(textToCopy);
            setCopied(true);
            setTimeout(() => setCopied(false), 2000);
            return;
          } catch (clipboardErr) {
            console.log('Clipboard API failed, using fallback');
          }
        }

        // Fallback method using temporary textarea
        const textarea = document.createElement('textarea');
        textarea.value = textToCopy;
        textarea.style.position = 'fixed';
        textarea.style.opacity = '0';
        document.body.appendChild(textarea);
        textarea.select();

        try {
          document.execCommand('copy');
          setCopied(true);
          setTimeout(() => setCopied(false), 2000);
        } catch (execErr) {
          console.error('Failed to copy:', execErr);
        } finally {
          document.body.removeChild(textarea);
        }
      }
    } catch (err) {
      console.error('Failed to copy:', err);
    }
  };

  function renderWithTooltips(text: string) {
    const multiWordKeys = Object.keys(crochetAbbreviations)
        .filter(k => k.includes(' '))
        .sort((a, b) => b.length - a.length); // dłuższe najpierw

    const parts: React.ReactNode[] = [];
    let remaining = text;

    while (remaining.length > 0) {
      // Sprawdź czy zaczyna się wielowyrazowy skrót
      const multiMatch = multiWordKeys.find(key =>
          remaining.toLowerCase().startsWith(key.toLowerCase())
      );

      if (multiMatch) {
        const translation = crochetAbbreviations[multiMatch];
        parts.push(
            <span key={parts.length} className="tooltip-wrapper underline decoration-dotted cursor-help">
          {remaining.slice(0, multiMatch.length)}
              <span className="tooltip-box">{translation}</span>
        </span>
        );
        remaining = remaining.slice(multiMatch.length);
        continue;
      }

      // Znajdź najbliższy token (słowo lub białe znaki)
      const tokenMatch = remaining.match(/^(\s+|\S+)/);
      if (!tokenMatch) break;

      const token = tokenMatch[0];
      const clean = token.toLowerCase().replace(/[(),;:.]/g, '').trim();
      const translation = crochetAbbreviations[clean];

      if (translation) {
        parts.push(
            <span key={parts.length} className="tooltip-wrapper underline decoration-dotted cursor-help">
          {token}
              <span className="tooltip-box">{translation}</span>
        </span>
        );
      } else {
        parts.push(token);
      }

      remaining = remaining.slice(token.length);
    }

    return parts;
  }

  return (
    <div
      className="min-h-screen bg-rose-50 relative overflow-auto"
      style={{
        backgroundImage: `linear-gradient(rgba(255, 241, 242, 0.85), rgba(255, 228, 230, 0.85)), url('https://images.unsplash.com/photo-1714922938267-befc65aad0cf?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1920')`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundAttachment: 'fixed'
      }}
    >
      <div className="container mx-auto px-4 py-12 max-w-4xl">
        <div className="text-center mb-8">
          <h1 className="text-5xl mb-3 text-rose-900">CrochetLingo</h1>
          <p className="text-rose-700">Tłumacz wzory szydełkowe z angielskiego na polski</p>
        </div>

        <div className="bg-white/90 backdrop-blur-sm rounded-2xl shadow-2xl p-8 border-2 border-rose-200">
          <div className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              {/* Left side - Input */}
              <div className="space-y-4">
                <label className="block text-rose-900 font-semibold">Wzór po angielsku</label>
                <textarea
                  value={inputText}
                  onChange={(e) => setInputText(e.target.value)}
                  placeholder="Przykład DSL v1:&#10;rnd 1: mr, sc 8;&#10;rnd 2: inc 8;&#10;rnd 3: (sc 1, inc) x 8;"
                  className="w-full h-64 p-4 border-2 border-rose-200 rounded-lg resize-none focus:outline-none focus:ring-2 focus:ring-rose-400 focus:border-transparent bg-white"
                />
              </div>

              {/* Right side - Polish translation */}
              <div className="space-y-4">
                <label className="block text-rose-900 font-semibold">Wzór po polsku</label>
                {patternData ? (
                  <div className="p-4 border-2 border-rose-200 rounded-lg bg-rose-50 whitespace-pre-wrap h-64 overflow-y-auto">
                    {renderWithTooltips(patternData.pattern_pl.text)}
                  </div>
                ) : (
                  <div className="p-4 border-2 border-rose-200 rounded-lg bg-gray-50 h-64 flex items-center justify-center text-gray-400">
                    Tutaj pojawi się przetłumaczony wzór
                  </div>
                )}
              </div>
            </div>

            {/* Buttons row */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <button
                onClick={translatePattern}
                disabled={!inputText.trim()}
                className="w-full bg-rose-500 hover:bg-rose-600 text-white py-3 px-6 rounded-lg transition-colors disabled:bg-rose-300 disabled:cursor-not-allowed"
              >
                Przetłumacz na polski
              </button>

              <div className="flex justify-center items-center">
                {patternData && (
                  <button
                    onClick={copyToClipboard}
                    className="flex items-center gap-2 px-6 py-3 bg-rose-100 hover:bg-rose-200 text-rose-800 rounded-lg transition-colors"
                  >
                    {copied ? (
                      <>
                        <Check className="w-4 h-4" />
                        Skopiowano!
                      </>
                    ) : (
                      <>
                        <Copy className="w-4 h-4" />
                        Kopiuj
                      </>
                    )}
                  </button>
                )}
              </div>
            </div>

            {errorMessage && (
              <div className="rounded-lg border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-700">
                {errorMessage}
              </div>
            )}
          </div>
        </div>
        <div className="mt-8 bg-white/80 backdrop-blur-sm rounded-xl p-6 border border-rose-200 overflow-x-auto">
          <h2 className="text-rose-900 mb-3">Słownik skrótów:</h2>
          <table className="w-full text-sm">
            <thead>
            <tr className="text-left text-rose-500 border-b border-rose-100">
              <th className="pb-2 font-medium">Angielski</th>
              <th className="pb-2 font-medium">Polski skrót</th>
              <th className="pb-2 font-medium">Pełna nazwa</th>
            </tr>
            </thead>
            <tbody>
            {abbreviations.map(({ en, pl, full }) => (
                <tr key={en} className="border-b border-rose-50 hover:bg-rose-50/50">
                  <td className="py-2 pr-4">
                    <span className="font-mono bg-blue-50 text-blue-800 px-2 py-0.5 rounded text-xs">{en}</span>
                  </td>
                  <td className="py-2 pr-4">
                    <span className="font-mono bg-rose-50 text-rose-800 px-2 py-0.5 rounded text-xs">{pl}</span>
                  </td>
                  <td className="py-2 text-rose-700">{full}</td>
                </tr>
            ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
