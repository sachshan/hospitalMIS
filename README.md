# Hospital Management Information System

* Created a micro-services-based hospital information management application to book appointments, view procedure status, access final reports, and allow physicians to cancel appointments and upload results
* Utilized Spring Boot for implementation and Hibernate ORM for data persistence, resulting in a user-friendly, efficient, and reliable solution for managing hospital operations


## Introduction
This is a hospital information management system. The primary purpose of this system is to manage appointments and user profiles. This project was created using the SpringBoot framework and utilizes Hibernate ORM to create persistent objects.

## Plain Old Java Objects (POJO)
In this application, I have created five POJOs: Patient, Doctor, Admin, Proc, and Apt. All of the POJOs were mapped using annotations. The Proc POJO, which represents the Procedures, contains a list of Doctor objects with a Many To Many relationships. This list represents all the Doctors that perform the specific procedure. The Apt POJO, which represents the appointments, has a Doctor, Procedure, and Patient property. Each of those properties is mapped with a Many To One relationship.

## Data Access Objects
I created a DAO class with basic methods to create, commit, and close hibernate sessions. Each POJO has a Data Access Object: AdminDAO, AptDAO, PatientDAO, DoctorDAO, and ProcDAO. Each POJO-specific DAO object inherits the DAO class and has methods specific to their POJO. For instance, the PatientDAO has methods: createPatient(Patient patient) and getPatients(), and many others.

## Roles and session management
This application has three roles: Patient, Doctor, and Administrator. For each of the roles, there is a login page. The patients can create their profiles and then log in. For Doctors, the administrator can create a Doctor profile and give the Doctor the credentials to log in. The admin user ID and password are directly added to the Admin table in the Hospital Database using a SQL INSERT statement. All three roles have interceptors: PatientInterceptor, DoctorInterceptor, and AdminInterceptor. Each interceptor prevents users from accessing pages for which the users do not have authorization. The interceptor checks the username and password. The user is redirected to their respective login page if the login details are not verified. For instance, if a user tries to access HospitalMIS/admin/createDoctor.htm, a page the administrator uses to create new doctor profiles, he is redirected to HospitalMIS/admin/login.htm, which is the admin login page.
 
 
## Patient
##### Create Patient
For the patient role, the user can log in as a patient. If the user does not have a patient profile, he can click the "Create Patient Account!" link to create his profile. The profile will require basic details about the patient, such as age, gender, and email. While making the profile, the patient can choose a username and a password. The username and password fields must not be empty.
The username will be checked against the saved usernames in the patient table to check if it is unique, and the application will not save the form until the user provides a unique username. The application checks the validity of other fields using the Validator class object. When the patient submits his username and password, the username and password are verified by checking the database. After the user is confirmed, he is taken to the patient's homepage.
##### Patient HomePage
The patient can view the upcoming appointment and its details: Procedure, Doctor, Date, Time, Duration, and cancel button. When clicking the cancel button, the application will delete the appointment from the Apt table in the database. When the appointment is deleted, the application will not refresh the page as the command to delete the appointment will be sent using an Ajax Post request created using JQuery. The patient can view past appointments and their details: Procedure, Doctor, Date, Time, Report, and PDF. The report of the appointment is made available when the Doctor uploads it. In addition, the patient can download a PDF document with all the appointment details when the Doctor has uploaded the report.
 
##### Patient Book Appointment
First, the patient chooses a procedure. Based on the choice of procedure will lead to a list of doctors that perform the specific procedure and a calendar to choose the date of appointment is created on the same page, using jQuery. The calendar dates will only show dates from the current day to the next 31 days. The list of doctors is created by sending an Ajax Post Query, which returns the name of doctors by checking the database for the doctors performing the procedure. When the patient chooses the appointment date, a list of available times is generated and displayed. The list of times is created based on the procedure's duration and the hospital's working hours. The list of times is created by sending an Ajax Post Query, which first queries the Doctor's appointments and the checks for available time slots. The available time slots are returned for the specific date, procedure, and Doctor. When the list is created, the application will not refresh the page. The application will create a new record in the Apt Table when the patient submits a form.

 
## Doctor
The Doctor can view all of his appointments. The appointments are categorized into Today's, Upcoming, Completed(Report Pending), and Past. In Today's appointment, all appointments on the current date are shown. In Upcoming appointment, all of the appointments from tomorrow onwards are shown. In both Today and Upcoming, the Doctor can cancel the appointment.

In Today's appointment, the Doctor can set the appointment to complete or cancel the appointment. When the Doctor clicks the 'Complete' button the appointment is shifted to the Completed(Report Pending) table. When the Doctor clicks the 'Completed' button, an Ajax Post Query, which sends a request with the appointment id. The appointment status is changed to 'C' for completed in the Apt table in the Hospital database. If the 'Begin Time' of the appointment has passed, the appointment will be automatically shifted from the Today to Completed(Report Pending) table.

The Completed(Report pending) table contains a text box for the Doctor to write his report. The Doctor can submit his report using the 'Submit Report' button. When the Doctor clicks the submit report button, an Ajax Post Query is created with the report text. The text is saved in the Apt Result column. Afterward, the appointment is shifted from the Completed(Report Pending) to the past appointments table with the report. During the whole process, the page is not refreshed.

 
## Administrator
The Administrator can log in with his 'adminID' and password. After logging in, the administrator is redirected to the Admin Home Page.
The Administrator can create a new procedure. When the Administrator clicks on the 'Create new Procedure' button, he is redirected to a new page. On the page, the Administrator can fill in the procedure details: Name, Duration(min), Cost, and Doctors. The Administrator can choose multiple doctors per procedure. The 'Create new procedure' form entries will be validated using a validator class object ProcValidator. When the Administrator submits the form, the entries are saved in the Proc table in the Hospital Database. The Proc POJO has a list of Doctor objects with Many To Many relationships. When the form is saved, the Doctors will be saved by creating a new entry in the Proc-Doctor table.
The Administrator can create a new Doctor. When the Administrator clicks on the 'Enter new Doctor' button, he is redirected to a new page. On the page, the Administrator can fill in the Doctor's details: First Name, Last Name, Specialty, User, and Password. The 'Enter new Doctor' form entries will be validated using a validator class object DoctorValidator. When the form is submitted, the entries are saved in the Doctor table in the Hospital Database.

In addition, the Administrator can view all the Appointments, Procedures, Patients, and Doctors. In addition, the Administrator can delete any of the entries in the Appointments, Procedures, Patients, and Doctors tables. While deleting a Procedure, Doctor, or Patient, the application will also delete all appointments of that Procedure, Doctor, or Patient.
