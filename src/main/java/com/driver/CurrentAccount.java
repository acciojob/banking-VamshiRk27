package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isValid(tradeLicenseId)){
            String rearrangeId=arrangeId(tradeLicenseId);
            if(rearrangeId==""){
                throw new Exception("Valid License can not be generated");
            }
            else{
                this.tradeLicenseId=rearrangeId;
            }
        }
    }
    public String arrangeId(String Id){
        int n=Id.length();
        int[] count=new int[26];
        for(int i=0;i<count.length;i++){
            count[i]=0;
        }
        for(char ch:Id.toCharArray()){
            count[(int)ch-(int)'A']++;
        }
        char ch_max=getCountMax(count);
        int maxCount=count[(int)ch_max-(int)'A'];
        if(n%2==0) {
            if(maxCount>(n/2)+1){
                return "";
            }
        }
        else{
            if(maxCount>(n/2+2)){
                return "";
            }
        }
        char[] ans=new char[n];
        String res="";
        //Filling most frequent character
        int index=0;
        for(index=0;index<n;index+=2){
            if(count[ch_max]>0){
                ans[index]=ch_max;
                count[ch_max]--;
            }
            else{
                break;
            }
        }
        //Filling remaining characters
        for(int i=0; i<26;i++){
            char ch=(char)('A'+i);
            while(count[ch]>0){
                if(index>=n){
                    index=1;
                }
                ans[index]=ch;
                index+=2;
                count[ch]--;
            }
        }
    }
    public char getCountMax(int[] count){
        int max=0;
        char ch=0;
        for(int i=0;i<26;i++){
            if(count[i]>max){
                max=count[i];
                ch=(char)((int)'A'+i);
            }
        }
        return ch;
    }
    public boolean isValid(String licenseId){
        for (int i = 0;i<licenseId.length();i++){
            if(licenseId.charAt(i)==licenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
