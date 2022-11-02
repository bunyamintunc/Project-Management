package com.huawei.intern.projectmanagement.bugtracking.services;

import com.huawei.intern.projectmanagement.bugtracking.dtos.request.AddBug;
import com.huawei.intern.projectmanagement.bugtracking.dtos.response.BugResponse;
import com.huawei.intern.projectmanagement.bugtracking.models.Bug;
import com.huawei.intern.projectmanagement.bugtracking.repositories.BugRepository;
import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import com.huawei.intern.projectmanagement.task.dtos.response.UserResponse;
import com.huawei.intern.projectmanagement.task.models.*;
import com.huawei.intern.projectmanagement.task.services.TicketService;
import com.huawei.intern.projectmanagement.task.services.UserService;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;


import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BugServiceTest {

    @InjectMocks
    private BugService bugService;

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    @Mock
    private BugRepository bugRepository;

    @Mock
    private GenericService<Bug> genericService;


   @Mock
   protected GenericRepository<Bug> genericRepository;

   @BeforeEach
   public void setup(){
      MockitoAnnotations.initMocks(this);
      this.genericService = new GenericService<Bug>( genericRepository);
   }


   @Test
    public void it_should_create_bug() throws Exception {
       User testUser = User.builder().id(1L).username("abc").name("abc").build();
       Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
       Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();
       AddBug testAddBug = AddBug.builder().description("test").name("test").status("wARNİNG").userId(1l).build();

       when(userService.findById(any())).thenReturn(Optional.ofNullable(testUser));
       when(ticketService.findByName(any())).thenReturn(testTicket);
       when(bugRepository.save(any())).thenReturn(testBug);

       Bug result = bugService.saveBug(testAddBug);

       then(result).isNotNull();
       then(result.getName()).isEqualTo(testBug.getName());
   }

   @Test
    public void it_should_bug_close(){
       User testUser = User.builder().id(1L).username("abc").name("abc").build();
       Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
       Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();
       testBug.setClose(false);
       testBug.setName("test_bug");

       when(bugRepository.findById(1L)).thenReturn(Optional.of(testBug));
       when(bugRepository.save(testBug)).thenReturn(testBug);

       Bug result = bugService.bugClose(1L);

       then(result).isNotNull();
       then(result.getName()).isEqualTo(testBug.getName());
       then(result.getStatus()).isEqualTo(testBug.getStatus());


   }

   @Test
    public void it_should_change_user() throws Exception {
       User testUser = User.builder().id(1L).username("abc").name("abc").build();
       Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
       Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();
       testBug.setClose(false);
       testBug.setName("test_bug");

       when(userService.findById(any())).thenReturn(Optional.ofNullable(testUser));
       when(bugRepository.findById(any())).thenReturn(Optional.of(testBug));
       when(bugRepository.save(any())).thenReturn(testBug);

       bugService.changeUser(1L,1L);

   }

   @Test
    public void it_should_changeStatus(){
       User testUser = User.builder().id(1L).username("abc").name("abc").build();
       Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
       Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();
       testBug.setClose(false);
       testBug.setName("test_bug");

       when(bugRepository.findById(any())).thenReturn(Optional.of(testBug));
       when(bugRepository.save(any())).thenReturn(testBug);

       bugService.changeStatus(1L,"Warning");
   }

   @Test
    public void it_should_get_by_user() throws Exception {
       User testUser = User.builder().id(1L).username("abc").name("abc").build();
       Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
       Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();

       List<Bug> list = new ArrayList<>();
       list.add(testBug);


       when(userService.findById(any())).thenReturn(Optional.ofNullable(testUser));
       when(bugRepository.findByUser_id(1L)).thenReturn(list);

       List<BugResponse> result = bugService.getByUser(1L);
       then(result.size()).isEqualTo(1);
   }

   @Test
    public void it_should_bug_conver_to_reponseBug() throws Exception {
       User testUser = User.builder().id(1L).username("abc").name("abc").build();
       Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
       Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();

       when(userService.findById(any())).thenReturn(Optional.ofNullable(testUser));

       BugResponse result = bugService.bugConverToBugResponse(1L,testBug);

       then(result).isNotNull();
       then(result.getName()).isEqualTo(testBug.getName());
   }

   @Test
   public void it_should_getall() throws Exception {
      User testUser = User.builder().id(1L).username("abc").name("abc").build();
      Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
      Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();
      testBug.setName("abc");

      List<Bug> bugList = new ArrayList<>();
      bugList.add(testBug);

      when(genericRepository.findAll()).thenReturn(bugList);

      List<Bug> result = bugService.findAll();

      then(result).isNotNull();
      then(result.size()).isEqualTo(1);
      then(result.get(0).getName()).isEqualTo(testBug.getName());
   }

   @Test
   public void it_should_findbyid() throws Exception {
      User testUser = User.builder().id(1L).username("abc").name("abc").build();
      Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
      Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();
      testBug.setName("abc");

      when(genericRepository.findById(1L)).thenReturn(Optional.ofNullable(testBug));

      Optional<Bug> result = bugService.findById(1L);

      then(result).isNotNull();
      then(result.get().getName()).isEqualTo(testBug.getName());
   }

   @Test
   public void it_should_delete_by_id() throws Exception {

      doNothing().when(genericRepository).deleteById(1L);
      bugService.deleteById(1L);

   }

   @Test
   public void it_should_find_by_name() throws Exception {
      User testUser = User.builder().id(1L).username("abc").name("abc").build();
      Ticket testTicket = Ticket.builder().id(1L).name("abc").build();
      Bug testBug = Bug.builder().id(1L).ticket(testTicket).user(testUser).build();
      testBug.setName("abc");

      when(genericRepository.findByName(any())).thenReturn(testBug);

      Bug result = bugService.findByName("asdsa");

      then(result).isNotNull();
      then(result.getName()).isEqualTo(testBug.getName());

   }




}