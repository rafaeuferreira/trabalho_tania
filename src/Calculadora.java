import java.util.List;

/**
 * Calculadora de expressoes infixas.
 * Fluxo: String -> tokens -> RPN (Shunting Yard) -> avaliacao com pilha.
 * Aceita inteiros e decimais, os operadores + - * / e parenteses.
 * Todos os calculos usam double.
 */
public class Calculadora {

    public static class Resultado {
        public final String expressaoOriginal;
        public final String expressaoRPN;
        public final double valor;

        public Resultado(String expressaoOriginal, String expressaoRPN, double valor) {
            this.expressaoOriginal = expressaoOriginal;
            this.expressaoRPN = expressaoRPN;
            this.valor = valor;
        }
    }

    public static Resultado calcular(String expressaoInfixa) {
        List<Token> tokensInfixos = Tokenizador.tokenizar(expressaoInfixa);
        List<Token> tokensRPN = ConversorInfixaParaRPN.converter(tokensInfixos);
        String rpnFormatada = ConversorInfixaParaRPN.paraString(tokensRPN);
        double valor = AvaliadorRPN.avaliar(tokensRPN);
        return new Resultado(expressaoInfixa, rpnFormatada, valor);
    }

    /** Uso: java Calculadora "(2.5 + 3) * 4" */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java Calculadora \"<expressao>\"");
            return;
        }
        StringBuilder expr = new StringBuilder();
        for (String a : args) {
            expr.append(a).append(' ');
        }
        Resultado r = calcular(expr.toString().trim());
        System.out.println("Expressão original : " + r.expressaoOriginal);
        System.out.println("Expressão em RPN   : " + r.expressaoRPN);
        System.out.println("Resultado           : " + r.valor);
    }
}
