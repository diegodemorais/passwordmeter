package com.passwordmeter.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author DM
 */
//@Entity
public class PasswordMeter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passwordMeterId;
    
    private int nota=0, complexidade=-1;
    
    public int getNota() {
        return nota;
    }
    
     public int getComplexidade() {
        return complexidade;
    }
     
    public void setNotaComplexidade(String password){
        int bonus;
        int deductions=0;
        
        char[] pass = password.toCharArray();
        
        bonus = setBonus(pass);
        deductions = setDeductions(pass);
        
        this.nota = bonus + deductions;
        this.complexidade =  this.setComplexidade();
    }
    
    private int setComplexidade(){
        if (this.nota < 25) return 0;
        else if (this.nota < 50) return 1;
        else if (this.nota < 75) return 3;
        else if (this.nota <= 100) return 4;
        else return -1;
    }
    
    private int setBonus(char[] pass){
        int bonus=0;
        
        bonus+=chars(pass);
        bonus+=upperLetters(pass);
        bonus+=lowLetters(pass);        
        bonus+=numbers(pass);        
        bonus+=symbols(pass);
        bonus+=midNumsSyms(pass);
        bonus+=requirements(pass);
        
        return bonus;
    }

    private int setDeductions(char[] pass){
        int deduc=0;
        
        deduc+=lettersOnly(pass);
        deduc+=numbersOnly(pass);
        deduc+=repeatChars(pass);
        deduc+=upperConsec(pass);
        deduc+=lowConsec(pass);        
        deduc+=numbersConsec(pass);
        deduc+=lettersSeq(pass);
        deduc+=numbersSeq(pass);
        deduc+=symbolsSeq(pass);
        
        return deduc;
    }
    
    private int size(char[] pass){
        return pass.length;
    }
    
    //Bonuses
    private int chars(char[] pass){
        int result = this.size(pass);
        
        return (result*4);
    }
    
    private int upperLetters(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isUpperCase(c))
                result++;
        }
        
        return ((this.size(pass)-result)*4);
    }    
    private int lowLetters(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isLowerCase(c))
                result++;
        }
        
        return ((this.size(pass)-result)*4);
    }
    private int numbers(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isDigit(c))
                result++;
        }
        
        return (result*4);
    }
    private int symbols(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (!Character.isDigit(c) && !Character.isLetter(c))
                result++;
        }
        
        return (result*6);
    }
    private int midNumsSyms(char[] pass){
        int result=0;
        int size = this.size(pass);
        
        if (size>=3){
            for(int i=1;i<(size-1);i++){
                if (!Character.isLetter(pass[i]))
                    result++;
            }            
        }

        return (result+2);
    }
    private int requirements(char[] pass){
//        Minimum 8 characters in length
//        Contains 3/4 of the following items:
//        - Uppercase Letters
//        - Lowercase Letters
//        - Numbers
//        - Symbols        
        int result=0;
        
        if (this.size(pass)>=8){
            result++;
            if (this.upperLetters(pass)>0) result++;
            if (this.lowLetters(pass)>0) result++;    
            if (this.numbers(pass)>0) result++;
            if (this.symbols(pass)>0) result++;
        }
        return (result*2);
    }
    
    //Deductions
    private int lettersOnly(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isLetter(c))
                result++;
        }
        
        return (result==this.size(pass)) ? -result : 0;
    }
    private int numbersOnly(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isDigit(c))
                result++;
        }
        return (result==this.size(pass)) ? -result : 0;
    } 
    private int repeatChars(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=2) {
            char last = pass[0];
            for(int i=1;i<size;i++){
                if (Character.toUpperCase(pass[i])==Character.toUpperCase(last))
                    result++;
                last=pass[i];
            }
        }
        return result;
    } 
    private int upperConsec(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=2) {
            boolean last = Character.isUpperCase(pass[0]);
            for(int i=1;i<size;i++){
                if (last && Character.isUpperCase(pass[i])) 
                    result++;
                last=Character.isUpperCase(pass[i]);
            }
        }
        return -(result*2);
    }     
    private int lowConsec(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=2) {
            boolean last = Character.isLowerCase(pass[0]);
            for(int i=1;i<size;i++){
                if (last && Character.isLowerCase(pass[i])) 
                    result++;
                last=Character.isLowerCase(pass[i]);
            }
        }
        return -(result*2);
    }     
    private int numbersConsec(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=2) {
            boolean last = Character.isDigit(pass[0]);
            for(int i=1;i<size;i++){
                if (last && Character.isDigit(pass[i])) 
                    result++;
                last=Character.isDigit(pass[i]);
            }
        }
        return -(result*2);
    } 
    private int lettersSeq(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=2) {
            int last = (int) (Character.isLetter(pass[0]) ? pass[0] : -1);
            for(int i=1;i<size;i++){
                if ((((int) pass[i]) == last+1) && Character.isLetter(pass[i])) 
                    result++;
                last=(int) (Character.isLetter(pass[i]) ? pass[i] : -1);
            }
        }
        return -(result*3);
    } 
    private int numbersSeq(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=2) {
            int last = (int) (Character.isDigit(pass[0]) ? pass[0] : -1);
            for(int i=1;i<size;i++){
                if ((((int) pass[i]) == last+1) && Character.isDigit(pass[i])) 
                    result++;
                last=(int) (Character.isDigit(pass[i]) ? pass[i] : -1);
            }
        }
        return -(result*3);
    } 
    private int symbolsSeq(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=2) {
            int last = (int) ((!Character.isDigit(pass[0]) && !Character.isLetter(pass[0])) ? pass[0] : -1);
            for(int i=1;i<size;i++){
                if ((((int) pass[i]) == last+1) && (!Character.isDigit(pass[i]) && !Character.isLetter(pass[i]))) 
                    result++;
                last=(int) ((!Character.isDigit(pass[i]) && !Character.isLetter(pass[i])) ? pass[i] : -1);
            }
        }
        return -(result*3);
    }     
     
//Additions	Type	Rate
//size Number of Characters	Flat	+(n*4)
//upperLetters Uppercase Letters	Cond/Incr	+((len-n)*2)
//lowLetters Lowercase Letters	Cond/Incr	+((len-n)*2)
//numbers Numbers	Cond	+(n*4)
//symbols Symbols	Flat	+(n*6)
//midNumsSyms Middle Numbers or Symbols	Flat	+(n*2)
//requirements Requirements	Flat	+(n*2)
//		
//lettersOnly Letters Only	Flat	-n
//numbersOnly Numbers Only	Flat	-n
//repeatChars Repeat Characters (Case Insensitive)	Comp	-
//upperConsec Consecutive Uppercase Letters	Flat	-(n*2)
//lowConsec Consecutive Lowercase Letters	Flat	-(n*2)
//numbersConsec Consecutive Numbers	Flat	-(n*2)
//lettersSeq Sequential Letters (3+)	Flat	-(n*3)
//numbersSeq Sequential Numbers (3+)	Flat	-(n*3)
//symbolsSeq Sequential Symbols (3+)	Flat	-(n*3)
    
    
    

}