package com.example.timeTable;

import com.example.timeTable.controller.AdminController;
import com.example.timeTable.controller.AuthenticationController;
import com.example.timeTable.model.entities.Role;
import com.example.timeTable.model.entities.TimeTable;
import com.example.timeTable.model.requestModel.*;
import com.example.timeTable.repository.TimeTableRepository;
import com.example.timeTable.service.TimeTableService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TimeTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeTableApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(TimeTableRepository timeTableRepository, AuthenticationController authenticationController, AdminController adminController, TimeTableService timeTableService){
//		return args -> {
//			addProfessors(authenticationController);
//			addCourses(adminController);
//			addTimeTable(timeTableService);
//			addNullPeriods(timeTableService, timeTableRepository);
//		};
//	}

//	void addProfessors(AuthenticationController authenticationController) {
//		authenticationController.registerProfessor(
//				new RegisterProfessorRequest(
//						"Indu@gmail.com", "Indu", Role.Professor, "Dr. Indu Saini", "ECE", "7814803200"
//				)
//		);
//		authenticationController.registerProfessor(
//				new RegisterProfessorRequest(
//						"Deepti@gmail.com", "Deepti", Role.Professor, "Dr. Deepti Kakkar", "ECE", "7814803201"
//				)
//		);
//		authenticationController.registerProfessor(
//				new RegisterProfessorRequest(
//						"Mamta@gmail.com", "Mamta", Role.Professor, "Dr. Mamta Khosla", "ECE", "9426735412"
//				)
//		);
//		authenticationController.registerProfessor(
//				new RegisterProfessorRequest(
//						"Neetu@gmail.com", "Neetu", Role.Professor, "Dr. Neetu Sood", "ECE", "9426735412"
//				)
//		);
//		authenticationController.registerProfessor(
//				new RegisterProfessorRequest(
//						"Barjinder@gmail.com", "Barjinder", Role.Professor, "Dr. B. S. Saini", "ECE", "9426735412"
//				)
//		);
//		authenticationController.registerProfessor(
//				new RegisterProfessorRequest(
//						"Ramesh@gmail.com", "Ramesh", Role.Professor, "Dr. R. K. Sunkaria", "ECE", "9426735412"
//				)
//		);
//
//		authenticationController.registerAdmin(
//				new RegisterAdminRequest(
//						"Yash@gmail.com","vijay",Role.Admin,"Yash Vijayvargiya"
//				)
//		);
//		authenticationController.registerStudent(
//				new RegisterStudentRequest(
//						"Student@gmail.com","vijay",Role.Student,"Student", 19104118L,"ECE_4"
//				)
//		);
//	}
//
//	void addCourses(AdminController adminController){
//		adminController.createCourse(
//				new CourseRequest(
//						"Digital Signal Processing", "ECPC-306", "ECE", "Remesh@gmail.com",	"DSP"
//				)
//		);
//		adminController.createCourse(
//				new CourseRequest(
//						"Digital Communication System", "ECPC-304", "ECE", "Neetu@gmail.com","DCS"
//				)
//		);adminController.createCourse(
//				new CourseRequest(
//						"Digital System Design", "ECPC-302", "ECE", "Mamta@gmail.com", "DSD"
//				)
//		);
//		adminController.createCourse(
//				new CourseRequest(
//						"Advanced Microprocessor and Microcontroller", "ECPC-308", "ECE", "Barjinder@gmail.com", "AMMC"
//				)
//		);
//		adminController.createCourse(
//				new CourseRequest(
//						"Digital Communication System Lab", "ECPC-324", "ECE", "Deepti@gmail.com", "DCS Lab"
//				)
//		);
//		adminController.createCourse(
//				new CourseRequest(
//						"Digital Signal Processing Lab", "ECPC-326", "ECE", "Deepti@gmail.com", "DSP Lab"
//				)
//		);
//		adminController.createCourse(
//				new CourseRequest(
//						"Advanced Microprocessor and Microcontroller Lab", "ECPC-328", "ECE", "Barjinder@gmail.com", "AMMC Lab"
//				)
//		);
//	}
//
//	void addTimeTable(TimeTableService timeTableService){
//		List<TimeTableRequest> list = new ArrayList<>();
//		list.add(new TimeTableRequest(1, "Mon", "ECPC-328",  "lab", "G1","LT-101"));
////		list.add(new TimeTableRequest(2, "Mon", "ECPC-***",  "tut", "G2","LT-102"));
//		list.add(new TimeTableRequest(3, "Mon", "ECPC-304",  "class", "All","LT-103"));
//		list.add(new TimeTableRequest(4, "Mon", "ECPC-302",  "class", "All","LT-103"));
//		list.add(new TimeTableRequest(5, "Mon", "ECPC-326",  "lab", "G3","LT-101"));
////		list.add(new TimeTableRequest(6, "Mon", "ECPC-***",  "tut", "G2","LT-102"));
//		list.add(new TimeTableRequest(7, "Mon", "ECPC-324",  "lab", "G2","LT-102"));
//
////		list.add(new TimeTableRequest(1, "Tue", "ECPC-328",  "lab", "G1","LT-101"));
////		list.add(new TimeTableRequest(2, "Tue", "ECPC-***",  "tut", "G2","LT-102"));
//		list.add(new TimeTableRequest(3, "Tue", "ECPC-302",  "class", "All","LT-103"));
//		list.add(new TimeTableRequest(4, "Tue", "ECPC-304",  "class", "All","LT-102"));
//		list.add(new TimeTableRequest(5, "Tue", "ECPC-326",  "lab", "G2","LT-101"));
////		list.add(new TimeTableRequest(6, "Tue", "ECPC-***",  "tut", "G2","LT-102"));
//		list.add(new TimeTableRequest(7, "Tue", "ECPC-324",  "lab", "G1","LT-102"));
//
//		list.add(new TimeTableRequest(1, "Wed", "ECPC-306",  "class", "All","LT-101"));
////		list.add(new TimeTableRequest(2, "Wed", "ECPC-***",  "tut", "G2","LT-102"));
////		list.add(new TimeTableRequest(3, "Wed", "ECPC-302",  "class", "All","LT-103"));
//		list.add(new TimeTableRequest(4, "Wed", "ECPC-308",  "class", "All","LT-102"));
////		list.add(new TimeTableRequest(5, "Wed", "ECPC-326",  "lab", "G2","LT-101"));
////		list.add(new TimeTableRequest(6, "Wed", "ECPC-***",  "tut", "G2","LT-102"));
//		list.add(new TimeTableRequest(7, "Wed", "ECPC-328",  "lab", "G1","LT-102"));
//
//		list.add(new TimeTableRequest(1, "Thur", "ECPC-308",  "class", "All","LT-101"));
//		list.add(new TimeTableRequest(2, "Thur", "ECPC-306",  "class", "All","LT-102"));
////		list.add(new TimeTableRequest(3, "Thur", "ECPC-302",  "class", "All","LT-103"));
////		list.add(new TimeTableRequest(4, "Thur", "ECPC-308",  "class", "All","LT-102"));
//		list.add(new TimeTableRequest(5, "Thur", "ECPC-328",  "lab", "G2","LT-101"));
////		list.add(new TimeTableRequest(6, "Thur", "ECPC-***",  "tut", "G2","LT-102"));
//		list.add(new TimeTableRequest(7, "Thur", "ECPC-328",  "lab", "G3","LT-102"));
//
//		list.add(new TimeTableRequest(1, "Fri", "ECPC-304",  "class", "All","LT-101"));
//		list.add(new TimeTableRequest(2, "Fri", "ECPC-308",  "class", "All","LT-102"));
//		list.add(new TimeTableRequest(3, "Fri", "ECPC-306",  "class", "All","LT-103"));
//		list.add(new TimeTableRequest(4, "Fri", "ECPC-302",  "class", "All","LT-102"));
//		list.add(new TimeTableRequest(5, "Fri", "ECPC-328",  "lab", "G4","LT-101"));
////		list.add(new TimeTableRequest(6, "Fri", "ECPC-***",  "tut", "G2","LT-102"));
//		list.add(new TimeTableRequest(7, "Fri", "ECPC-328",  "lab", "G3","LT-102"));
//
//
//		timeTableService.createTimeTable("ECE_4", list);
//	}
//
//	void addNullPeriods(TimeTableService timeTableService, TimeTableRepository timeTableRepository){
//		String[] days = {"Mon","Tues","Wed","Thur","Fri"};
//		String[] groups = {"All","G1","G2","G3","G4","G5","G6","G7","G8"};
//
//		for(int period=1; period<=8; period++){
//			for(String day: days){
//				for(String group: groups){
//					TimeTable timeTable =  timeTableRepository.findTimeTableByPeriodAndGrpAndDayAndClassId(period, group, day, "ECE - 8");
//
//					if(timeTable!=null) continue;
//
//					TimeTableRequest timeTableRequest = new TimeTableRequest();
//					timeTableRequest.setPeriod(period);
//					timeTableRequest.setGrp(group);
//					timeTableRequest.setDay(day);
//					timeTableService.createTimeTable("ECE - 8",List.of(timeTableRequest));
//				}
//			}
//		}
//
//	}

//
//	void addProfessors(AuthenticationController authenticationController) {
//		Faker faker = new Faker();
//		for(int i=0; i<10; i++) {
//			String firstName = faker.name().firstName();
//			String lastName = faker.name().lastName();
//			String name = firstName + " " + lastName;
//			String email = String.format("%s%s@gmail.com", firstName, lastName);
//			String password = lastName;
//			Role role = Role.Professor;
//			String dept = "ECE";
//			String phoneNumber = String.valueOf(faker.phoneNumber());
//			authenticationController.registerProfessor(new RegisterProfessorRequest(email, password, role, name, dept, phoneNumber));
//		}
//	}
//
//	void addStudents(AuthenticationController authenticationController) {
//		Faker faker = new Faker();
//		for(int i=0; i<100; i++) {
//			String firstName = faker.name().firstName();
//			String lastName = faker.name().lastName();
//			String name = firstName + " " + lastName;
//			String email = String.format("%s%s@gmail.com", firstName, lastName);
//			String password = lastName;
//			Role role = Role.Student;
//			Long rollNumber = Long.valueOf(i+1);
//			String classID = "ECE_8";
//			authenticationController.registerStudent(new RegisterStudentRequest(email, password, role, name, rollNumber, classID));
//		}
//	}
}

