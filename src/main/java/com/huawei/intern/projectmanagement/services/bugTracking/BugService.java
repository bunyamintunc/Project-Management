package com.huawei.intern.projectmanagement.services.bugTracking;

import com.huawei.intern.projectmanagement.dtos.request.AddBug;
import com.huawei.intern.projectmanagement.dtos.response.BugResponse;
import com.huawei.intern.projectmanagement.dtos.response.UserResponse;
import com.huawei.intern.projectmanagement.models.Comment;
import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.models.bugTracking.Bug;
import com.huawei.intern.projectmanagement.repositories.bugTracking.BugRepository;
import com.huawei.intern.projectmanagement.services.TicketService;
import com.huawei.intern.projectmanagement.services.UserService;
import com.huawei.intern.projectmanagement.services.generics.impl.GenericService;
import org.springframework.stereotype.Service;

import javax.el.ELException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BugService extends GenericService<Bug> {


    private final BugRepository bugRepository;
    private final UserService userService;
    private final TicketService ticketService;


    public BugService(BugRepository bugRepository, UserService userService, TicketService ticketService) {
        this.bugRepository = bugRepository;
        this.userService = userService;
        this.ticketService = ticketService;
    }


    public List<BugResponse> getAll() throws Exception {
        List<BugResponse> bugs = new ArrayList<>();
        for( Bug bug : super.findAll()){
            BugResponse response = bugConverToBugResponse(bug.getUser().getId(),bug);
            bugs.add(response);
        }
        return  bugs;
    }

    public BugResponse getBugById(Long bugId) throws Exception {
        Bug bug  = super.findById(bugId).orElseThrow(() -> new RuntimeException("bug not foudn"));
        BugResponse response = bugConverToBugResponse(bug.getUser().getId(),bug);
        return response;
    }

    public Bug saveBug(AddBug bugDto) throws Exception {

        User user = userService.findById(bugDto.getUserId()).orElseThrow(()-> new ELException("user does not found"));
        Ticket ticket = ticketService.findByName("bug");

        if( user != null && ticket != null ){
            Bug bug = new Bug();
            bug.setName(bugDto.getName());
            bug.setDesription(bugDto.getDescription());
            bug.setTicket(ticket);
            bug.setUser(user);
            bug.setStatus(bugDto.getStatus());
            bug.setClose(false);
           return bugRepository.save(bug);
        }else {
            return null;
        }
    }

    public Bug bugClose(Long bugId){
        Bug bug = bugRepository.findById(bugId).orElseThrow(()-> new RuntimeException("bug does not found "));

        bug.setClose(true);
        return bugRepository.save(bug);
    }

    public void changeUser(Long bugId, Long userId) throws Exception {
        Bug bug = bugRepository.findById(bugId).orElseThrow(()-> new RuntimeException("bug does not found"));
        User user = userService.findById(userId).orElseThrow(()-> new RuntimeException("bug does not foudn"));
        bug.setUser(user);
        bugRepository.save(bug);
    }

    public void changeStatus(Long bugId, String status) {
        Bug bug = bugRepository.findById(bugId).orElseThrow(() -> new RuntimeException("bug not found"));
        bug.setStatus(status);
        bugRepository.save(bug);
    }

    public List<BugResponse> getByUser(Long userId) throws Exception {

        List<Bug> bugs = bugRepository.findByUser_id(userId);
        List<BugResponse> bugResponses = new ArrayList<>();
        if( bugs != null ){
            for ( Bug bug : bugs){
                  BugResponse response = bugConverToBugResponse(userId,bug);
                  bugResponses.add(response);
            }
            return bugResponses;
        }
       return null;
    }

   public   BugResponse bugConverToBugResponse(Long userId,Bug bug) throws Exception {

        User user = this.userService.findById(userId).orElseThrow(()-> new RuntimeException("user not found"));
        UserResponse userResponse = UserResponse.builder().username(user.getUsername())
               .imageUrl(user.getImageUrl()).id(user.getId()).email(user.getEmail()).build();
        BugResponse bugResponse = BugResponse.builder().userResponse(userResponse).description(bug.getDesription())
               .name(bug.getName()).isClose(bug.isClose()).status(bug.getStatus()).ticket(bug.getTicket()).build();


       return bugResponse;
    };


}


