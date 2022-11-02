package com.huawei.intern.projectmanagement;

import com.huawei.intern.projectmanagement.task.dtos.request.AddTaskDto;
import com.huawei.intern.projectmanagement.task.repositories.CommentRepository;
import com.huawei.intern.projectmanagement.task.repositories.UserRepository;
import com.huawei.intern.projectmanagement.task.models.Project;
import com.huawei.intern.projectmanagement.task.models.Role;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.models.User;
import com.huawei.intern.projectmanagement.task.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class ProjectmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectmanagementApplication.class, args);
	}



	@Bean
	CommandLineRunner commandLineRunner(UserService userService, TicketService ticketService, RoleService roleService, ProjectService projectService, TaskService taskService, UserRepository userRepository, CommentRepository commentRepository ){
		return args -> {
			Long sayi = Long.valueOf("1");
			userService.saveUser(User.builder().username("bunaymin").surName("tünç").password("12345678").email("abc").roles(new HashSet<>()).name("Bunyamin").build());
			userService.saveUser(User.builder().username("Eyüp").surName("tünç").password("abc").email("yup@eyup").roles(new HashSet<>()).name("Eyüp").imageUrl("https://cdn-icons-png.flaticon.com/512/219/219986.png").build());
			roleService.saveRole(Role.builder().name("ROLE_ADMIN").build());
			userService.addRoleToUser(sayi,sayi);
			userService.addRoleToUser(sayi+1,sayi);



			Ticket ticket = Ticket.builder().name("bug").description("there is bug").build();
			ticketService.saveTicket(ticket);

			Ticket ticket1 = Ticket.builder().name("database").description("update database").build();
			ticketService.saveTicket(ticket1);


			projectService.saveProject(Project.builder().name("ProjectManager").description("taskManager").build());
			projectService.saveProject(Project.builder().name("Proje2").description("deneme amacali acildi").build());


			System.out.println("sayi ---- >" +ticketService.findById(sayi));



			AddTaskDto addTaskDto = AddTaskDto.builder().desription("task açıklama")
					.name("Veritabanı").ticketId(sayi).ProjectId(sayi).userId(sayi).build();
            taskService.saveTask(addTaskDto);
			AddTaskDto addTaskDto1 = AddTaskDto.builder().desription("task1 açıklama")
					.name("frontend").ticketId(sayi).ProjectId(sayi).userId(sayi).build();
			taskService.saveTask(addTaskDto1);

			System.out.println("comment ---> " + commentRepository.findByTask_id(Long.valueOf("1")));
/*
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
			*/

		};


	}
}
