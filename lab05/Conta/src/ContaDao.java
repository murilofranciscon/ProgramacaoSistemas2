import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

// jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.wpdkuovjwcbnuhxhbyjh&password=senhalab00002
public class ContaDao implements IContaDao {
    private PreparedStatement pstmCreate;
    private PreparedStatement pstmRead;
    private PreparedStatement pstmReadByNumber;
    private PreparedStatement pstmUpdate;
    private PreparedStatement pstmDelete;

    public ContaDao(Connection c) throws Exception {
        pstmCreate = c.prepareStatement("INSERT INTO CONTAS VALUES (?, ?)");
        pstmRead = c.prepareStatement("SELECT * FROM CONTAS");
        pstmReadByNumber = c.prepareStatement("SELECT * FROM CONTAS WHERE NRO_CONTA = ?");
        pstmUpdate = c.prepareStatement("UPDATE CONTAS SET SALDO = ? WHERE NRO_CONTA = ?");
        pstmDelete = c.prepareStatement("DELETE FROM CONTAS WHERE NRO_CONTA = ?");
    }

    @Override
    public boolean criar(Conta c) {
        try {
            pstmCreate.setLong(1, c.getNumero());
            pstmCreate.setBigDecimal(2, c.getSaldo());
            int ret = pstmCreate.executeUpdate();
            return ret > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Conta> lerTodas() throws Exception {
        List<Conta> contas = new ArrayList<>();
        ResultSet resultados = pstmRead.executeQuery();
        while (resultados.next()) {
            long n = resultados.getLong("nro_conta");
            BigDecimal s = resultados.getBigDecimal("saldo");
            Conta c = new Conta(n, s);
            contas.add(c);
        }
        return contas;
    }

    @Override
    public Conta buscarPeloNumero(long id) {
        try {
            pstmReadByNumber.setLong(1, id);
            ResultSet resultados = pstmReadByNumber.executeQuery();
            if (resultados.next()) {
                long n = resultados.getLong("nro_conta");
                BigDecimal s = resultados.getBigDecimal("saldo");
                return new Conta(n, s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean atualizar(Conta c) {
        try {
            pstmUpdate.setBigDecimal(1, c.getSaldo());
            pstmUpdate.setLong(2, c.getNumero());
            int alterados = pstmUpdate.executeUpdate();
            return alterados > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean apagar(Conta c) {
        try {
            pstmDelete.setLong(1, c.getNumero());
            int deletados = pstmDelete.executeUpdate();
            return deletados > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}