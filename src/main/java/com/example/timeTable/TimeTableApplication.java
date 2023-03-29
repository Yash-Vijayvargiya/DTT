package com.example.timeTable;

import com.example.timeTable.controller.AdminController;
import com.example.timeTable.controller.AuthenticationController;
import com.example.timeTable.model.entities.Role;
import com.example.timeTable.model.entities.TimeTable;
import com.example.timeTable.model.requestModel.CourseLabRequest;
import com.example.timeTable.model.requestModel.CourseRequest;
import com.example.timeTable.model.requestModel.RegisterProfessorRequest;
import com.example.timeTable.model.requestModel.TimeTableRequest;
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

	@Bean
	CommandLineRunner commandLineRunner(TimeTableRepository timeTableRepository, AuthenticationController authenticationController, AdminController adminController, TimeTableService timeTableService){
		return args -> {
			addProfessors(authenticationController);
			addCourses(adminController);
			addTimeTable(timeTableService);
			addNullPeriods(timeTableService, timeTableRepository);
		};
	}

	void addProfessors(AuthenticationController authenticationController) {
		authenticationController.registerProfessor(
				new RegisterProfessorRequest(
						"Indu@gmail.com", "Saini", Role.Professor, "Indu Saini", "ECE", "7814803200"
				)
		);
	}

	void addCourses(AdminController adminController){
		adminController.createCourse(
				new CourseRequest(
						"AIC", "ECPC-***", "ECE", "Indu@gmail.com"
				)
		);

		adminController.createCourseLab(
				new CourseLabRequest(
						"AIC-LAB", "ECPC-**1", "G2", "ECE", "Indu@gmail.com"
				)
		);
	}

	void addTimeTable(TimeTableService timeTableService){
		List<TimeTableRequest> list = new ArrayList<>();
		list.add(new TimeTableRequest(1, "Monday", "ECPC-***", "AIC", "class", "Indu Saini", "All"));

		timeTableService.createTimeTable("ece_8", list);
	}

	void addNullPeriods(TimeTableService timeTableService, TimeTableRepository timeTableRepository){
		String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
		String[] groups = {"All","G1","G2","G3","G4"};

		for(int period=1; period<=8; period++){
			for(String day: days){
				for(String group: groups){
					TimeTable timeTable =  timeTableRepository.findTimeTableByPeriodAndGrpAndDayAndClassId(period, group, day, "ece_8");

					if(timeTable!=null) continue;

					TimeTableRequest timeTableRequest = new TimeTableRequest();
					timeTableRequest.setPeriod(period);
					timeTableRequest.setGrp(group);
					timeTableRequest.setDay(day);
					timeTableService.createTimeTable("ece_8",List.of(timeTableRequest));
				}
			}
		}

	}

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

