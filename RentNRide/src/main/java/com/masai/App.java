package com.masai;

import java.util.List;
import java.util.Scanner;

import com.masai.model.Car;
import com.masai.model.Customer;
import com.masai.model.Reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class App {
	
	private static final Scanner scanner = new Scanner(System.in);
	private static final String PERSISTENCE_UNIT_NAME = "rentnride"; 
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static Customer currentCustomer;
	
	
    public static void main( String[] args ){
    	
    	showMainMenu();
    }
    
    
    private static void showMainMenu() {
        int choice;

        do {
            System.out.println("Car Rental System");
            System.out.println("1. Administrator Login");
            System.out.println("2. Customer Registration");
            System.out.println("3. Customer Login");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    administratorLogin();
                    break;
                case 2:
                    customerRegistration();
                    break;
                case 3:
                    customerLogin();
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
    
    
    private static void administratorLogin() {
        System.out.print("Enter Administrator Username: ");
        String adminUsername = scanner.next();
        System.out.print("Enter Administrator Password: ");
        String adminPassword = scanner.next();

        if (ADMIN_USERNAME.equals(adminUsername) && ADMIN_PASSWORD.equals(adminPassword)) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            try {
                showAdministratorMenu(entityManager);

                transaction.commit();

            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                if (entityManager != null) {
                    entityManager.close();
                }
                entityManagerFactory.close();
            }
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }
    
    
    private static void showAdministratorMenu(EntityManager entityManager) {
        int choice;

        do {
            System.out.println("\nAdministrator Menu");
            System.out.println("1. Add Car");
            System.out.println("2. Update Car Availability");
            System.out.println("3. View Cars in Inventory");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCar(entityManager);
                    break;
                case 2:
                    updateCarAvailability(entityManager);
                    break;
                case 3:
                    viewCarsInInventory(entityManager);
                    break;
                case 4:
                    System.out.println("Logging out from the administrator account.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }
    
    
    private static void addCar(EntityManager entityManager) {
    	System.out.print("Enter Car Brand: ");
        String brand = scanner.next();
        System.out.print("Enter Car Model: ");
        String model = scanner.next();
        System.out.print("Enter Car Year: ");
        int year = scanner.nextInt();
        System.out.print("Enter Car Mileage: ");
        int mileage = scanner.nextInt();
        System.out.print("Is Car Available (true/false): ");
        boolean availability = scanner.nextBoolean();

        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setMileage(mileage);
        car.setAvailability(availability);

        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();

        try {
            entityManager.persist(car);
            System.out.println("Car added to the inventory!");

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    

    private static void updateCarAvailability(EntityManager entityManager) {
    	System.out.print("Enter Car ID to update availability: ");
        int carId = scanner.nextInt();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Car car = entityManager.find(Car.class, carId);

            if (car != null) {
                car.setAvailability(!car.isAvailability());
                System.out.println("Car availability updated!");
            } else {
                System.out.println("Car not found.");
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    
    private static void viewCarsInInventory(EntityManager entityManager) {
        List<Car> cars = entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();

        System.out.println("Cars in Inventory:");
        for (Car car : cars) {
            System.out.println(car);
            System.out.println();
        }
    }
    
    
    
    private static void customerRegistration() {
    	System.out.print("Enter Customer First Name: ");
        String customerFirstName = scanner.next();
        System.out.print("Enter Customer Last Name: ");
        String customerLastName = scanner.next();
        System.out.print("Enter Customer Username: ");
        String customerUsername = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Customer Password: ");
        String customerPassword = scanner.nextLine();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
//            Customer existingCustomer = entityManager.find(Customer.class, customerUsername);
//            if (existingCustomer == null) {
                Customer customer = new Customer(customerFirstName, customerLastName, customerUsername, customerPassword);
                entityManager.persist(customer);
                System.out.println("Customer registration successful!");
//            } 
//              else {
//                System.out.println("Username is already taken. Please choose another one.");
//            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            entityManagerFactory.close();
        }
    }

    private static void customerLogin() {
        System.out.print("Enter Customer Username: ");
        String customerUsername = scanner.next();
        System.out.print("Enter Customer Password: ");
        String customerPassword = scanner.next();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
//            currentCustomer = entityManager.find(Customer.class, customerUsername);
//            if (currentCustomer != null && currentCustomer.getPassword().equals(customerPassword)) {
//                showCustomerMenu(entityManager);
//            } else {
//                System.out.println("Invalid credentials. Please try again.");
//            }
        	
        	
        	Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.username = :username", Customer.class);
            query.setParameter("username", customerUsername);

            List<Customer> customers = query.getResultList();

            if (!customers.isEmpty()) {
                currentCustomer = customers.get(0);

                if (currentCustomer.getPassword().equals(customerPassword)) {
                    showCustomerMenu(entityManager);
                } else {
                    System.out.println("Invalid password. Please try again.");
                }
            } else {
                System.out.println("Customer not found. Please check your username.");
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            entityManagerFactory.close();
        }
    }

    private static void showCustomerMenu(EntityManager entityManager) {
        int choice;

        do {
            System.out.println("\nCustomer Menu");
            System.out.println("1. Search for Available Cars");
            System.out.println("2. View Car Details");
            System.out.println("3. Make Reservation");
            System.out.println("4. Manage Reservations");
            System.out.println("5. View Past and Current Reservations");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchForAvailableCars(entityManager);
                    break;
                case 2:
                    viewCarDetails(entityManager);
                    break;
                case 3:
                    makeReservation(entityManager);
                    break;
                case 4:
                    manageReservations(entityManager);
                    break;
                case 5:
                    viewPastAndCurrentReservations(entityManager);
                    break;
                case 6:
                    System.out.println("Logging out from the customer account.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    private static void searchForAvailableCars(EntityManager entityManager) {
        List<Car> availableCars = entityManager.createQuery("SELECT c FROM Car c WHERE c.availability = true", Car.class).getResultList();

        System.out.println("Available Cars:");
        for (Car car : availableCars) {
            System.out.println(car);
        }
    }

    private static void viewCarDetails(EntityManager entityManager) {
        System.out.print("Enter car ID to view details: ");
        int carId = scanner.nextInt();

        Car car = entityManager.find(Car.class, carId);

        if (car != null) {
            System.out.println("Car Details:");
            System.out.println(car);
        } else {
            System.out.println("Car not found.");
        }
    }

    private static void makeReservation(EntityManager entityManager) {
        System.out.print("Enter car ID to make a reservation: ");
        int carId = scanner.nextInt();

        Car selectedCar = entityManager.find(Car.class, carId);

        if (selectedCar != null && selectedCar.isAvailability()) {
            Reservation reservation = new Reservation();
            reservation.setCar(selectedCar);
            reservation.setCustomer(currentCustomer);
            entityManager.persist(reservation);

            selectedCar.setAvailability(false);

            System.out.println("Reservation successful!");
        } else {
            System.out.println("Invalid car ID or car not available for reservation.");
        }
    }

    private static void manageReservations(EntityManager entityManager) {
        int choice;
        do {
            System.out.println("\nManage Reservations");
            System.out.println("1. Cancel Reservation");
            System.out.println("2. View Reserved Cars");
            System.out.println("3. Back to Customer Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    cancelReservation(entityManager);
                    break;
                case 2:
                    viewReservedCars(entityManager);
                    break;
                case 3:
                    System.out.println("Returning to the customer menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);
    }

    private static void cancelReservation(EntityManager entityManager) {
        System.out.print("Enter reservation ID to cancel: ");
        int reservationId = scanner.nextInt();

        Reservation reservation = entityManager.find(Reservation.class, reservationId);

        if (reservation != null && reservation.getCustomer().equals(currentCustomer)) {
            Car reservedCar = reservation.getCar();
            reservedCar.setAvailability(true);

            entityManager.remove(reservation);

            System.out.println("Reservation canceled successfully.");
        } else {
            System.out.println("Invalid reservation ID or not authorized to cancel.");
        }
    }

    private static void viewReservedCars(EntityManager entityManager) {
        List<Reservation> reservations = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.customer = :customer", Reservation.class)
                .setParameter("customer", currentCustomer)
                .getResultList();

        System.out.println("Reserved Cars:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getCar());
        }
    }
    

    private static void viewPastAndCurrentReservations(EntityManager entityManager) {
        List<Reservation> reservations = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.customer = :customer", Reservation.class)
                .setParameter("customer", currentCustomer)
                .getResultList();

        System.out.println("Past and Current Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    
    
    
}
