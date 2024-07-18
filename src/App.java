import br.com.psouza.dao.ClienteMapDAO;
import br.com.psouza.dao.IClienteDAO;
import br.com.psouza.domain.Cliente;

import javax.swing.*;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        // Loop da mensagem para opções invalidas
        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "OPÇÃO INVÁLIDA! Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Green Dinner", JOptionPane.INFORMATION_MESSAGE);
        }

        // Loop para opções validas
        while (isOpcaoValida(opcao)) {

            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isOpcaoCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme o exemplo: Nome, CPF, Telefone, Número, Cidade, Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
               cadastrar(dados);
            } else if (isOpcaoConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isOpcaoExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente que deseja excluir",
                        "Excluir", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else if (isOpcaoAlteracao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os novos dados do cliente, o parametro de alteração será o CPF:",
                        "Alterar", JOptionPane.INFORMATION_MESSAGE);
                alterar(dados);
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 4-) Método parar alterar dados do cliente
    private static void alterar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Cliente clienteAConsultar = iClienteDAO.consultar(cliente.getCpf());
        if (clienteAConsultar != null) {
            iClienteDAO.alterar(cliente);
            JOptionPane.showMessageDialog(null, "Cliente " + cliente.toString() + " alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "CPF não existe", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 3-) Método para excluir cliente
    private static void excluir(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            iClienteDAO.excluir(Long.parseLong(dados));
            JOptionPane.showMessageDialog(null, "Cliente " + cliente.toString() + " excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente NÃO existe!", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 2-) Método para consultar cliente por CPF(dados)
    private static void consultar(String dados) {
        // Validar se foi passado somento o CPF!
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente NÃO encontrado :(", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 1-) Método para cadastrar clientes recebendo dados
    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        // Tentar validar se todos os campos estão preenchidos!
        // Se não tiver, passsar no construtor onde o valor é nulo
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado!", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Opcao sair, fecha a aplicação
    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }


    private static boolean isOpcaoExclusao(String opcao) {
        if ("3".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoAlteracao(String opcao) {
        if ("4".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }

}
