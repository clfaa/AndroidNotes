package com.clfaa.androidnotes.lambda;


import java.util.List;

/**
 * Created by changlifei on 16/4/27.
 */
public class Person {
    public enum Sex {
        MALE,FEMALE
    }

    String name;
    Sex gender;
    String emailAddress;
    int age;



    public void printPerson() {
        System.out.println("name: " + name +" gender: " + gender.name() + " age: " + age);
    }


    public static void printPersons(List<Person> roster,CheckPerson tester){
        for (Person p : roster){
            if (tester.test(p)){
                p.printPerson();
            }
        }
    }

    interface  CheckPerson{
        boolean test(Person p);
    }


}
