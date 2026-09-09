import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Roda os cinco experimentos da parte 3 na aplicacao da parte 2.
 * O valor esperado usa BigDecimal, e nao double: em double ele sofreria
 * o mesmo arredondamento do resultado obtido e a diferenca sumiria.
 */
public class ExperimentosParte3 {

    static class Experimento {
        String expressao;
        BigDecimal esperadoMatematico;
        String observacao;

        Experimento(String expressao, BigDecimal esperadoMatematico, String observacao) {
            this.expressao = expressao;
            this.esperadoMatematico = esperadoMatematico;
            this.observacao = observacao;
        }
    }

    public static void main(String[] args) {
        Experimento[] experimentos = {
                new Experimento("0.1 + 0.2",
                        new BigDecimal("0.1").add(new BigDecimal("0.2")),
                        "exato em base 10"),
                new Experimento("0.1 + 0.1 + 0.1",
                        new BigDecimal("0.1").add(new BigDecimal("0.1")).add(new BigDecimal("0.1")),
                        "exato em base 10"),
                new Experimento("(0.1 + 0.2) - 0.3",
                        new BigDecimal("0.1").add(new BigDecimal("0.2")).subtract(new BigDecimal("0.3")),
                        "exato em base 10"),
                new Experimento("1.0 / 3.0",
                        BigDecimal.ONE.divide(new BigDecimal("3"), new MathContext(50, RoundingMode.HALF_UP)),
                        "dizima periodica; truncada em 50 casas para comparar"),
                new Experimento("10000000000000000.0 + 1.0",
                        new BigDecimal("10000000000000000").add(BigDecimal.ONE),
                        "exato em base 10 (número inteiro)")
        };

        System.out.printf("%-30s | %-28s | %-25s | %s%n",
                "Expressao", "Resultado esperado", "Resultado obtido", "Diferenca (esperado-obtido)");
        System.out.println("-".repeat(130));

        for (Experimento e : experimentos) {
            Calculadora.Resultado r = Calculadora.calcular(e.expressao);
            BigDecimal obtido = new BigDecimal(r.valor); // valor exato do double
            BigDecimal diferenca = e.esperadoMatematico.subtract(obtido);

            System.out.printf("%-30s | %-28s | %-25s | %s%n",
                    e.expressao,
                    truncar(e.esperadoMatematico, 20),
                    truncar(obtido, 20),
                    truncar(diferenca, 20));
            System.out.println("   -> double bruto (r.valor)         : " + r.valor);
            System.out.println("   -> RPN                            : " + r.expressaoRPN);
            System.out.println("   -> observação                     : " + e.observacao);
            System.out.println();
        }
    }

    private static String truncar(BigDecimal valor, int casas) {
        return valor.setScale(casas, RoundingMode.HALF_UP).toPlainString();
    }
}
