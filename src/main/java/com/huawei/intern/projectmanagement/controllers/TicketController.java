package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.services.TicketService;
import com.huawei.intern.projectmanagement.services.abstracts.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class TicketController  {

    @Autowired
    private final TicketService ticketService;


    @GetMapping("/getall")
    public ResponseEntity<List<Ticket>> getTickets() throws Exception {
        return  new ResponseEntity<>(ticketService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public Ticket createTicket(@RequestBody  Ticket ticket){
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(ticketService.findById(id).orElse(null),HttpStatus.OK);
    }


    @GetMapping("/getbyname/{name}")
    public Ticket getTicketByName(@PathVariable("name") String name) throws Exception {
        return ticketService.findByName(name);
    }

    @DeleteMapping("/delete/{ticketId}")
    public void delete(@PathVariable("ticketId") Long id) throws Exception {
        ticketService.deleteById(id);
    }




}
