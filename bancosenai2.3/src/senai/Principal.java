package senai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    private static Cliente cliente;
    
    private static Conta conta;

    private static Scanner scan = new Scanner(System.in);
    
    private static String codCliente = "";
    
    private static String deposito = "+";
    
    private static String saque = "-";
    
    private static String extrato = "";
    
    private static String valor = "";
    
    private static String conta1 = "";
    
    private static String conta2 = "";
    
    private static String codigoo = "";
    
    

    public static void main(String[] args) {
        exibirlogin();
    }

    private static void exibirMenuPrincipal() throws IOException {
        int opcao = -1;
        while (opcao != 9) {
            System.out.println("BANCO SENAI");
            System.out.println("");
            System.out.println("--------- MENU PRINCIPAL ---------");
            System.out.println("(0) Cadastrar Cliente");
            System.out.println("(1) Consultar Cliente");
            System.out.println("(2) Exibir Clientes Cadastrados");
            System.out.println("");
            System.out.println("(9) Encerrar");
            System.out.print("Entre com a opção: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 0:
                    cadastrarCliente();
                    break;
                case 1:
                    consultarCliente();
                    break;
                case 2:
                    exibirClientes();
                    break;
                case 9:
                    System.out.println("Até mais");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void cadastrarCliente() throws IOException {
        scan.nextLine();
        System.out.println("Digite os dados do cliente:");
        System.out.print("Código: ");
        String codigo = scan.nextLine();
        System.out.print("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Data de Nascimento: ");
        String dataDeNascimento = scan.nextLine();
        cliente = new Cliente();
        cliente.setCodigo(codigo);
        cliente.setNome(nome);
        cliente.setDataDeNascimento(dataDeNascimento);
        boolean clienteEncontrado = false;
        try {
            FileReader leitor = new FileReader("cliente.csv");
            BufferedReader leitorCliente = new BufferedReader(leitor);
            String linhaCliente = leitorCliente.readLine();
            String[] cliente;

            while (linhaCliente != null) {
                cliente = linhaCliente.split(";");
                if (codigo.equals(cliente[0])) {
                    clienteEncontrado = true;
                }

                linhaCliente = leitorCliente.readLine();

            }
        } catch (Exception e) {

        }

        if (clienteEncontrado != true) {
            salvarCliente(cliente);
        } else {
            System.out.println("Código já cadastrado!");

        }
    }

    private static void salvarCliente(Cliente cliente) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("cliente.csv", true);
            BufferedWriter clienteWriter = new BufferedWriter(fileWriter);
            String linhaCliente = cliente.getCodigo() + ";" + cliente.getNome() + ";" + cliente.getDataDeNascimento();
            clienteWriter.write(linhaCliente);
            clienteWriter.newLine();
            clienteWriter.close();
            fileWriter.close();

        } catch (IOException e) {

        }
        System.out.println("Cliente Cadastrado com sucesso!");
    }

    private static void consultarCliente() {
        System.out.println("Digite o código de cliente: ");
        Scanner osc = new Scanner(System.in);
        String codigo = osc.nextLine();
        boolean clienteEncontrado = false;
        try {
            FileReader leitor = new FileReader("cliente.csv");
            BufferedReader leitorCliente = new BufferedReader(leitor);
            String linhaCliente = leitorCliente.readLine();
            String[] cliente;
            String a = "";
            String b = "";
            String c = "";

            while (linhaCliente != null) {
                cliente = linhaCliente.split(";");
                if (codigo.equals(cliente[0])) {
                    clienteEncontrado = true;
                    a = cliente[0];
                    b = cliente[1];
                    c = cliente[2];
                    
                }

                linhaCliente = leitorCliente.readLine();

            }
            if (clienteEncontrado == true) {
                System.out.println("Cliente encontrado!");
                System.out.println("codigo: " + a + " nome: " + b + " data de nascimento: " + c);
                codCliente = a;
                exibirMenuCliente();

            } else {
                System.out.println("Cliente inexistente!");
                
            }
        } catch (Exception e) {

        }
        
    }

    

    private static void exibirMenuCliente() {
        int opcao = -1;
        while (opcao != 9) {
            System.out.println("BANCO SENAI");
            System.out.println("");
            System.out.println("--------- MENU CLIENTE ---------");
            System.out.println("(0) Criar Conta");
            System.out.println("(1) Exibir Extrato");
            System.out.println("(2) Sacar");
            System.out.println("(3) Depositar");
            System.out.println("(4) Consultar Saldo");
            System.out.println("(5) Transferir");
            System.out.println("(6) Listar Contas do Cliente");
            System.out.println("");
            System.out.println("(9) Retornar ao menu principal");
            System.out.print("Entre com a opção: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 0:
                    criarConta();
                    break;
                case 1:
                    exibirExtratoConta();
                    break;
                case 2:
                    sacarConta();
                    break;
                case 3:
                    depositarConta();
                    break;
                case 4:
                    consultarSaldo();
                case 5:
                    transferir();
                case 6:
                    listarConta();
                case 9:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void criarConta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da conta (4 Dígitos): ");
        String num = sc.nextLine();
        System.out.println("Informe o Depósito Inicial: ");
        String dep = sc.nextLine();
        System.out.println("Informe a Data de Abertura: ");
        String data = sc.nextLine();
        conta = new Conta();
        conta.setNumero(num);
        conta.setDeposito(dep);
        conta.setData(data);
        boolean contaEncontrada = false;
        try {
            FileReader leitor = new FileReader("conta.csv");
            BufferedReader leitorConta = new BufferedReader(leitor);
            String linhaConta = leitorConta.readLine();
            String[] conta;

            while (linhaConta != null) {
                conta = linhaConta.split(";");
                if (num.equals(conta[1])) {
                    contaEncontrada = true;
                }

                linhaConta = leitorConta.readLine();

            }
            if (contaEncontrada == true) {
                System.out.println("Conta já existe!");

            } else {
                extrato = deposito + dep;
                salvarConta();
                salvarExtrato();
            }
        } catch (Exception e) {
            System.out.println(e);
        
    }
    }

    //private static void exibirSaldoConta() {
    //    if (cliente.getConta() == null) {
    //        System.out.println("Conta inexistente.");
    //    } else {
    //        double saldo = cliente.getConta().consultarSaldo();
    //        System.out.println("O saldo é: " + saldo);
    //    }
    //}

    private static void exibirExtratoConta() {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite o numero de conta: ");
        String num = s.nextLine();
        boolean contaEncontrada = false;
        try {
            FileReader leitor = new FileReader("extrato.csv");
            BufferedReader leitorExtrato = new BufferedReader(leitor);
            String linhaExtrato = leitorExtrato.readLine();
            String[] extrato;

            while (linhaExtrato != null) {
                extrato = linhaExtrato.split(";");
                if (num.equals(extrato[1]) && codCliente.equals(extrato[0])) {
                    System.out.println("Operação: " + extrato[2] + " Data: " + extrato[3]);
                    contaEncontrada = true;
                }

                linhaExtrato = leitorExtrato.readLine();

            }
            if (contaEncontrada == true) {
                System.out.println("Todas operações mostradas!");

            } else {
                System.out.println("Cliente nao tem acesso a essa conta!");
            }
        } catch (Exception e) {
            System.out.println(e);
        
    }
    }

    private static void depositarConta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da conta: ");
        String num = sc.nextLine();
        boolean contaEncontrada = false;
        try {
            FileReader leitor = new FileReader("extrato.csv");
            BufferedReader leitorE = new BufferedReader(leitor);
            String linhaE = leitorE.readLine();
            String[] extrato1;
            
            while (linhaE != null) {
                extrato1 = linhaE.split(";");
                if (num.equals(extrato1[1]) && codCliente.equals(extrato1[0])) {
                    contaEncontrada = true;
                    
                }

                linhaE = leitorE.readLine();

            }
            if (contaEncontrada == true) {
                System.out.println("Digite o valor: ");
                valor = sc.nextLine();
                conta = new Conta();
                conta.setNumero(num);
                extrato = deposito + valor;
                salvarExtrato();

            } else {
                System.out.println("Este cliente não possui acesso a conta digitada!");
                
            }
        } catch (Exception e) {

        }
        
        
    }

    private static void sacarConta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da conta: ");
        String num = sc.nextLine();
        boolean contaEncontrada = false;
        try {
            FileReader leitor = new FileReader("extrato.csv");
            BufferedReader leitorE = new BufferedReader(leitor);
            String linhaE = leitorE.readLine();
            String[] extrato1;
            
            while (linhaE != null) {
                extrato1 = linhaE.split(";");
                if (num.equals(extrato1[1]) && codCliente.equals(extrato1[0])) {
                    contaEncontrada = true;
                    
                }

                linhaE = leitorE.readLine();

            }
            if (contaEncontrada == true) {
                System.out.println("Digite o valor: ");
                valor = sc.nextLine();
                conta = new Conta();
                conta.setNumero(num);
                extrato = saque + valor;
                salvarExtrato();

            } else {
                System.out.println("Este cliente não possui acesso a conta digitada!");
                
            }
        } catch (Exception e) {

        }
        
                
    }

    private static void exibirlogin() {
        try {
            FileReader leitor = new FileReader("login.csv");
            BufferedReader leitorLogin = new BufferedReader(leitor);
            String linhaLogin = leitorLogin.readLine();
            String[] login;
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o Login e Senha");
            System.out.println("");
            System.out.println("Login:");
            String l = sc.nextLine();
            System.out.println("Senha:");
            String s = sc.nextLine();
            boolean loginEncontrado = false;
            while (linhaLogin != null) {
                login = linhaLogin.split(";");
                if (l.equals(login[1]) && s.equals(login[2])) {
                    System.out.println("Seja Bem-vindo " + login[0] + "!");
                    loginEncontrado = true;
                    exibirMenuPrincipal();
                }
                linhaLogin = leitorLogin.readLine();
            }
            if (loginEncontrado != true) {
                System.out.println("Usuário não encontrado");
            }

        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void exibirClientes() {
        try {
            FileReader leitor = new FileReader("cliente.csv");
            BufferedReader leitorCliente = new BufferedReader(leitor);
            String linhaCliente = leitorCliente.readLine();
            String[] cliente;
            while (linhaCliente != null) {
                cliente = linhaCliente.split(";");
                System.out.println("Código: " + cliente[0] + " Nome: " + cliente[1]);
                linhaCliente = leitorCliente.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void salvarConta() {
        try {
            FileWriter fileWriter = new FileWriter("conta.csv", true);
            BufferedWriter contaWriter = new BufferedWriter(fileWriter);
            String linhaConta = codCliente + ";" + conta.getNumero() + ";" + conta.getData();
            contaWriter.write(linhaConta);
            contaWriter.newLine();
            contaWriter.close();
            fileWriter.close();

        } catch (IOException e) {

        }
        System.out.println("Conta Cadastrado com sucesso!");
    }

    private static void salvarExtrato() {
        Date data = new Date();
        try {
            FileWriter fileWriter = new FileWriter("extrato.csv", true);
            BufferedWriter extratoWriter = new BufferedWriter(fileWriter);
            String linhaExtrato = codCliente + ";" + conta.getNumero() + ";" + extrato + ";" + data;
            extratoWriter.write(linhaExtrato);
            extratoWriter.newLine();
            extratoWriter.close();
            fileWriter.close();

        } catch (IOException e) {

        }
        System.out.println("Extrato Atualizado com sucesso!");
    
    }

    private static void listarConta() {
        boolean contaEncontrada = false;
        try {
            FileReader leitor = new FileReader("conta.csv");
            BufferedReader leitorConta = new BufferedReader(leitor);
            String linhaConta = leitorConta.readLine();
            String[] conta;

            while (linhaConta != null) {
                conta = linhaConta.split(";");
                if (codCliente.equals(conta[0])) {
                    System.out.println("Conta: " + conta[1] + " Aberta no dia: " + conta[2]);
                    contaEncontrada = true;
                }

                linhaConta = leitorConta.readLine();

            }
            if (contaEncontrada == true) {
                System.out.println("Todas as contas do cliente foram exibidas");

            } else {
                System.out.println("Cliente não possui contas!");
            }
        } catch (Exception e) {
            System.out.println(e);
        
    }

    }

    private static void consultarSaldo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da conta");
        String num = sc.nextLine();
        Double saldo = 0.0;
        boolean contaEncontrada = false;
        try {
            FileReader leitor = new FileReader("extrato.csv");
            BufferedReader leitorExtrato = new BufferedReader(leitor);
            String linhaExtrato = leitorExtrato.readLine();
            String[] extrato;

            while (linhaExtrato != null) {
                extrato = linhaExtrato.split(";");
                if (num.equals(extrato[1]) && codCliente.equals(extrato[0])) {
                    saldo = saldo + Double.parseDouble(extrato[2]);
                    contaEncontrada = true;
                }

                linhaExtrato = leitorExtrato.readLine();

            }
            if (contaEncontrada == true) {
                System.out.println("O Saldo atual da Conta é: " + saldo);

            } else {
                System.out.println("Cliente nao tem acesso a essa conta!");
            }
        } catch (Exception e) {
            System.out.println(e);
        
    }
    }

    private static void transferir() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da Conta a ser Retirado o Valor: ");
        conta1 = sc.nextLine();
        System.out.println("Digite o Código de Usuário e o número da Conta que irá Receber o Valor: ");
        System.out.println("Código: ");
        codigoo = sc.nextLine();
        System.out.println("Conta: ");
        conta2 = sc.nextLine();
        System.out.println("Digite o Valor a ser transferido: ");
        valor = sc.nextLine();
        transferir1();
        transferir2();
        
       
        
        
    }

    private static void transferir1() {
        Scanner sc = new Scanner(System.in);
        boolean contaEncontrada = false;
        try {
            FileReader leitor = new FileReader("extrato.csv");
            BufferedReader leitorE = new BufferedReader(leitor);
            String linhaE = leitorE.readLine();
            String[] extrato1;
            
            while (linhaE != null) {
                extrato1 = linhaE.split(";");
                if (conta1.equals(extrato1[1]) && codCliente.equals(extrato1[0])) {
                    contaEncontrada = true;
                    
                }

                linhaE = leitorE.readLine();

            }
            if (contaEncontrada == true) {
                conta = new Conta();
                conta.setNumero(conta1);
                extrato = saque + valor;
                salvarExtrato();
                

            } else {
                System.out.println("Este cliente não possui acesso a conta digitada!");
                
            }
        } catch (Exception e) {

        }
    }

    private static void transferir2() {
        Scanner sc = new Scanner(System.in);
        boolean contaEncontrada = false;
        try {
            FileReader leitor = new FileReader("extrato.csv");
            BufferedReader leitorE = new BufferedReader(leitor);
            String linhaE = leitorE.readLine();
            String[] extrato1;
            
            while (linhaE != null) {
                extrato1 = linhaE.split(";");
                if (conta2.equals(extrato1[1]) && codigoo.equals(extrato1[0])) {
                    contaEncontrada = true;
                    
                }

                linhaE = leitorE.readLine();

            }
            if (contaEncontrada == true) {
                conta = new Conta();
                conta.setNumero(conta2);
                extrato = deposito + valor;
                salvarExtrato0();

            } else {
                System.out.println("Conta para Tranferencia não existe!");
                
            }
        } catch (Exception e) {

        }
    }

    private static void salvarExtrato0() {
        Date data = new Date();
        try {
            FileWriter fileWriter = new FileWriter("extrato.csv", true);
            BufferedWriter extratoWriter = new BufferedWriter(fileWriter);
            String linhaExtrato = codigoo + ";" + conta.getNumero() + ";" + extrato + ";" + data;
            extratoWriter.write(linhaExtrato);
            extratoWriter.newLine();
            extratoWriter.close();
            fileWriter.close();

        } catch (IOException e) {

        }
        System.out.println("Extrato Atualizado com sucesso!");
    }
        

}
