package org.example.models;

import lombok.*;
import org.example.enums.Gender;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private int age;
    private Gender gender;
    private String email;

    public Person(String name, int age, Gender gender, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }
}
