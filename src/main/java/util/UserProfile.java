package util;

import java.util.Date;
import java.util.Random;

public class UserProfile {
    static String[] MaritalStatus = {"Married","Single","Divorsced","Widow(er)","Separated","Living with Partner"};

    private String name;
    private String phone;
    private String surname;
    private String email;
    private String password;
    private String title;
    private String gender;
    private Date dateOfBirth;
    private String maritalStatus;
    private String NINumber;
    private boolean UKdomiciled;
    private boolean UKTaxResident;
    private String BankAccountNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNINumber() {
        return NINumber;
    }

    public void setNINumber(String NINumber) {
        this.NINumber = NINumber;
    }

    public boolean isUKdomiciled() {
        return UKdomiciled;
    }

    public void setUKdomiciled(boolean UKdomiciled) {
        this.UKdomiciled = UKdomiciled;
    }

    public boolean isUKTaxResident() {
        return UKTaxResident;
    }

    public void setUKTaxResident(boolean UKTaxResident) {
        this.UKTaxResident = UKTaxResident;
    }

    public String getBankAccountNumber() {
        return BankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        BankAccountNumber = bankAccountNumber;
    }

    public  UserProfile(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public  UserProfile(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    static public UserProfile createUserWithRandomNameAndSurname(){
        String randomName = getRandomString();
        String randomSurname = getRandomString();
        return new UserProfile(randomName,randomSurname);
    }

    public void generatePassword(){
        this.setPassword(getRandomString());
    }

    public void generatePhone(){
        setPhone(getRandomNumberString());
    }

    static public String getRandomString() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    static public String getRandomNumberString(){
        Random rand = new Random();

        int num1, num2, num3;

        num1 = rand.nextInt (900) + 100;
        num2 = rand.nextInt (643) + 100;
        num3 = rand.nextInt (9000) + 1000;

        System.out.println(num1+"-"+num2+"-"+num3);
        String out = Integer.toString(num1) + Integer.toString(num2) + Integer.toString(num3);
        return out;
    }
}
