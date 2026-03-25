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

  // English to Polish abbreviation mapping
  const crochetAbbreviations: { [key: string]: string } = {
    'ps': 'półsłupek',
    'psn': 'półsłupek nawijany', 
    's': 'słupek',    
    'ł': 'łańcuszek',     
    'oś': 'oczko ścisłe',     
    'zr': 'zakończ robótkę',   
    'mk': 'magiczne kółko',   
    'zw': 'zwiększenie ilości oczek',
    'zm': 'zmniejszenie ilości oczek',       
    'po': 'tylko przednie oczka',       
    'to': 'tylko tylnie oczka',     
    'okr': 'okrąg',        
    'ocz': 'oczko',          
    'nast': 'następne',
  };

  const translatePattern = async () => {
    const response = await fetch('http://localhost:8080/api/translate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        pattern_en: { text: inputText }
      })
    });

    const data = await response.json();
    setPatternData(data);
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
                  placeholder="Przykład:&#10;rnd 1: mr 8 sc (8);&#10;rnd 2: 8 inc (16);&#10;rnd 3: (1 sc, 1 inc) x 8 (24);"
                  className="w-full h-64 p-4 border-2 border-rose-200 rounded-lg resize-none focus:outline-none focus:ring-2 focus:ring-rose-400 focus:border-transparent bg-white"
                />
              </div>

              {/* Right side - Polish translation */}
              <div className="space-y-4">
                <label className="block text-rose-900 font-semibold">Wzór po polsku</label>
                {patternData ? (
                  <div className="p-4 border-2 border-rose-200 rounded-lg bg-rose-50 whitespace-pre-wrap h-64 overflow-y-auto">
                    {patternData.pattern_pl.text}
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
          </div>
        </div>

        <div className="mt-8 bg-white/80 backdrop-blur-sm rounded-xl p-6 border border-rose-200">
          <h2 className="text-rose-900 mb-3">Tabela tłumaczeń</h2>
          <div className="grid grid-cols-2 md:grid-cols-3 gap-3 text-sm text-rose-800">
            {Object.entries(crochetAbbreviations).slice(0, 12).map(([enAbbr, plAbbr]) => (
              <div key={enAbbr} className="flex gap-2 items-center">
                <span className="font-semibold text-rose-900">{enAbbr}</span>
                <span className="text-rose-400">→</span>
                <span className="font-semibold text-rose-700">{plAbbr}</span>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}