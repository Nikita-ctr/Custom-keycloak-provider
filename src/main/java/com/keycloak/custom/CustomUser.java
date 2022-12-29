package com.keycloak.custom;

import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.adapter.AbstractUserAdapter;

import java.util.List;
import java.util.Map;

public class CustomUser extends AbstractUserAdapter {

    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String birthDate;

    private final String phoneNumber;

    private final boolean isEmailVerified;

    private final String about;


    public CustomUser(KeycloakSession session,
                      RealmModel realm,
                      ComponentModel storageProviderModel,
                      String username,
                      String email,
                      String firstName,
                      String lastName,
                      String birthDate,
                      String phoneNumber,
                      boolean isEmailVerified,
                      String about) {
        super(session, realm, storageProviderModel);
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.isEmailVerified = isEmailVerified;
        this.about = about;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean isEmailVerified() {
        return isEmailVerified;
    }


    public String getAbout() {
        return about;
    }

    @Override
    public Map<String, List<String>> getAttributes() {
        MultivaluedHashMap<String, String> attributes = new MultivaluedHashMap<>();
        attributes.add(UserModel.USERNAME, getUsername());
        attributes.add(UserModel.EMAIL, getEmail());
        attributes.add(UserModel.FIRST_NAME, getFirstName());
        attributes.add(UserModel.LAST_NAME, getLastName());
        attributes.add(("isEmailVerified"), String.valueOf(isEmailVerified()));
        attributes.add("birthDate", getBirthDate());
        attributes.add("phone", getPhoneNumber());
        attributes.add("about", getAbout());
        return attributes;
    }

    static class Builder {
        private final KeycloakSession session;
        private final RealmModel realm;
        private final ComponentModel storageProviderModel;
        private final String username;
        private String email;
        private String firstName;
        private String lastName;
        private String birthDate;

        private boolean isEmailVerified;

        private String phoneNumber;

        private String about;

        Builder(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, String username) {
            this.session = session;
            this.realm = realm;
            this.storageProviderModel = storageProviderModel;
            this.username = username;
        }

        CustomUser.Builder email(String email) {
            this.email = email;
            return this;
        }

        CustomUser.Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        CustomUser.Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        CustomUser.Builder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        CustomUser.Builder isEmailVerified(boolean isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
            return this;
        }

        CustomUser.Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        CustomUser.Builder about(String about) {
            this.about = about;
            return this;
        }

        CustomUser build() {
            return new CustomUser(
                    session,
                    realm,
                    storageProviderModel,
                    username,
                    email,
                    firstName,
                    lastName,
                    birthDate,
                    phoneNumber,
                    isEmailVerified,
                    about);

        }
    }
}
