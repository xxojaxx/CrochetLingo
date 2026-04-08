import { useState } from 'react';
import { createPortal } from 'react-dom';
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
  const [mirrorHand, setMirrorHand] = useState(false);

  const [tooltip, setTooltip] = useState<{
    active: boolean;
    content: { full: string; img: string | null } | null;
    position: { x: number; y: number };
  }>({ active: false, content: null, position: { x: 0, y: 0 } });

  const handleMouseEnter = (e: React.MouseEvent, entry: { full: string; img: string | null }) => {
    const rect = (e.target as HTMLElement).getBoundingClientRect();
    setTooltip({
      active: true,
      content: entry,
      position: {
        x: rect.left + rect.width / 2, // Środek podkreślonego słowa
        y: rect.top // Góra słowa
      }
    });
  };

  const handleMouseLeave = () => setTooltip(prev => ({ ...prev, active: false }));
  const abbreviations = [
    { en: 'rnd',        pl: 'okr',           full: 'okrąg',                     img: null },
    { en: 'sc',         pl: 'ps',            full: 'półsłupek',                 img: '/images/stitches/sc.jpg' },
    { en: 'hdc',        pl: 'psn',           full: 'półsłupek nawijany',        img: '/images/stitches/hdc.jpg' },
    { en: 'dc',         pl: 's',             full: 'słupek',                    img: '/images/stitches/dc.jpg' },
    { en: 'ch',         pl: 'ł',             full: 'łańcuszek',                 img: '/images/stitches/ch.jpg' },
    { en: 'slst',       pl: 'oś',            full: 'oczko ścisłe',              img: '/images/stitches/slst.jpg' },
    { en: 'inc',        pl: 'zw',            full: 'zwiększenie ilości oczek',  img: '/images/stitches/inc.jpg' },
    { en: 'dec',        pl: 'zm',            full: 'zmniejszenie ilości oczek', img: '/images/stitches/dec.jpg' },
    { en: 'flo',        pl: 'po',            full: 'tylko przednie oczka',      img: '/images/stitches/flo.jpg' },
    { en: 'blo',        pl: 'to',            full: 'tylko tylne oczka',         img: '/images/stitches/blo.jpg' },
    { en: 'mr',         pl: 'mk',            full: 'magiczne kółko',            img: '/images/stitches/mr.jpg' },
    { en: 'fo',         pl: 'zr',            full: 'zakończ robótkę',           img: '/images/stitches/fo.jpg' },
    { en: 'in_next_st', pl: 'w nast. ocz',   full: 'w następne oczko',          img: null },
    { en: 'in_same_st', pl: 'w to samo ocz', full: 'w to samo oczko',           img: null },
    { en: 'skip N',     pl: 'omiń N oczek',  full: 'opuść N oczek',             img: null },
    { en: 'turn',       pl: 'obróć',         full: 'obróć pracę',               img: '/images/stitches/turn.jpg' },
  ];

  // słownik do tooltipów (klucz: skrót pl → { pełna nazwa, zdjęcie })
  const crochetAbbreviations: Record<string, { full: string; img: string | null }> = Object.fromEntries(
      abbreviations.map(({ pl, full, img }) => [pl, { full, img }])
  );

  function mapDslError(error: any): string {
    const { line, expected, offendingToken } = error;
    if (expected?.includes("';'")) return `Linia ${line}: Brakuje średnika ";" na końcu rundy.`;
    if (expected?.includes("','")) return `Linia ${line}: Brakuje przecinka między elementami (np. "mr, sc 8").`;
    if (/^\d+$/.test(offendingToken)) return `Linia ${line}: Nie mogą być dwie liczby do jednego ściegu. Użyj np. "sc 8", nie "8 sc 8".`;
    if (offendingToken && !expected?.length) return `Linia ${line}: Nieznany element "${offendingToken}".`;
    return `Nieznany błąd. Sprawdź, czy wzór wpisany zgodnie ze schematem.`;
  }

  const translatePattern = async () => {
    setErrorMessage(null);
    try {
      const response = await fetch('http://localhost:8080/api/translate', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          pattern_en: { text: inputText.toLowerCase() },
          mirror: mirrorHand,
        })
      });

      if (!response.ok) {
        try {
          const errorBody = await response.json();
          setErrorMessage(mapDslError(errorBody));
        } catch {
          setErrorMessage('Nie udało się przetłumaczyć wzoru. Sprawdź, czy wejście jest zgodne z DSL v1.');
        }
        setPatternData(null);
        return;
      }
      setPatternData(await response.json());
    } catch {
      setPatternData(null);
      setErrorMessage('Backend jest niedostępny. Upewnij się, że Spring Boot działa na porcie 8080.');
    }
  };

  const copyToClipboard = async () => {
    if (!patternData) return;
    const text = patternData.pattern_pl.text;
    try {
      if (navigator.clipboard?.writeText) {
        await navigator.clipboard.writeText(text);
      } else {
        const ta = document.createElement('textarea');
        ta.value = text;
        ta.style.cssText = 'position:fixed;opacity:0';
        document.body.appendChild(ta);
        ta.select();
        document.execCommand('copy');
        document.body.removeChild(ta);
      }
      setCopied(true);
      setTimeout(() => setCopied(false), 2000);
    } catch (err) {
      console.error('Failed to copy:', err);
    }
  };
  function TooltipPortal({
                           active,
                           content,
                           position
                         }: {
    active: boolean;
    content: { full: string; img: string | null } | null;
    position: { x: number; y: number }
  }) {
    if (!active || !content) return null;

    return createPortal(
        <div
            className="fixed z-[9999] pointer-events-none -translate-x-1/2 -translate-y-full mb-2"
            style={{ left: position.x, top: position.y }}
        >
          <div className="bg-white border border-rose-200 rounded-xl shadow-xl flex flex-col items-center gap-1 p-2 w-36 text-xs text-rose-900 text-center animate-in fade-in zoom-in duration-150">
            {content.img ? (
                <img src={content.img} alt={content.full} className="w-28 h-20 object-contain rounded-lg" />
            ) : (
                <div className="w-28 h-20 bg-rose-50 rounded-lg flex items-center justify-center text-rose-300 text-3xl">🧶</div>
            )}
            <span className="font-medium">{content.full}</span>
          </div>
        </div>,
        document.body
    );
  }

  function renderWithTooltips(text: string) {
    const multiWordKeys = Object.keys(crochetAbbreviations)
        .filter(k => k.includes(' '))
        .sort((a, b) => b.length - a.length);

    const parts: React.ReactNode[] = [];
    let remaining = text;

    const createSpan = (token: string, entry: any) => (
        <span
            key={parts.length}
            className="underline decoration-dotted cursor-help"
            onMouseEnter={(e) => handleMouseEnter(e, entry)}
            onMouseLeave={handleMouseLeave}
        >
        {token}
      </span>
    );

    while (remaining.length > 0) {
      const multiMatch = multiWordKeys.find(key => remaining.toLowerCase().startsWith(key.toLowerCase()));

      if (multiMatch) {
        parts.push(createSpan(remaining.slice(0, multiMatch.length), crochetAbbreviations[multiMatch]));
        remaining = remaining.slice(multiMatch.length);
        continue;
      }

      const tokenMatch = remaining.match(/^(\s+|\S+)/);
      if (!tokenMatch) break;

      const token = tokenMatch[0];
      const clean = token.toLowerCase().replace(/[(),;:.]/g, '').trim();
      const entry = crochetAbbreviations[clean];

      parts.push(entry ? createSpan(token, entry) : token);
      remaining = remaining.slice(token.length);
    }
    return parts;
  }

  return (
      <>
        <TooltipPortal
            active={tooltip.active}
            content={tooltip.content}
            position={tooltip.position}
        />
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
              {/* Toggle — zmiana ręki */}
              <label className="flex items-center gap-3 cursor-pointer w-fit select-none">
                <div className="relative">
                  <input type="checkbox" className="sr-only peer" checked={mirrorHand} onChange={(e) => setMirrorHand(e.target.checked)} />
                  <div className="w-11 h-6 bg-rose-200 rounded-full peer-checked:bg-rose-500 transition-colors" />
                  <div className="absolute top-0.5 left-0.5 w-5 h-5 bg-white rounded-full shadow transition-transform peer-checked:translate-x-5" />
                </div>
                <span className="text-rose-900 font-medium">
                {mirrorHand ? 'Odbicie lustrzane' : 'Bez odbicia lustrzanego'}
              </span>
              </label>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div className="space-y-4">
                  <label className="block text-rose-900 font-semibold">Wzór po angielsku</label>
                  <textarea
                      value={inputText}
                      onChange={(e) => setInputText(e.target.value)}
                      placeholder="Przykład:&#10;rnd 1: mr, sc 8;&#10;rnd 2: inc 8;&#10;rnd 3: (sc 1, inc) x 8;"
                      className="w-full h-64 p-4 border-2 border-rose-200 rounded-lg resize-none focus:outline-none focus:ring-2 focus:ring-rose-400 focus:border-transparent bg-white"
                  />
                </div>

                <div className="space-y-4">
                  <label className="block text-rose-900 font-semibold">Wzór po polsku</label>
                  {patternData ? (
                      <div className="p-4 border-2 border-rose-200 rounded-lg bg-rose-50 whitespace-pre-wrap h-64 overflow-y-auto">
                        {renderWithTooltips(patternData.pattern_pl.text)}
                      </div>
                  ) : (
                      <div className="p-4 border-2 border-rose-200 rounded-lg bg-gray-50 h-64 flex flex-col items-center justify-center gap-2">
                        <span className="text-gray-400">Tutaj pojawi się przetłumaczony wzór</span>
                      </div>
                  )}
                </div>
              </div>

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
                      <button onClick={copyToClipboard} className="flex items-center gap-2 px-6 py-3 bg-rose-100 hover:bg-rose-200 text-rose-800 rounded-lg transition-colors">
                        {copied ? <><Check className="w-4 h-4" /> Skopiowano!</> : <><Copy className="w-4 h-4" /> Kopiuj</>}
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

          {/* Słownik - zdjęcia BEZ tooltipów */}
          <div className="mt-8 bg-white/80 backdrop-blur-sm rounded-xl p-6 border border-rose-200 overflow-x-auto">
            <h2 className="text-rose-900 mb-3">Słownik skrótów:</h2>
            <table className="w-full text-sm">
              <thead>
              <tr className="text-left text-rose-500 border-b border-rose-100">
                <th className="pb-2 font-medium">Podgląd</th>
                <th className="pb-2 font-medium">Angielski</th>
                <th className="pb-2 font-medium">Polski skrót</th>
                <th className="pb-2 font-medium">Pełna nazwa</th>
              </tr>
              </thead>
              <tbody>
              {abbreviations.map(({ en, pl, full, img }) => (
                  <tr key={en} className="border-b border-rose-50 hover:bg-rose-50/50">
                    <td className="py-2 pr-4">
                      {img ? (
                          <img
                              src={img}
                              alt={full}
                              className="w-10 h-10 object-contain rounded-md border border-rose-100"
                              onError={(e) => {
                                const target = e.target as HTMLImageElement;
                                target.style.display = 'none';
                                const span = document.createElement('span');
                                span.className = 'inline-block w-10 h-10 bg-rose-50 rounded-md border border-rose-100 text-lg leading-10 text-center';
                                span.textContent = '🧶';
                                target.parentElement?.appendChild(span);
                              }}
                          />
                      ) : (
                          <span className="inline-block w-10 h-10 bg-rose-50 rounded-md border border-rose-100 text-lg leading-10 text-center">🧶</span>
                      )}
                    </td>
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
      </>
  );
}
