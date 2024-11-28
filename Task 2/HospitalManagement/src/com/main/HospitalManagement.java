package com.main;

import java.util.List;
import java.util.Scanner;

import com.dao.AppointmentDAO;
import com.dao.BillingDAO;
import com.dao.InventoryDAO;
import com.dao.PatientDAO;
import com.dao.StaffDAO;
import com.models.Appointment;
import com.models.Billing;
import com.models.Inventory;
import com.models.Patient;
import com.models.Staff;
import com.services.AuthService;

public class HospitalManagement {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        loginFlow(scanner);
	    }
	 
	    private static void loginFlow(Scanner scanner) {
	        // This method will handle the login process
	        System.out.println("Welcome to the Hospital Management System!");
	        System.out.print("Username: ");
	        String username = scanner.next();
	        System.out.print("Password: ");
	        String password = scanner.next();

	        String role = AuthService.login(username, password);
	        if (role == null) {
	            System.out.println("Invalid credentials. Exiting...");
	            return;
	        }

	        System.out.println("Welcome, " + role + "!");
	        switch (role) {
	            case "Admin":
	                adminMenu(scanner);
	                break;
	            case "Doctor":
	            case "Nurse":
	            case "Staff":
	                staffMenu(scanner);
	                break;
	            case "Patient":
	                patientMenu(scanner);
	                break;
	        }
	    }

	    private static void adminMenu(Scanner scanner) {
	        while (true) {
	            System.out.println("\nAdmin Menu:");
	            System.out.println("1. Manage Patients");
	            System.out.println("2. Manage Staff");
	            System.out.println("3. Manage Inventory");
	            System.out.println("4. Manage Appointments");
	            System.out.println("5. Manage Billing");
	            System.out.println("6. Logout");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    // Call patient management methods
	                	managePatients(scanner);
	                    break;
	                case 2:
	                    // Call staff management methods
	                	manageStaff(scanner);
	                    break;
	                case 3:
	                    // Call inventory management methods
	                	manageInventory(scanner);
	                    break;
	                case 4:
	                    // Call appointment management methods
	                	manageAppointments(scanner);
	                    break;
	                case 5:
	                	// Call billing management methods
	                	manageBilling(scanner);
	                	break;
	                case 6:
	                    System.out.println("Admin Logging out...");
	                    System.out.println("Logged out!");
	                    // After logging out, go back to the login method
	                    loginFlow(scanner);
	                    return; // Return from the method, but loginFlow will call it again
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }

	    private static void staffMenu(Scanner scanner) {
	        // Staff-specific menu
	    	 while (true) {
		            System.out.println("\nStaff Menu:");
		            System.out.println("1. Manage Patients");
		            System.out.println("2. Manage Inventory");
		            System.out.println("3. Manage Appointments");
		            System.out.println("4. Manage Billing");
		            System.out.println("5. Logout");
		            System.out.print("Enter choice: ");
		            int choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    // Call patient management methods
		                	managePatients(scanner);
		                    break;
		                case 2:
		                    // Call inventory management methods
		                	manageInventory(scanner);
		                    break;
		                case 3:
		                    // Call appointment management methods
		                	manageAppointments(scanner);
		                    break;
		                case 4:
		                	// Call billing management methods
		                	manageBilling(scanner);
		                	break;
		                case 5:
		                    System.out.println("Staff member Logging out...");
		                    System.out.println("Logged out!");
		                    // After logging out, go back to the login method
		                    loginFlow(scanner);
		                    return; // Return from the method, but loginFlow will call it again
		                default:
		                    System.out.println("Invalid choice. Try again.");
		            }
		        }
	    }

	    private static void patientMenu(Scanner scanner) {
	        // Patient-specific menu
	    	
	    }
	    
	    private static void managePatients(Scanner scanner) {
	        PatientDAO patientDAO = new PatientDAO();
	        while (true) {
	            System.out.println("\nPatient Management:");
	            System.out.println("1. Register Patient");
	            System.out.println("2. View All Patients");
	            System.out.println("3. Update Patient Information");
	            System.out.println("4. Delete Patient");
	            System.out.println("5. Back");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    Patient patient = new Patient();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Name: ");
	                    patient.setName(scanner.nextLine());
	                    System.out.print("Enter Age: ");
	                    patient.setAge(scanner.nextInt());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Gender: ");
	                    patient.setGender(scanner.nextLine());
	                    System.out.print("Enter Contact: ");
	                    patient.setContact(scanner.nextLine());
	                    System.out.print("Enter Address: ");
	                    patient.setAddress(scanner.nextLine());
	                    patientDAO.addPatient(patient);
	                    System.out.println("Patient registered successfully!");
	                    break;

	                case 2:
	                    List<Patient> patients = patientDAO.getAllPatients();
	                    System.out.println("\nAll Patients:");
	                    for (Patient p : patients) {
	                        System.out.println("ID: " + p.getPatientID() +
	                                ", Name: " + p.getName() +
	                                ", Age: " + p.getAge() +
	                                ", Gender: " + p.getGender() +
	                                ", Contact: " + p.getContact() +
	                                ", Address: " + p.getAddress());
	                    }
	                    break;

	                case 3:
	                    System.out.print("Enter Patient ID to update: ");
	                    int patientID = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline
	                    Patient patientToUpdate = new Patient();
	                    patientToUpdate.setPatientID(patientID);
	                    System.out.print("Enter Updated Name: ");
	                    patientToUpdate.setName(scanner.nextLine());
	                    System.out.print("Enter Updated Age: ");
	                    patientToUpdate.setAge(scanner.nextInt());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Updated Gender: ");
	                    patientToUpdate.setGender(scanner.nextLine());
	                    System.out.print("Enter Updated Contact: ");
	                    patientToUpdate.setContact(scanner.nextLine());
	                    System.out.print("Enter Updated Address: ");
	                    patientToUpdate.setAddress(scanner.nextLine());
	                    patientDAO.updatePatient(patientToUpdate);
	                    System.out.println("Patient information updated successfully!");
	                    break;

	                case 4:
	                    System.out.print("Enter Patient ID to delete: ");
	                    int deleteID = scanner.nextInt();
	                    patientDAO.deletePatient(deleteID);
	                    System.out.println("Patient deleted successfully!");
	                    break;

	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }

	    
	    private static void manageStaff(Scanner scanner) {
	        StaffDAO staffDAO = new StaffDAO();
	        while (true) {
	            System.out.println("\nStaff Management:");
	            System.out.println("1. Add Staff");
	            System.out.println("2. View All Staff");
	            System.out.println("3. Update Staff");
	            System.out.println("4. Delete Staff");
	            System.out.println("5. Back");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    Staff staff = new Staff();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Name: ");
	                    staff.setName(scanner.nextLine());
	                    System.out.print("Enter Role: ");
	                    staff.setRole(scanner.nextLine());
	                    System.out.print("Enter Contact Number: ");
	                    staff.setContactNumber(scanner.nextLine());
	                    System.out.print("Enter Department: ");
	                    staff.setDepartment(scanner.nextLine());
	                    staffDAO.addStaff(staff);
	                    System.out.println("Staff added successfully!");
	                    break;

	                case 2:
	                    List<Staff> staffList = staffDAO.getAllStaff();
	                    System.out.println("\nStaff Details:");
	                    for (Staff s : staffList) {
	                        System.out.println("ID: " + s.getStaffID() + ", Name: " + s.getName() +
	                                ", Role: " + s.getRole() + ", Contact: " + s.getContactNumber() +
	                                ", Department: " + s.getDepartment());
	                    }
	                    break;

	                case 3:
	                    System.out.print("Enter Staff ID to update: ");
	                    int updateID = scanner.nextInt();
	                    Staff staffToUpdate = new Staff();
	                    staffToUpdate.setStaffID(updateID);
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Updated Name: ");
	                    staffToUpdate.setName(scanner.nextLine());
	                    System.out.print("Enter Updated Role: ");
	                    staffToUpdate.setRole(scanner.nextLine());
	                    System.out.print("Enter Updated Contact Number: ");
	                    staffToUpdate.setContactNumber(scanner.nextLine());
	                    System.out.print("Enter Updated Department: ");
	                    staffToUpdate.setDepartment(scanner.nextLine());
	                    staffDAO.updateStaff(staffToUpdate);
	                    System.out.println("Staff updated successfully!");
	                    break;

	                case 4:
	                    System.out.print("Enter Staff ID to delete: ");
	                    int deleteID = scanner.nextInt();
	                    staffDAO.deleteStaff(deleteID);
	                    System.out.println("Staff deleted successfully!");
	                    break;

	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }
	    
	    private static void manageInventory(Scanner scanner) {
	        InventoryDAO inventoryDAO = new InventoryDAO();
	        while (true) {
	            System.out.println("\nInventory Management:");
	            System.out.println("1. Add Item");
	            System.out.println("2. View All Items");
	            System.out.println("3. Update Item");
	            System.out.println("4. Delete Item");
	            System.out.println("5. Back");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    Inventory item = new Inventory();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Item Name: ");
	                    item.setItemName(scanner.nextLine());
	                    System.out.print("Enter Quantity: ");
	                    item.setQuantity(scanner.nextInt());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Supplier Name: ");
	                    item.setSupplier(scanner.nextLine());
	                    System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
	                    item.setExpiryDate(scanner.nextLine());
	                    inventoryDAO.addItem(item);
	                    System.out.println("Item added successfully!");
	                    break;

	                case 2:
	                    List<Inventory> items = inventoryDAO.getAllItems();
	                    System.out.println("\nInventory Items:");
	                    for (Inventory i : items) {
	                        System.out.println("ID: " + i.getItemID() + ", Name: " + i.getItemName() +
	                                ", Quantity: " + i.getQuantity() + ", Supplier: " + i.getSupplier() +
	                                ", Expiry Date: " + i.getExpiryDate());
	                    }
	                    break;

	                case 3:
	                    System.out.print("Enter Item ID to update: ");
	                    int updateID = scanner.nextInt();
	                    Inventory itemToUpdate = new Inventory();
	                    itemToUpdate.setItemID(updateID);
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Updated Name: ");
	                    itemToUpdate.setItemName(scanner.nextLine());
	                    System.out.print("Enter Updated Quantity: ");
	                    itemToUpdate.setQuantity(scanner.nextInt());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Updated Supplier Name: ");
	                    itemToUpdate.setSupplier(scanner.nextLine());
	                    System.out.print("Enter Updated Expiry Date (YYYY-MM-DD): ");
	                    itemToUpdate.setExpiryDate(scanner.nextLine());
	                    inventoryDAO.updateItem(itemToUpdate);
	                    System.out.println("Item updated successfully!");
	                    break;

	                case 4:
	                    System.out.print("Enter Item ID to delete: ");
	                    int deleteID = scanner.nextInt();
	                    inventoryDAO.deleteItem(deleteID);
	                    System.out.println("Item deleted successfully!");
	                    break;

	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }
	    
	    private static void manageAppointments(Scanner scanner) {
	        AppointmentDAO appointmentDAO = new AppointmentDAO();
	        while (true) {
	            System.out.println("\nAppointments Management:");
	            System.out.println("1. Schedule Appointment");
	            System.out.println("2. View All Appointments");
	            System.out.println("3. Update Appointment");
	            System.out.println("4. Cancel Appointment");
	            System.out.println("5. Back");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    Appointment appointment = new Appointment();
	                    System.out.print("Enter Patient ID: ");
	                    appointment.setPatientID(scanner.nextInt());
	                    System.out.print("Enter Doctor ID: ");
	                    appointment.setDoctorID(scanner.nextInt());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Date (YYYY-MM-DD): ");
	                    appointment.setDate(scanner.nextLine());
	                    System.out.print("Enter Time (HH:MM): ");
	                    appointment.setTime(scanner.nextLine());
	                    appointment.setStatus("Scheduled");
	                    appointmentDAO.addAppointment(appointment);
	                    System.out.println("Appointment scheduled successfully!");
	                    break;

	                case 2:
	                    List<Appointment> appointments = appointmentDAO.getAllAppointments();
	                    System.out.println("\nAppointments:");
	                    for (Appointment a : appointments) {
	                        System.out.println("ID: " + a.getAppointmentID() +
	                                ", PatientID: " + a.getPatientID() +
	                                ", DoctorID: " + a.getDoctorID() +
	                                ", Date: " + a.getDate() +
	                                ", Time: " + a.getTime() +
	                                ", Status: " + a.getStatus());
	                    }
	                    break;

	                case 3:
	                    System.out.print("Enter Appointment ID to update: ");
	                    int updateID = scanner.nextInt();
	                    Appointment appointmentToUpdate = new Appointment();
	                    appointmentToUpdate.setAppointmentID(updateID);
	                    System.out.print("Enter Updated Patient ID: ");
	                    appointmentToUpdate.setPatientID(scanner.nextInt());
	                    System.out.print("Enter Updated Doctor ID: ");
	                    appointmentToUpdate.setDoctorID(scanner.nextInt());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Updated Date (YYYY-MM-DD): ");
	                    appointmentToUpdate.setDate(scanner.nextLine());
	                    System.out.print("Enter Updated Time (HH:MM): ");
	                    appointmentToUpdate.setTime(scanner.nextLine());
	                    System.out.print("Enter Updated Status: ");
	                    appointmentToUpdate.setStatus(scanner.nextLine());
	                    appointmentDAO.updateAppointment(appointmentToUpdate);
	                    System.out.println("Appointment updated successfully!");
	                    break;

	                case 4:
	                    System.out.print("Enter Appointment ID to cancel: ");
	                    int cancelID = scanner.nextInt();
	                    appointmentDAO.deleteAppointment(cancelID);
	                    System.out.println("Appointment canceled successfully!");
	                    break;

	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }

	    private static void manageBilling(Scanner scanner) {
	        BillingDAO billingDAO = new BillingDAO();
	        while (true) {
	            System.out.println("\nBilling Management:");
	            System.out.println("1. Generate Bill");
	            System.out.println("2. View All Bills");
	            System.out.println("3. Update Bill Status");
	            System.out.println("4. Back");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    Billing bill = new Billing();
	                    System.out.print("Enter Patient ID: ");
	                    bill.setPatientID(scanner.nextInt());
	                    System.out.print("Enter Amount: ");
	                    bill.setAmount(scanner.nextDouble());
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Date (YYYY-MM-DD): ");
	                    bill.setDate(scanner.nextLine());
	                    bill.setStatus("Unpaid");
	                    billingDAO.addBill(bill);
	                    System.out.println("Bill generated successfully!");
	                    break;

	                case 2:
	                    List<Billing> bills = billingDAO.getAllBills();
	                    System.out.println("\nAll Bills:");
	                    for (Billing b : bills) {
	                        System.out.println("BillID: " + b.getBillID() +
	                                ", PatientID: " + b.getPatientID() +
	                                ", Amount: " + b.getAmount() +
	                                ", Date: " + b.getDate() +
	                                ", Status: " + b.getStatus());
	                    }
	                    break;

	                case 3:
	                    System.out.print("Enter Bill ID to update: ");
	                    int billID = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter New Status (Paid/Unpaid): ");
	                    String status = scanner.nextLine();
	                    billingDAO.updateBillStatus(billID, status);
	                    System.out.println("Bill status updated successfully!");
	                    break;

	                case 4:
	                    return;

	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }
	}