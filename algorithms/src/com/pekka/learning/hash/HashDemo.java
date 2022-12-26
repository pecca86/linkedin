package com.pekka.learning.hash;

import java.util.HashMap;
import java.util.HashSet;

public class HashDemo {

    public static void main(String[] args) {
        Employee emp1 = new Employee("Pekka", 1232, "Tech");
        Employee emp2 = new Employee("Claus", 12212, "Sales");
        Employee emp3 = new Employee("Fjalle", 1231232, "Marketing");
        Employee emp4 = new Employee("Olle", 231232, "Customer care");
        Employee emp5 = new Employee("Månsa", 551232, "Tech");

        // Create key-val datastructure of employees
        HashMap<Integer, Employee> employeeHashMap = new HashMap<>();
        employeeHashMap.put(emp1.id(), emp1);
        employeeHashMap.put(emp2.id(), emp2);
        employeeHashMap.put(emp3.id(), emp3);
        employeeHashMap.put(emp4.id(), emp4);
        employeeHashMap.put(emp5.id(), emp5);

        System.out.println(employeeHashMap.get(1232));
        System.out.println(employeeHashMap.containsKey(1030));

        // If each item in hashlist is unique HashSet is optimal
        HashSet<String> productCodes = new HashSet<>();
        productCodes.add("WEAOK");
        productCodes.add("09dD");
        productCodes.add("DSAD11");

        // Check if value exists
        System.out.println(productCodes.contains("WEAOK"));

        HashMap<Integer, Integer> homo = new HashMap<>();
        homo.put(1,1);
        int i = homo.get(1);
        homo.replace(1, i+1);
        System.out.println(homo);
    }
}
