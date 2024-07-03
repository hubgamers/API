package com.hubgamers.api.controller;

import com.hubgamers.api.model.Invitation;
import com.hubgamers.api.model.dto.InvitationDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.InvitationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
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
    public ResponseJson<List<Invitation>> getAllByTeamId(@PathVariable Long teamId) {
        return new ResponseJson<>(invitationService.getAllByTeamId(teamId), HttpStatus.OK.value());
    }
    
    @GetMapping("/allByPlayerId/{playerId}")
    public ResponseJson<List<Invitation>> getAllByPlayerId(@PathVariable Long playerId) {
        return new ResponseJson<>(invitationService.getAllByPlayerId(playerId), HttpStatus.OK.value());
    }

    /**
     * Récupérer toutes les invitations de staff en rapport à une équipe
     * @param teamId
     * @return
     */
    @GetMapping("/allJoinStaffByTeamId/{teamId}")
    public ResponseJson<List<Invitation>> getAllJoinStaffInvitationByTeamId(@PathVariable Long teamId) {
        return new ResponseJson<>(invitationService.getAllInvitationsByTeamIdAndType(teamId, Invitation.InvitationType.JOIN_STAFF), HttpStatus.OK.value());
    }

    /**
     * Récupérer toutes les invitations de staff en rapport à une équipe
     * @param teamId
     * @return
     */
    @GetMapping("/allJoinTeamRosterByTeamId/{teamId}")
    public ResponseJson<List<Invitation>> getAllJoinTeamRosterInvitationByTeamId(@PathVariable Long teamId) {
        return new ResponseJson<>(invitationService.getAllInvitationsByTeamIdAndType(teamId, Invitation.InvitationType.JOIN_TEAM_ROSTER), HttpStatus.OK.value());
    }

    /**
     * Récupérer toutes les invitations de recrutement de joueur en rapport à une équipe
     * @param teamId
     * @return
     */
    @GetMapping("/allRecruitStaffByTeamId/{teamId}")
    public ResponseJson<List<Invitation>> getAllRecruitStaffInvitationByTeamId(@PathVariable Long teamId) {
        return new ResponseJson<>(invitationService.getAllInvitationsByTeamIdAndType(teamId, Invitation.InvitationType.RECRUIT_STAFF), HttpStatus.OK.value());
    }

    /**
     * Récupérer toutes les invitations de recrutement de joueur en rapport à une équipe
     * @param teamId
     * @return
     */
    @GetMapping("/allRecruitPlayerByTeamId/{teamId}")
    public ResponseJson<List<Invitation>> getAllRecruitPlayerInvitationByTeamId(@PathVariable Long teamId) {
        return new ResponseJson<>(invitationService.getAllInvitationsByTeamIdAndType(teamId, Invitation.InvitationType.RECRUIT_PLAYER), HttpStatus.OK.value());
    }
    
    @GetMapping("/{id}")
    public ResponseJson<Invitation> getInvitationById(@PathVariable String id) {
        return new ResponseJson<>(invitationService.getInvitationById(id), HttpStatus.OK.value());
    }
    
    @PostMapping("/create")
    public ResponseJson<Invitation> createInvitation(@RequestBody InvitationDTO invitationDTO) throws AccountNotFoundException {
        return new ResponseJson<>(invitationService.createInvitation(invitationDTO), HttpStatus.CREATED.value());
    }
    
    @PutMapping("/update")
    public ResponseJson<Invitation> updateInvitation(@RequestBody InvitationDTO invitationDTO) throws AccountNotFoundException {
        return new ResponseJson<>(invitationService.createInvitation(invitationDTO), HttpStatus.OK.value());
    }
    
    @PostMapping("/accept/{id}")
    public ResponseJson<Invitation> acceptInvitation(@PathVariable String id) throws AccountNotFoundException {
        return new ResponseJson<>(invitationService.acceptInvitation(id), HttpStatus.OK.value());
    }
    
    @PostMapping("/decline/{id}")
    public ResponseJson<Invitation> declineInvitation(@PathVariable String id) {
        return new ResponseJson<>(invitationService.declineInvitation(id), HttpStatus.OK.value());
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteInvitation(@PathVariable String id) {
        invitationService.deleteInvitation(id);
    }
}
