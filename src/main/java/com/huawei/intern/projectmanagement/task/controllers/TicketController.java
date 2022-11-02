package com.huawei.intern.projectmanagement.task.controllers;

import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.services.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Ticket Api Documantation")
public class TicketController  {

    @Autowired
    private final TicketService ticketService;


    @GetMapping("/getall")
    @ApiOperation(value = "Get all ticket method")
    public ResponseEntity<List<Ticket>> getTickets() throws Exception {
        return  new ResponseEntity<>(ticketService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create new ticket  method")
    public Ticket createTicket(@RequestBody  Ticket ticket){
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/getbyid/{id}")
    @ApiOperation(value = "Get one ticket by id  method")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(ticketService.findById(id).orElse(null),HttpStatus.OK);
    }


    @GetMapping("/getbyname/{name}")
    @ApiOperation(value = "Get one ticket by name")
    public Ticket getTicketByName(@PathVariable("name") String name) throws Exception {
        return ticketService.findByName(name);
    }

    @DeleteMapping("/delete/{ticketId}")
    @ApiOperation(value = "Delete one ticket by id method")
    public void delete(@PathVariable("ticketId") Long id) throws Exception {
        ticketService.deleteById(id);
    }




}
