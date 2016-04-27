package com.clfaa.androidnotes.lambda;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by changlifei on 16/4/27.
 */
public class PersonTest {

    @Test
    public void testPrintPerson() throws Exception {

    }

    @Test
    public void testPrintPersons() throws Exception {
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
        //不知为何，在这儿使用lambda，编译错误，但testTest()方法则没有问题
        Person.printPersons(list,p -> p.age > 18 && p.age < 25);
    }

    @Test
    public void testTest(){
        Person.test();
    }
}