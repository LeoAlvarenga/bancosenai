package senai;

class Cliente {

    private String codigo;
    private String nome;
    private String dataDeNascimento;
    private Conta conta;
            
    public Cliente() {
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
     
    public Conta getConta() {
        return this.conta;
    }
    
    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
