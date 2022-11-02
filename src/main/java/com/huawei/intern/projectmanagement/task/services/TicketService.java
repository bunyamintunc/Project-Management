package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.repositories.TicketRepository;
import com.huawei.intern.projectmanagement.task.services.abstracts.ITicketService;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class TicketService extends GenericService<Ticket> implements ITicketService {

    private  TicketRepository ticketRepository;



    @Transactional
    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }





}
