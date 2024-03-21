package com.hubgamers.api.controller;

import com.hubgamers.api.model.Invitation;
import com.hubgamers.api.model.dto.InvitationDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.InvitationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invitation")
public class InvitationController {
    
    private final InvitationService invitationService;
    
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }
    
    @GetMapping("/columns")
    public ResponseJson<List<String>> getColumns() {
        return new ResponseJson<>(invitationService.getColumns(), HttpStatus.OK.value());
    }
    
    @GetMapping("/all")
    public ResponseJson<List<Invitation>> getAll() {
        return new ResponseJson<>(invitationService.getAll(), HttpStatus.OK.value());
    }
    
    @GetMapping("/allByTeamId/{teamId}")
    public ResponseJson<List<Invitation>> getAllByTeamId(@PathVariable String teamId) {
        return new ResponseJson<>(invitationService.getAllByTeamId(teamId), HttpStatus.OK.value());
    }
    
    @GetMapping("/allJoinByTeamId/{teamId}")
    public ResponseJson<List<Invitation>> getAllJoinInvitationByTeamId(@PathVariable String teamId) {
        return new ResponseJson<>(invitationService.getAllJoinInvitationByTeamId(teamId), HttpStatus.OK.value());
    }
    
    @GetMapping("/allRecruitPlayerByTeamId/{teamId}")
    public ResponseJson<List<Invitation>> getAllRecruitPlayerInvitationByTeamId(@PathVariable String teamId) {
        return new ResponseJson<>(invitationService.getAllRecruitPlayerInvitationByTeamId(teamId), HttpStatus.OK.value());
    }
    
    @GetMapping("/{id}")
    public ResponseJson<Invitation> getInvitationById(@PathVariable String id) {
        return new ResponseJson<>(invitationService.getInvitationById(id), HttpStatus.OK.value());
    }
    
    @PostMapping("/create")
    public ResponseJson<Invitation> createInvitation(@RequestBody InvitationDTO invitationDTO) {
        return new ResponseJson<>(invitationService.createInvitation(invitationDTO), HttpStatus.CREATED.value());
    }
    
    @PutMapping("/update")
    public ResponseJson<Invitation> updateInvitation(@RequestBody InvitationDTO invitationDTO) {
        return new ResponseJson<>(invitationService.createInvitation(invitationDTO), HttpStatus.OK.value());
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteInvitation(@PathVariable String id) {
        invitationService.deleteInvitation(id);
    }
}
