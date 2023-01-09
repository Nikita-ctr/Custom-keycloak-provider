package com.keycloak.custom;

public final class CustomUserStorageProviderConstants {
    public static final String CONFIG_KEY_JDBC_DRIVER = "org.postgresql.Driver";
    public static final String CONFIG_KEY_JDBC_URL = "jdbc:postgresql://host.docker.internal:5432/hitcher";
    public static final String CONFIG_KEY_DB_USERNAME = "postgres";
    public static final String CONFIG_KEY_DB_PASSWORD = "password";
    public static final String CONFIG_KEY_VALIDATION_QUERY = "SELECT 1";
}