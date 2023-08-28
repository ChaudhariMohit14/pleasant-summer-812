package com.masai.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.masai.Entity.Car;
import com.masai.Entity.Customer;

public class Main {

    public static void main(String[] args) {
        AdministratorService adminService = new AdministratorService();
        CustomerService customerService = new CustomerService();
        Scanner sc = new Scanner(System.in);

        try {
            // Administrator work
            System.out.print("Enter brand of the car: ");
            String brand = sc.nextLine();

            System.out.print("Enter model of the car: ");
            String model = sc.nextLine();

            System.out.print("Enter year of the car: ");
            int year = sc.nextInt();

            System.out.print("Enter mileage of the car: ");
            int mileage = sc.nextInt();

            Car car = new Car(brand, model, year, mileage, true, false);
            adminService.addCar(car);
            System.out.println("Car added successfully!");

            // Customer work
            System.out.print("Enter car ID to reserve: ");
            Long carId = sc.nextLong();

            Car availableCar = customerService.getCarById(carId);

            System.out.print("Enter reservation start date and time (yyyy-MM-dd HH:mm): ");
            String startTimeStr = sc.nextLine();
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Enter reservation end date and time (yyyy-MM-dd HH:mm): ");
            String endTimeStr = sc.nextLine();
            LocalDateTime endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            Customer customer = new Customer();
            customerService.createReservation(customer, availableCar, startTime, endTime);
            System.out.println("Reservation created successfully!");

        } finally {
            adminService.close();
            customerService.close();
            sc.close();
        }
    }
}
