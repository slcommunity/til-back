package com.example.tilproject.domain;

import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;
import com.example.tilproject.utils.TurnValidator;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    @NotNull
    private String turn;

    @OneToMany(mappedBy = "turn", fetch = FetchType.LAZY)
    List<User> users = new ArrayList<User>();

    public Turn(TurnRequestDto requestDto) {
        TurnValidator.validateTurnCreate(requestDto);
        this.turn = requestDto.getTurnName();
    }
    public Turn(String turn) {
        this.turn = turn;
    }

    public void update(TurnModifyDto turnModifyDto){
        TurnValidator.validateTurnModify(turnModifyDto);
        this.turn = turnModifyDto.getNewTurn();
    }

    public Turn(Long idx, String turn, List<User> users) {
        this.idx = idx;
        this.turn = turn;
        this.users = users;
    }
}
