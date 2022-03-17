public class Contact {
    private String FirstName;
    private String LastName;
    private String PhoneNumber;

    public Contact (String firstName, String lastName, String phoneNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.PhoneNumber = phoneNumber;
    }

    public String getFirstName(){
        return this.FirstName;
    }

    public String getLastName(){
        return this.LastName;
    }

    public String getPhoneNumber() {
        return this.PhoneNumber;
    }

    public  void setFirstName(String firstName){
        this.FirstName = firstName;
    }

    public void setLastName(String lastName){
        this.LastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber){
        this.PhoneNumber = phoneNumber;
    }
}
