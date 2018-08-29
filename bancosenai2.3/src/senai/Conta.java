package senai;

import java.util.ArrayList;
        
public class Conta {
    
    private double saldo;
    private ArrayList<Double> extrato;
    private String numero;
    private String deposito;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
        this.extrato = new ArrayList<Double>();
    }

    public Conta() {
        
    }
    
    public double consultarSaldo() {
        return this.saldo;
    }
    
    public ArrayList<Double> consultarExtrato() {
        return this.extrato;
    }
    
    public double depositar(double deposito) {
        this.saldo = this.saldo + deposito;
        this.extrato.add(deposito);
        return this.saldo;
    }
    
    public double sacar(double saque) {
        if (saque <= saldo) {
            this.saldo = this.saldo - saque;
            this.extrato.add(-saque);
            return this.saldo;
        } else {
            return -1;
        }   
    }
}
