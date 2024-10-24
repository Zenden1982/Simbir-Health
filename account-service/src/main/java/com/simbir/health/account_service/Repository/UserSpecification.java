package com.simbir.health.account_service.Repository;

import org.springframework.data.jpa.domain.Specification;

import com.simbir.health.account_service.Class.User;

public class UserSpecification {

    public static Specification<User> firstName(String firstName) {
        return (root, query, criteriaBuilder) -> {
            if (firstName == null) {
                return null;
            }

            return criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");

        };
    }

    public static Specification<User> lastName(String lastName) {
        return (root, query, criteriaBuilder) -> {
            if (lastName == null) {
                return null;
            }

            return criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");

        };
    }
}
