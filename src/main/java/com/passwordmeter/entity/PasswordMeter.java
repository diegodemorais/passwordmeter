package com.passwordmeter.entity;

import java.io.Serializable;
import java.util.Arrays;
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
    
    private String complexidade;
    private int nota=0;
    public int chars=0, upperLetters=0, lowLetters=0, numbers=0, symbols=0, midNumsSyms=0, requirements=0, lettersOnly=0, numbersOnly=0, repeatChars=0, upperConsec=0, lowConsec=0, numbersConsec=0, lettersSeq=0, numbersSeq=0, symbolsSeq=0;
    public int deduc=0, bonus=0;
    
           
    public int getNota() {
        return nota;
    }
    
     public String getComplexidade() {
        return complexidade;
    }
     
    public void setNotaComplexidade(String password){
        this.nota = 0;
        int bonus=0;
        int deductions=0;
        
        char[] pass = password.toCharArray();
        
        bonus = setBonus(pass);
        deductions = setDeductions(pass);
        
        this.nota = bonus - deductions;
        if (this.nota>100) this.nota = 100;
        this.complexidade =  this.setComplexidade();
    }
    
    private String setComplexidade(){
        if (this.nota == 0) return "Muito curta";
        else if (this.nota < 25) return "Muito fraca";
        else if (this.nota < 50) return "Fraca";
        else if (this.nota < 75) return "Boa";
        else if (this.nota < 100) return "Forte";
        else if (this.nota == 100) return "Muito forte";
        else return "Muito curta";
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
        
        this.bonus = bonus;
        
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
        
        this.deduc = deduc;
        
        return deduc;
    }
    
    private int size(char[] pass){
        return pass.length;
    }
    
    //Bonuses
    private int chars(char[] pass){
        int result = this.size(pass);
        result = (result*4);
        this.chars = result;
        return result;
    }
    
    private int upperLetters(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isUpperCase(c))
                result++;
        }

        result = (result > 0) ? ((this.size(pass)-result)*2) : 0;        
        this.upperLetters = result;
        return result;
    }    
    
    private int lowLetters(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isLowerCase(c))
                result++;
        }
        
        result = (result > 0) ? ((this.size(pass)-result)*2): 0;        
        this.lowLetters = result;
        return result;
    }
    private int numbers(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isDigit(c))
                result++;
        }
        result = (result*4);
        result = (this.lowLetters > 0 || this.upperLetters > 0) ? result : 0;
        this.numbers = result;
        return result;
    }
    
    private int symbols(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (!Character.isDigit(c) && !Character.isLetter(c))
                result++;
        }
        
        result = (result*6);        
        this.symbols = result;
        return result;
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
        
        result = (result*2);        
        this.midNumsSyms = result;
        return result;
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

        result = (result*2);        
        this.requirements = result;
        return result;
    }
    
    //Deductions
    private int lettersOnly(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isLetter(c))
                result++;
        }
        result = (result==this.size(pass)) ? result : 0;        
        this.lettersOnly = result;
        return result;
    }
    private int numbersOnly(char[] pass){
        int result=0;
        
        for(char c : pass){
            if (Character.isDigit(c))
                result++;
        }
        result = (result==this.size(pass)) ? result : 0;
        this.numbersOnly = result;
        return result;
    } 
    private int repeatChars(char[] pass){
        int result=0;
        int size=this.size(pass);
        int[] repeats = new int[size];
        
        if (size>=2) {
            String passLower = pass.toString().toLowerCase();
            char[] passLowerChar = passLower.toCharArray();
            
            for (int i=0;i<size;i++){
                int cnt=0, idx=0;
                while(true){
                    idx = passLower.indexOf(passLower, idx);
                    if (idx == -1) break;
                    idx++;
                    cnt++;
                }
                if (cnt>1 && repeats[passLowerChar[i]]==0){
                    repeats[passLowerChar[i]]=cnt;
                    result += cnt;
                }
            }
        }
        if (result>0) result = (result / size) *10;
        
        this.repeatChars = result;
        return result;
        
//var repeats = {};
//var _p = p.toLowerCase();
//var arr = _p.split('');
//counts.neg.repeated = 0;
//for(i = 0; i< arr.length; i++) {
//var cnt = 0, idx = 0;
//while (1) {
//        var idx = _p.indexOf(arr[i], idx);
//        if (idx == -1) break;
//        idx++;
//        cnt++;
//}
//if (cnt > 1 && !repeats[_p[i]]) {
//        repeats[_p[i]] = cnt;
//        counts.neg.repeated += cnt;
//if (counts.neg.repeated) {
//        strength -= (counts.neg.repeated / counts.pos.numChars) * 10;


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
        
        this.upperConsec = result;
        
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
        
        result = (result*2);
        this.lowConsec = result;
        return result;
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
        result = (result*2);        
        this.numbersConsec = result;
        return result;
    } 
    private int lettersSeq(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=3) {
            fora: for(int i=0;i<size-2;i++){
                int[] sub = new int[3];
                for(int j=i,k=0;j<(i+3);j++,k++){
                    if (!Character.isLetter(pass[j]))
                        continue fora;
                    else 
                        sub[k] = (int) Character.toLowerCase(pass[j]);
                }
                Arrays.sort(sub);
                if ( (sub[2] == sub[1]+1) && (sub[1] == sub[0]+1) )
                    result++;
            }
        }
        result = (result > 0) ? (result*3) : 0;        
        this.lettersSeq = result;
        return result;
    } 
    private int numbersSeq(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=3) {
            fora: for(int i=0;i<size-2;i++){
                int[] sub = new int[3];
                for(int j=i,k=0;j<(i+3);j++,k++){
                    if (!Character.isDigit(pass[j]))
                        continue fora;
                    else 
                        sub[k] = (int) pass[j];
                }
                Arrays.sort(sub);
                if ( (sub[2] == sub[1]+1) && (sub[1] == sub[0]+1) )
                    result++;
            }
        }
        result = (result > 0) ? (result*3) : 0;        
        this.numbersSeq = result;
        return result;
    } 
    private int symbolsSeq(char[] pass){
        int result=0;
        int size=this.size(pass);
        
        if (size>=3) {
            fora: for(int i=0;i<size-2;i++){
                int[] sub = new int[3];
                for(int j=i,k=0;j<(i+3);j++,k++){
                    if (Character.isLetterOrDigit(pass[j]))
                        continue fora;
                    else 
                        sub[k] = (int) pass[j];
                }
                Arrays.sort(sub);
                if ( (sub[2] == sub[1]+1) && (sub[1] == sub[0]+1) )
                    result++;
            }
        }
        result = (result > 0) ? (result*3) : 0;        
        this.symbolsSeq = result;
        return result;
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