package com.keycloak.custom;

import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.adapter.AbstractUserAdapter;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CustomUser extends AbstractUserAdapter {

    private final String username;
    private final String email;
    private final String phone;

    private final boolean verified;

    private final boolean accepted;

    private Date registrationDate;

    private final String about;

    public CustomUser(KeycloakSession session,
                      RealmModel realm,
                      ComponentModel storageProviderModel,
                      String username,
                      String email,
                      String phone,
                      boolean verified,
                      boolean accepted,
                      Date registrationDate,
                      String about) {
        super(session, realm, storageProviderModel);
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.verified = verified;
        this.accepted = accepted;
        this.registrationDate = registrationDate;
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

    public String getPhone() {
        return phone;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public Map<String, List<String>> getAttributes() {
        MultivaluedHashMap<String, String> attributes = new MultivaluedHashMap<>();
        attributes.add(UserModel.USERNAME, getUsername());
        attributes.add(UserModel.EMAIL, getEmail());
        attributes.add(("verified"), String.valueOf(isVerified()));
        attributes.add("accepted",String.valueOf(isAccepted()));
        attributes.add("registrationDate", String.valueOf(getRegistrationDate()));
        attributes.add("about", getAbout());

        return attributes;
    }

    static class Builder {
        private final KeycloakSession session;
        private final RealmModel realm;
        private final ComponentModel storageProviderModel;
        private final String username;
        private String email;
        private boolean verified;
        private boolean accepted;
        private String phone;

        private Date registrationDate;

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


        CustomUser.Builder isVerified(boolean isVerified) {
            this.verified = isVerified;
            return this;
        }

        CustomUser.Builder accepted(boolean accepted) {
            this.accepted = accepted;
            return this;
        }

        CustomUser.Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        CustomUser.Builder registrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
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
                    phone,
                    verified,
                    accepted,
                    registrationDate,
                    about);

        }
    }
}
