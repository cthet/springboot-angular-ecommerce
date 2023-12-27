package com.ecommerce.model.user;


public class Info {

    private Civility civility;

    private String firstName;

    private String lastName;

    public User toUser(Info info){
        User user = new User();
        user.setCivility(info.getCivility());
        user.setFirstName(info.getFirstName());
        user.setLastName(info.getLastName());

        return user;
    }

    public Civility getCivility() {
        return civility;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
