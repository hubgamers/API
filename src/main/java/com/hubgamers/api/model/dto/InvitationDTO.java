package com.hubgamers.api.model.dto;

import com.hubgamers.api.model.Invitation;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvitationDTO {
    public Long id;

    public Long playerId;

    public Long teamId;

    public Invitation.InvitationStatus status = Invitation.InvitationStatus.PENDING;

    public Invitation.InvitationType type;
}
