/**
 * Cinco expressoes usadas para testar a aplicacao (item 2.2).
 * Imprime a expressao original, a convertida para RPN e o resultado.
 */
public class ExemplosParte2 {
    public static void main(String[] args) {
        String[] expressoes = {
                "3 + 4 * 2",
                "(3 + 4) * 2",
                "10 / 2 - 3",
                "2.5 + 3.75 * 2",
                "(1.5 + 2.5) / (4 - 2)"
        };

        for (String expressao : expressoes) {
            Calculadora.Resultado r = Calculadora.calcular(expressao);
            System.out.println("Expressão original : " + r.expressaoOriginal);
            System.out.println("Expressão em RPN   : " + r.expressaoRPN);
            System.out.println("Resultado           : " + r.valor);
            System.out.println("------------------------------------------------");
        }
    }
}
