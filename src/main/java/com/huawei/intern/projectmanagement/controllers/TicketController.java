package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody  Ticket ticket){
        return ticketService.saveTicket(ticket);
    }

    @GetMapping

    public List<Ticket> listTickets(){
        return  ticketService.getAllTickets();
    }

    @GetMapping("/{name}")
    public Ticket getTicketByName(@PathVariable("name") String name){
        return ticketService.getTicketByName(name);
    }


}
