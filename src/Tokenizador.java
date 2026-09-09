import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por transformar uma String com a expressão infixa
 * (ex.: "(2.5 + 3) * 4") em uma lista de {@link Token}.
 */
public class Tokenizador {

    public static List<Token> tokenizar(String expressao) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        int n = expressao.length();

        while (i < n) {
            char c = expressao.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                int inicio = i;
                boolean pontoEncontrado = false;
                while (i < n && (Character.isDigit(expressao.charAt(i))
                        || (expressao.charAt(i) == '.' && !pontoEncontrado))) {
                    if (expressao.charAt(i) == '.') {
                        pontoEncontrado = true;
                    }
                    i++;
                }
                String numeroStr = expressao.substring(inicio, i);
                tokens.add(new Token(Double.parseDouble(numeroStr)));
                continue;
            }

            switch (c) {
                case '+':
                    tokens.add(new Token(Token.Tipo.SOMA));
                    break;
                case '-':
                    tokens.add(new Token(Token.Tipo.SUBTRACAO));
                    break;
                case '*':
                    tokens.add(new Token(Token.Tipo.MULTIPLICACAO));
                    break;
                case '/':
                    tokens.add(new Token(Token.Tipo.DIVISAO));
                    break;
                case '(':
                    tokens.add(new Token(Token.Tipo.PARENTESE_ESQ));
                    break;
                case ')':
                    tokens.add(new Token(Token.Tipo.PARENTESE_DIR));
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Caractere inválido na expressão: '" + c + "' (posição " + i + ")");
            }
            i++;
        }

        return tokens;
    }
}
