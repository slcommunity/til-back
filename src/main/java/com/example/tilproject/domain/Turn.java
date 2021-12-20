package com.example.tilproject.domain;

import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;
import com.example.tilproject.utils.TurnValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String turn;

    @OneToMany(mappedBy = "turn", fetch = FetchType.LAZY)
    List<User> users = new ArrayList<User>();

    public Turn(TurnRequestDto requestDto) {
        TurnValidator.validateTurnCreate(requestDto);
        this.turn = requestDto.getTurnName();
    }


    public void update(TurnModifyDto turnModifyDto){
        TurnValidator.validateTurnModify(turnModifyDto);
        this.turn = turnModifyDto.getNewTurn();
    }
}
