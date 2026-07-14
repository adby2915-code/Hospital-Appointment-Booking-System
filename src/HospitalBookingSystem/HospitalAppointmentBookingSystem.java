package HospitalBookingSystem;

import java.util.HashMap;

import HospitalBookingSystem.Appointment.AppointmentStatus;

class Patient {
	 int patientId;
	 String patientName;
	 int age;
	 String phoneNumber;
	 
	 Patient(int patientId,String patientName,int age,String phoneNumber){
		 this.patientId=patientId;
		 this.patientName=patientName;
		 this.age=age;
		 this.phoneNumber= phoneNumber;
	 }
	 
	 @Override
	 public String toString() {
		return "\n Patient Id : " + patientId +
			     "\n PatientName : " + patientName +
			     "\n Patient age : " + age +
			     "\n Patient PhoneNumber : " + phoneNumber ; 
	 }
 }
 class Doctor{
	 int doctorId ;
	 String doctorName;
	 String specialization;
	 double consultationFee;
	 
	 Doctor(int doctorId,String doctorName,String specialization,double consultationFee){
		 this.doctorId=doctorId;
		 this.doctorName=doctorName;
		 this.specialization=specialization;
		 this.consultationFee=consultationFee;
	 }
	 
	 @Override
	 public String toString() {
		 return "\nDoctor ID : " + doctorId +
				 "\nDoctor Name : " + doctorName +
				 "\nSpecialization : " + specialization +
				 "\nDoctor ConsultationFee : " + consultationFee ; 
	 }
 }
 
 
 class Appointment {
	 int appointmentId;
	 Patient patient;
	 Doctor doctor;
	 String appointmentDate;
	 String appointmentTime;
	 
	 enum AppointmentStatus{
		 BOOKED,COMPLETED,CANCELLED
	 }
	 AppointmentStatus status;
	 
	 Appointment(int appointmentId,Patient patient,Doctor doctor,String appointmentDate,String appointmentTime,AppointmentStatus status){
		 this.appointmentId = appointmentId ;
		 this.patient = patient;
		 this.doctor = doctor;
		 this.appointmentDate = appointmentDate;
		 this.appointmentTime = appointmentTime;
		 this.status = status;
	 }
	 
	 @Override
	 public String toString() {
		return "Appointment ID : " + appointmentId + 
			   "\nAppointment of Patient " + patient +
			   "\nAppointment of the Doctor  " + doctor +
			    "\nAppointment Date : " + appointmentDate +
		       	"\nAppointment Time : " + appointmentTime +
				"\nAppointment Status : " + status + "\n";
		 
	 }
 }
 class Management{
	 HashMap<Integer,Appointment>appointments = new HashMap<>();
	 //CRUDD
	 void addAppointment(Appointment a) {
		 if(appointments.containsKey(a.appointmentId)) {
			 System.out.println("Appointment ID is already Exists.");
			 return;
		 } appointments.put(a.appointmentId, a);
		 System.out.println("Successfully Booked:\n" + a);
		 System.out.println();
	 }

	 void searchAppointment(int appointmentId) {
		 if(appointments.containsKey(appointmentId)) {
			 Appointment A = appointments.get(appointmentId);
			 System.out.println("---ID Found Successfully---");
			 System.out.println(A);
		 }else {
			 System.out.println("---ID Not Found---");
		 } 
	 }

	 void removeAppointment(int appointmentId) {
		if(appointments.containsKey(appointmentId)) {
			Appointment Removed = appointments.remove(appointmentId);
			System.out.println("---ID Removed Successfully---");
			System.out.println(Removed);
		}else {
			System.out.println("---ID Not Found---");
		} 
	 }

	 void displayAllAppointments() {
		 System.out.println("---All Appointments---");
		 for(Appointment a : appointments.values()) {
			 System.out.println(a);
		 }
	 }

	 public void updateAppointmentStatus(int appointmentId,AppointmentStatus newstatus){ 
		System.out.println("---Appointment Status Updated---");
		Appointment a = appointments.get(appointmentId);
			if(a != null) {
				a.status = newstatus;
				System.out.println(a);
			}
	 }

	 void rescheduleAppointment(int appointmentId,String newappointmentDate,String newappointmentTime){
		 
		 Appointment a = appointments.get(appointmentId);
		 if(a != null) {
			 a.appointmentDate = newappointmentDate;
			 a.appointmentTime = newappointmentTime;
			 System.out.println("---Appointment Reschedule---");
			 System.out.println(a);
		 }else {
			 System.out.println("Appointment ID Not Found");
		 }
	 }
	 //Business Logic
	// 1. Display appointments by Doctor
	 void displayAppointmentsByDoctor(int doctorId) {
		 System.out.println("---Appointment For Doctor ID : " + doctorId + "---");
		 boolean found = false;
		 for(Appointment a : appointments.values()) {
			 if(a.doctor != null && a.doctor.doctorId == doctorId) { 
				System.out.println(a);
				found = true;
			 }
		 }if(!found)System.out.println("No Appointments found for this Doctor.");
	 }
	 //2.Find appointments by Patient
	 void displayAppointmentsByPatient(int patientId) {
		 System.out.println("---Appointment For Patient Id : " + patientId + "---");
		 boolean found = false;
		 for(Appointment a : appointments.values()) {
			 if(a.patient != null && a.patient.patientId == patientId) {
				 System.out.println(a);
				 found = true;
			 }
		 }if(!found)System.out.println("No Appointments found for this Patient.");
	 }
	 //3.Count appointments for each doctor
	 void doctorAppointmentCount(){
		 System.out.println("--- Doctor Appointment Counts ---");
		 HashMap< Integer, Integer > counts = new HashMap<>();
		 for(Appointment a : appointments.values()) {
			 if(a.doctor != null) {
				 int docID = a.doctor.doctorId;
				 counts.put(docID, counts.getOrDefault(docID, 0)+1);
			 }
		 }
		 for(Integer name : counts.keySet()) {
			 System.out.println(name + " : " + counts.get(name) + " appointments");
		 }
	 }
	 //4.Count completed appointments
	 void completedAppointmentCount() {
		 int count = 0;
		 for(Appointment a : appointments.values()) {
			 if(a.status == AppointmentStatus.COMPLETED) {
				count++; 
			 }
		 }
		 System.out.println("Total Completed Appointment : " + count);
	 }
	 //5.Count cancelled appointments
	 void cancelledAppointmentCount() {
		 int count = 0;
		 for(Appointment a : appointments.values()) {
			 if(a.status == AppointmentStatus.CANCELLED){
				 count++;
			 }
		 }
		 System.out.println("Total Cancelled Appointment : " + count);
	 }
	 //6.Find doctor earning
	 void doctorEarning() {
		 System.out.println(" --- Doctor Total Earnings ---");
		 HashMap< Integer, Double > earnings = new HashMap<>();
		 for(Appointment a : appointments.values()) {
			 //Earnings are only calculated on 'Completed' bookings.
			 if(a.doctor != null && a.status == AppointmentStatus.COMPLETED) {
				 int docID = a.doctor.doctorId;
				 earnings.put(docID, earnings.getOrDefault(docID, 0.0) + a.doctor.consultationFee);
			 }
		 }
		 for(int name : earnings.keySet()) {
			 System.out.println(name + "Earnings : " + earnings.get(name));
		 }
	 }
	 //7.Find doctor with maximum appointments
	 void doctorWithMaximumAppointments() {
		 if(appointments.isEmpty()) {
			 System.out.println("---No Appointments Available---");
			 return;
		 }
		 HashMap< Integer, Integer > doctorcount = new HashMap<>();
		 for(Appointment a : appointments.values()) {
			 if (a.doctor != null) {
			 int doctorId = a.doctor.doctorId;
			 doctorcount.put(doctorId, doctorcount.getOrDefault(doctorId, 0) + 1);
			 }
		 }
		 
		 int maxDoctorId = -1;
		 int maxCount = 0;
		 
		 for(Integer doctorId : doctorcount.keySet()) {
			 int count = doctorcount.get(doctorId);
			 
			 if(count > maxCount ){
				maxCount = count;
				maxDoctorId = doctorId;
			 }
		 }
		 System.out.println("--Doctor with Maxx Appointments---");
		 for(Appointment a : appointments.values()) {
			 if(a.doctor != null && a.doctor.doctorId == maxDoctorId) {
				 System.out.println(a.doctor);
				 System.out.println("Appointment : " + maxCount);
				 break;
			 }
		 }
	 }
	 
	 //8.Display today's appointments
	 void displayTodaysAppointments(String todayDate) {
		 System.out.println("--- Appointments For date : " + todayDate + "---");
		 boolean found = false;
		 for(Appointment a : appointments.values()) {
			 if(a.appointmentDate != null && a.appointmentDate.equals(todayDate)) {
				 System.out.println(a);
				 found=true;
			 }
		 }
		 if(!found)System.out.println("No Appointment Scheduledd for today.");
	 }
 }
