package com.huawei.intern.projectmanagement.repositories;

import com.huawei.intern.projectmanagement.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public Ticket findByName(String name);

}
