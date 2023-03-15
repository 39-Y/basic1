package com.ll.basic1.Home.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ResponseBody
public class HomeController {
    int count=0;
    List<Person> persons = new ArrayList<>();
    @GetMapping("/home/main")
    public String showMain(){
        return "Hi dear!!";
    }

    @GetMapping("/home/main2")
    public String showMain2(){
        return "Googbye dear ~";
    }

    @GetMapping("/home/main3")
    public String showMain3(){
        return "Well.. I dont think so...";
    }

    @GetMapping("/home/increase")
    public int showIncrease(){
        return count++;
    }
    @GetMapping("/home/cookie/increase")
    public int showCookieIncrease(HttpServletRequest rq, HttpServletResponse rs) {
//        int c_count = -1;
//        if(rq.getCookies()!= null){
//            c_count = Arrays.stream(rq.getCookies())
//                    .filter(cookie -> cookie.getName().equals("count"))
//                    .map(cookie -> cookie.getValue())
//                    .mapToInt(Integer::parseInt)
//                    .findFirst()
//                    .orElse(0);
//        }
//        rs.addCookie(new Cookie("count",++c_count + ""));
        int c_count=0;
        if(rq.getCookies()!= null){
            Arrays.stream(rq.getCookies())
                    .filter(c->c.getName().equals("count"))
                    .map(c->c.getValue())
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }
        rs.addCookie(new Cookie("count", ++c_count+""));
        return c_count;
    }
    @GetMapping("/home/plus")
    public int showAdd(@RequestParam(defaultValue = "0", required = false) int a, @RequestParam(defaultValue = "0", required = false) int b){

        return a+b;
    }

    @GetMapping("/home/addPerson")
    public String AddPerson(@RequestParam String name, @RequestParam int age){
        persons.add(Person.builder().name(name).age(age).id(++Person.ID).build());
        return Person.ID+"번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/person")
    public String showPerson(){
        JSONArray jPersons = new JSONArray(persons);
        //return jPersons.toString();
        return persons.toString();
    }

    @GetMapping("/home/removePerson")
    public String removePerson(int id){
        boolean removed = persons.removeIf(person -> person.getId() == id);
        if(removed)
            return persons.toString();
        else
            return id+"번인 사람은 존재하지 않습니다.";
    }

    @GetMapping("/home/updatePerson")
    public String updatePerson(int id, String name, int age){
        Person person = persons.stream()
                .filter(p -> p.getId()==id)
                .findFirst()
                .orElse(null);
        if(person != null){
            person.setName(name);
            person.setAge(age);
            return person.toString();
        }
//        for(Person p: persons){
//            if(p.getId() == id){
//                p.setName(name);
//                p.setAge(age);
//                return p.toString();
//            }
//        }
        return id+"번인 사람은 존재하지 않습니다.";
    }


}
@Builder
@Getter

class Person{
    static int ID = 0;
    final int id;
    @Setter
    String name;
    @Setter
    int age;



    @Override
    public String toString() {
        JSONObject jperson = new JSONObject();
        try {
            jperson.put("id", id);
            jperson.put("name", name);
            jperson.put("age", age);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jperson.toString();

    }
}
