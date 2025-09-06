import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class java04 {
    public static void main(String[] args) throws Exception {
        System.out.println("Consulta de contas!");
        
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Menu de Contas Bancárias ---");
            System.out.println("1 - Criar nova conta");
            System.out.println("2 - Atualizar saldo de conta");
            System.out.println("3 - Apagar conta");
            System.out.println("4 - Ver contas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            try {
                switch (opcao) {
                    case 1:
                        create();
                        break;
                    case 2:
                        update();
                        break;
                    case 3:
                        delete();
                        break;
                    case 4:
                        read();
                        break;
                    case 0:
                        System.out.println("Saindo do programa...");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            }
        } while (opcao != 0);

        sc.close();
    }




    public static void create() throws SQLException {
    System.out.print("Número para a nova conta: ");
    long nro = Long.parseLong(System.console().readLine());
    System.out.print("Saldo da nova conta: ");
    BigDecimal saldo = new BigDecimal(System.console().readLine());
    String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.wpdkuovjwcbnuhxhbyjh&password=senhalab00002";
    Connection c = DriverManager.getConnection(url);
    String sql = "INSERT INTO contas VALUES (?,?)";
    PreparedStatement prepstm = c.prepareStatement(sql);
    prepstm.setLong(1, nro);
    prepstm.setBigDecimal(2, saldo);
    int ret = prepstm.executeUpdate();
    System.out.println("Número de registros inseridos: " + ret);
    c.close();
}
public static void update() throws SQLException {
    System.out.print("Número de uma conta já existente: ");
    long nro = Long.parseLong(System.console().readLine());
    System.out.print("Novo saldo para esta conta: ");
    BigDecimal saldo = new BigDecimal(System.console().readLine());
    String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.wpdkuovjwcbnuhxhbyjh&password=senhalab00002";
    Connection c = DriverManager.getConnection(url);
    String sql = "UPDATE contas SET saldo=? WHERE nro_conta=?";
    PreparedStatement prepstm = c.prepareStatement(sql);
    prepstm.setBigDecimal(1, saldo);
    prepstm.setLong(2, nro);
    int ret = prepstm.executeUpdate();
    System.out.println("Número de registros alterados: " + ret);
    c.close();
}
public static void delete() throws SQLException {
    System.out.print("Número de uma conta já existente: ");
    long nro = Long.parseLong(System.console().readLine());
    String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.wpdkuovjwcbnuhxhbyjh&password=senhalab00002";
    Connection c = DriverManager.getConnection(url);
    String sql = "DELETE FROM contas WHERE nro_conta=?";
    PreparedStatement prepstm = c.prepareStatement(sql);
    prepstm.setLong(1, nro);
    int ret = prepstm.executeUpdate();
    System.out.println("Número de registros apagados: " + ret);
    c.close();
}
public static void read() throws SQLException{
        String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.wpdkuovjwcbnuhxhbyjh&password=senhalab00002";
        Connection c = DriverManager.getConnection(url);
        System.out.println("Conexão ok!");
        String sql = "SELECT * FROM CONTAS";
        PreparedStatement stm = c.prepareStatement(sql);
        ResultSet resultado = stm.executeQuery();
        while (resultado.next()) {
            long nro = resultado.getLong("nro_conta");
            double saldo = resultado.getDouble("saldo");
            System.out.println("Número: " + nro + " - R$ " + saldo);
        }
        c.close();
    }
}
