package com.huawei.intern.projectmanagement;

import com.huawei.intern.projectmanagement.dtos.request.AddTaskToProjectDto;
import com.huawei.intern.projectmanagement.models.*;
import com.huawei.intern.projectmanagement.services.*;
import org.hibernate.usertype.UserVersionType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class ProjectmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectmanagementApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(UserService userService, TicketService ticketService, RoleService roleService, ProjectService projectService, TaskService taskService){
		return args -> {
			userService.saveUser(User.builder().username("bunaymin").surName("tünç").password("12345678").email("abc").roles(new HashSet<>()).build());
			userService.saveUser(User.builder().username("Eyüp").surName("tünç").password("abc").email("yup@eyup").roles(new HashSet<>()).imageUrl("https://cdn-icons-png.flaticon.com/512/219/219986.png").build());
			roleService.saveRole(Role.builder().name("ROLE_ADMIN").build());
			userService.addRoleToUser("bunaymin","ROLE_ADMIN");
			userService.addRoleToUser("Eyüp","ROLE_ADMIN");


			Ticket ticket = Ticket.builder().name("bug").description("there is bug").build();
			ticketService.saveTicket(ticket);

			Ticket ticket1 = Ticket.builder().name("database").description("update database").build();
			ticketService.saveTicket(ticket1);


			projectService.saveProject(Project.builder().name("ProjectManager").tasks(new HashSet<>()).description("taskManager").build());
			projectService.saveProject(Project.builder().name("Proje2").tasks(new HashSet<>()).description("deneme amacali acildi").build());


			AddTaskToProjectDto addTaskToProjectDto = AddTaskToProjectDto.builder()
					.projectName("ProjectManager").name("Deneme").desription("deneme amaçlı açtım ")
					.imageUrl("sadsadsadsadsad")
					.userName("bunaymin").ticket("bug")
					.build();
            projectService.addTaskForProject(addTaskToProjectDto);

			AddTaskToProjectDto addTaskToProjectDto1 = AddTaskToProjectDto.builder()
					.projectName("ProjectManager").name("Ben").desription("ben deneme amaçlı açtım ")
					.imageUrl("bunyaminImage")
					.userName("bunaymin")
					.ticket("bug")
					.build();
			projectService.addTaskForProject(addTaskToProjectDto1);





			taskService.addTicketToTask("Deneme","bug");
			taskService.addTicketToTask("Deneme","database");
			taskService.addTicketToTask("Ben","database");
			List<Task> tasks = taskService.getTasksByTicketName("database");
			for(Task task:tasks){
				System.out.println(task);
			}
		};


	}
}
