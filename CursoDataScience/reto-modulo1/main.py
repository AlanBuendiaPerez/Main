# CLASE ANALIZADORA DE TEXTO
class TextAnalyzer():
    def __init__(self, raw_text='', stop_words='', output_file='processed-text.txt'):
        # Leer el archivo raw-text.txt
        with open(raw_text, 'r', encoding='utf-8') as file:
            raw_text = file.read()

        # 1. Convertir todo el texto a minúsculas.
        formattedText = raw_text.lower()

        # 2. Eliminar saltos de línea y caracteres que no sean letras.
        formattedText = ''.join([char for char in formattedText if char.isalpha() or char.isspace()])

        # Eliminar saltos de línea reemplazándolos por espacios
        formattedText = formattedText.replace('\n', ' ')

        # 3. Eliminar cualquier dígito presente en el texto.
        formattedText = ''.join([char for char in formattedText if not char.isdigit()])

        # Guardar el texto formateado como un atributo de la clase
        self.fmtText = formattedText

        # 4. Tokenizar el texto (convertir el texto en una lista de palabras)
        self.tokenizedText = self.fmtText.split()

        # Leer el archivo stop-words.txt y convertirlo en un set de stopwords
        with open(stop_words, 'r', encoding='utf-8') as file:
            stopwords = set(file.read().splitlines())

        # 5. Remover las stopwords más comunes en español (último paso)
        self.filteredText = [word for word in self.tokenizedText if word not in stopwords]

        # Guardar el resultado final en un archivo processed-text.txt
        with open(output_file, 'w', encoding='utf-8') as file:
            file.write(' '.join(self.filteredText))

analyzed = TextAnalyzer('raw-text.txt', 'stop-words.txt', 'processed-text.txt')

print()
print("\nSalida:")
print()
print(analyzed.filteredText)
print()
