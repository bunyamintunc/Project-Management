package com.huawei.intern.projectmanagement.services;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    @Transactional
    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicketByName(String name){
        return (ticketRepository.findByName(name));
    }
}
