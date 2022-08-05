package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.dtos.request.AddBug;
import com.huawei.intern.projectmanagement.dtos.response.BugResponse;
import com.huawei.intern.projectmanagement.models.bugTracking.Bug;
import com.huawei.intern.projectmanagement.services.bugTracking.BugService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bugs")
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @PostMapping("/create")
    public Bug createBug(@RequestBody AddBug bug) throws Exception {
       return  bugService.saveBug(bug);
    }

    @GetMapping("/close/{bugId}")
    public Bug closeBug(@PathVariable("bugId") Long bugId){
        return bugService.bugClose(bugId);
    }

    @DeleteMapping("/delete/{bugId}")
    public void deleteBug(@PathVariable("bugId") Long bugId) throws Exception {
        bugService.deleteById(bugId);
    }

    @GetMapping("/getbyid/{bugId}")
    public BugResponse getBugById(@PathVariable("bugId") Long bugId) throws Exception {
        return bugService.getBugById(bugId);
    }

    @GetMapping("/getall")
    public List<BugResponse> getBugs() throws Exception {
        return  bugService.getAll();
    }

    @PutMapping("/changeuser")
    public void changeUser(@RequestParam("bugId")Long bugId, @RequestParam("userId") Long userId) throws Exception {
        bugService.changeUser(bugId,userId);
    }

    @PutMapping("/changestatus")
    public void changeBugStatus(@RequestParam("bugId") Long bugId,@RequestParam("status") String status){
        bugService.changeStatus(bugId,status);
    }

    @GetMapping("/user/{userId}")
    public List<BugResponse> getByUserId(@PathVariable("userId") Long userId) throws Exception {
         return bugService.getByUser(userId);
    }
}

