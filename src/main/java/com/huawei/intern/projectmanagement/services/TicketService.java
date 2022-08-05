package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.repositories.TicketRepository;
import com.huawei.intern.projectmanagement.services.abstracts.ITicketService;
import com.huawei.intern.projectmanagement.services.generics.impl.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class TicketService extends GenericService<Ticket> implements ITicketService {

    private final TicketRepository ticketRepository;



    @Transactional
    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }





}
