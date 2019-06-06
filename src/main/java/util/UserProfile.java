package util;

import java.util.Date;

public class UserProfile {
    static String[] MaritalStatus = {"Married","Single","Divorsced","Widow(er)","Separated","Living with Partner"};

    private String name;
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


}
