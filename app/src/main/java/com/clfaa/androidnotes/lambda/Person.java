package com.clfaa.androidnotes.lambda;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by changlifei on 16/4/27.
 */
public class Person {
    public enum Sex {
        MALE,FEMALE
    }

    String name;
    Sex gender;
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

    public static void test(){
        List<Person> list = new ArrayList<>();
        Random ra =new Random();
        for (int i = 0; i < 100;i++){
            Person person = new Person();
//            person.age = (int) (Math.random() * 100);
            person.age = ra.nextInt(100);

            if (i % 2 == 0){
                person.gender = Person.Sex.FEMALE;
            }else {
                person.gender = Person.Sex.MALE;
            }
            person.name = "LambdaName_"+ i;

            list.add(person);
        }
        Person.printPersons(list,p -> p.age > 18 && p.age < 25);
    }



}
