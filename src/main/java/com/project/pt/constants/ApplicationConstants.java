package com.project.pt.constants;

import java.util.Map;
import java.util.Set;

public class ApplicationConstants {

    public final static Set<String> PERMITTED_ENDPOINTS = Set.of("/login", "/signup");

    public static final String ALL_COUNTIRES_ENDPOINT = "https://restcountries.com/v3.1/all";
    public static final String ALL_USERS_ENDPOINT =  "https://fakerapi.it/api/v1/persons";

    public static final Map<String, Integer> userValidationPriority = Map.ofEntries(
            Map.entry("name", 1),
            Map.entry("surname", 2),
            Map.entry("username", 3),
            Map.entry("password", 4),
            Map.entry("birthyear", 5),
            Map.entry("gender", 6),
            Map.entry("address.country", 7),
            Map.entry("address.city", 8),
            Map.entry("address.neighbourhood", 9),
            Map.entry("address.street", 10),
            Map.entry("address.no", 11),
            Map.entry("address.phoneNumber", 12),
            Map.entry("confirmPassword", 99)
            );

    public static final Map<String, Integer> addressValidationPriority = Map.ofEntries(
            Map.entry("country", 1),
            Map.entry("city", 2),
            Map.entry("neighbourhood", 3),
            Map.entry("street", 4),
            Map.entry("no", 5),
            Map.entry("phoneNumber", 6)
    );

}