public class HospitalAppointmentBookingSystem {

	public static void main(String[] args) {
		
		Patient p1 = new Patient(101,"Adhi",22,"9876543210");
		Patient p2 = new Patient(102,"Kumar",35,"9876543211");
		Patient p3 = new Patient(103,"Ravi",40,"9876543212");
		Patient p4 = new Patient(104,"Priya",27,"9876543213");
		Patient p5 = new Patient(105,"Bala",31,"9876543214");
		
		Doctor d1 = new Doctor(201,"Dr Kumar","Cardiology",800);
		Doctor d2 = new Doctor(202,"Dr Priya","Neurology",1200);
		Doctor d3 = new Doctor(203,"Dr Arun","Orthopedics",600);
		Doctor d4 = new Doctor(204,"Dr Meena","Pediatrics",700);
		Doctor d5 = new Doctor(205,"Dr Ravi","Dermatology",900);
		
		Appointment a1 = new Appointment(1001,p1,d1,"2026-07-10","09:00 AM",AppointmentStatus.BOOKED);
		Appointment a2 = new Appointment(1002,p2,d2,"2026-07-10","10:30 AM",AppointmentStatus.COMPLETED);
		Appointment a3 = new Appointment(1003,p3,d1,"2026-07-10","11:00 AM",AppointmentStatus.CANCELLED);
		Appointment a4 = new Appointment(1004,p4,d3,"2026-07-10","02:00 PM",AppointmentStatus.COMPLETED);
		Appointment a5 = new Appointment(1005,p4,d1,"2026-07-10","03:30 PM",AppointmentStatus.BOOKED);
		
		Management management = new Management()	;
		
		management.addAppointment(a1);
		management.addAppointment(a2);
		management.addAppointment(a3);
		management.addAppointment(a4);
		management.addAppointment(a5);
		
		management.searchAppointment(1003);
		  
		management.displayAllAppointments();
		
		management.updateAppointmentStatus(1005,AppointmentStatus.CANCELLED);
		management.rescheduleAppointment(1003,"2026-07-11","09:00 AM");
		
		management.displayAppointmentsByDoctor(201);
		
		management.displayAppointmentsByPatient(102);
		
		management.doctorAppointmentCount();

		management.completedAppointmentCount();

		management.cancelledAppointmentCount();

		management.doctorEarning();

		management.doctorWithMaximumAppointments();

		management.displayTodaysAppointments("2026-07-10");
	}

}
