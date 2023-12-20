interface BankInterface{
    int getBalance();
    double getInterestRate();
}

class BankA implements BankInterface{
    
    int balance = 10000;
    
    public int getBalance(){
        return balance;
    }

    public double getInterestRate(){
        return balance*(7/100);
    }
}

class BankB implements BankInterface{
    
    int balance = 150000;
    
    public int getBalance(){
        return balance;
    }

    public double getInterestRate(){
        return balance*(7.4/100);
    }
}

class BankC implements BankInterface{
    
    int balance = 200000;
    
    public int getBalance(){
        return balance;
    }

    public double getInterestRate(){
        return balance*(7.9/100);
    }
}

class lab5{
    public static void main(String[] args){
        BankA objA = new BankA();
        BankB objB = new BankB();
        BankC objC = new BankC();

        System.out.println("Balance of Bank A: "+objA.getBalance());
        System.out.println("Interest of Bank A: "+objA.getInterestRate()+"\n\n");

        System.out.println("Balance of Bank B: "+objB.getBalance());
        System.out.println("Interest of Bank B: "+objB.getInterestRate()+"\n\n");

        System.out.println("Balance of Bank C: "+objC.getBalance());
        System.out.println("Interest of Bank C: "+objC.getInterestRate());
    }
}