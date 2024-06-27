package org.m0d3rn1ca.restaurantcrm;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Cloneable {
    private int ID;
    private String last_name;
    private String first_name;
    private String patronymic;
    private String login;
    private String hashed_password;
    private String ITN;
    private String phone;
    private String address;

    private User() {

    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return hashed_password;
    }

    public String getITN() {
        return ITN;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getFullName() {
        // Если у человека отсутствует отчество, то оно не возвращается
        if (patronymic == null || patronymic.isBlank())
            return String.format("%s %s", last_name, first_name);
        return String.format("%s %s %s", last_name, first_name, patronymic);
    }

    public String getShortName() {
        // Если у человека отсутствует отчество, то оно не возвращается
        if (patronymic == null || patronymic.isBlank())
            return String.format("%s %s.", last_name, first_name.charAt(0));
        return String.format("%s %s. %s.", last_name, first_name.charAt(0), patronymic.charAt(0));
    }

    public static String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded_hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return new String(encoded_hash, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }

    public static Builder newBuilder(int ID, String login) {
        return new User().new Builder(ID, login);
    }

    public Builder reBuilder() {
        return this.new Builder(ID, login);
    }

    @Override
    public User clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public class Builder {
        private Builder(int ID, String login) {
            User.this.ID = ID;
            User.this.login = login;
        }

        public Builder setPassword(String password) {
            User.this.hashed_password = password;
            return this;
        }

        public Builder setFirstName(String first_name) {
            User.this.first_name = first_name;
            return this;
        }

        public Builder setLastName(String last_name) {
            User.this.last_name = last_name;
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            User.this.patronymic = patronymic;
            return this;
        }

        public Builder setITN(String ITN) {
            User.this.ITN = ITN;
            return this;
        }

        public Builder setPhone(String phone) {
            User.this.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            User.this.address = address;
            return this;
        }

        public User build() {
            return User.this;
        }

    }
}
