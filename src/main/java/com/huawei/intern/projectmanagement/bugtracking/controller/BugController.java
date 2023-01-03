package com.huawei.intern.projectmanagement.bugtracking.controller;

import com.huawei.intern.projectmanagement.bugtracking.dtos.request.AddBug;
import com.huawei.intern.projectmanagement.bugtracking.dtos.response.BugResponse;
import com.huawei.intern.projectmanagement.bugtracking.models.Bug;
import com.huawei.intern.projectmanagement.bugtracking.services.BugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bugs")
@Api(value = "Bug Tracking Api documantation")
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create new bug method")
    public Bug createBug(@RequestBody AddBug bug) throws Exception {
       return  bugService.saveBug(bug);
    }

    @GetMapping("/close/{bugId}")
    @ApiOperation(value = "Close  bug method")
    public Bug closeBug(@PathVariable("bugId") Long bugId){
        return bugService.bugClose(bugId);
    }

    @DeleteMapping("/delete/{bugId}")
    @ApiOperation(value = "Delete bug by bug id method")
    public void deleteBug(@PathVariable("bugId") Long bugId) throws Exception {
        bugService.deleteById(bugId);
    }

    @GetMapping("/getbyid/{bugId}")
    @ApiOperation(value = "Get one bug by id  method")
    public BugResponse getBugById(@PathVariable("bugId") Long bugId) throws Exception {
        return bugService.getBugById(bugId);
    }

    @GetMapping("/getall")
    @ApiOperation(value = "Get all bug method")
    public List<BugResponse> getBugs() throws Exception {
        return  bugService.getAll();
    }

    @PutMapping("/changeuser")
    @ApiOperation(value = "Change user  for bug method")
    public void changeUser(@RequestParam("bugId")Long bugId, @RequestParam("userId") Long userId) throws Exception {
        bugService.changeUser(bugId,userId);
    }

    @PutMapping("/changestatus")
    @ApiOperation(value = "Change status bug method")
    public void changeBugStatus(@RequestParam("bugId") Long bugId,@RequestParam("status") String status){
        bugService.changeStatus(bugId,status);
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get bug list by user id method")
    public List<BugResponse> getByUserId(@PathVariable("userId") Long userId) throws Exception {
         return bugService.getByUser(userId);
    }
}

