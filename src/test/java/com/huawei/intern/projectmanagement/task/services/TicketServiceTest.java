package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.repositories.TicketRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import  org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private GenericService<Ticket> genericService;

    
    @Mock
    protected GenericRepository<Ticket> genericRepository;
    
    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.genericService = new GenericService<Ticket>( genericRepository);
    }

    @Test
    public void it_should_create_ticket(){
        Ticket testTicket = Ticket.builder().name("test_ticket")
                .description("test_description")
                .id(1l).build();
        when(ticketRepository.save(any())).thenReturn(testTicket);
        Ticket result = ticketService.saveTicket(testTicket);

        then(result).isNotNull();
        then(result.getName()).isEqualTo(testTicket.getName());
    }

    @Test
    public void it_should_list_ticket() throws Exception {

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(testTicket);
        when(ticketRepository.findAll()).thenReturn(tickets);
        List<Ticket> result = genericService.findAll();
        then(result.size()).isEqualTo(1);

    }

    @Test
    public void it_shoul_get_ticket_by_id() throws Exception {
        Ticket testTicket = Ticket.builder().name("test_ticket").description("test_description").id(1l).build();

        when(ticketRepository.findById(1l)).thenReturn(Optional.ofNullable(testTicket));
        when(genericRepository.findById(any())).thenReturn(Optional.ofNullable(testTicket));
        Optional<Ticket> result = genericService.findById(1l);

        then(result).isNotNull();
        then(result.get().getName()).isEqualTo(testTicket.getName());

    }

    @Test
    public void it_should_getall() throws Exception {
        Ticket testTicket = Ticket.builder().id(1L).name("test_ticket").description("test_description").build();

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(testTicket);

        when(genericRepository.findAll()).thenReturn(ticketList);
        when(genericRepository.findAll()).thenReturn(ticketList);
        List<Ticket> result = ticketService.findAll();

        then(result).isNotNull();
        then(result.size()).isEqualTo(1);
        then(result.get(0).getName()).isEqualTo(testTicket.getName());
    }

    @Test
    public void it_should_findbyid() throws Exception {
        Ticket testTicket = Ticket.builder().id(1L).name("test_ticket").description("test_description").build();
        when(genericRepository.findById(any())).thenReturn(Optional.ofNullable(testTicket));

        Optional<Ticket> result = ticketService.findById(1L);

        then(result).isNotNull();
        then(result.get().getName()).isEqualTo(testTicket.getName());
    }

    @Test
    public void it_should_delete_by_id() throws Exception {
        Ticket testTicket = Ticket.builder().id(1L).name("test_ticket").description("test_description").build();
        doNothing().when(genericRepository).deleteById(any());

        ticketService.deleteById(1L);
    }

    @Test
    public void it_should_find_by_name() throws Exception {
        Ticket testTicket = Ticket.builder().id(1L).name("test_ticket").description("test_description").build();

        when(genericRepository.findByName(testTicket.getName())).thenReturn(testTicket);

        Ticket result = ticketService.findByName(testTicket.getName());

        then(result).isNotNull();
        then(result.getName()).isEqualTo(testTicket.getName());
    }

}