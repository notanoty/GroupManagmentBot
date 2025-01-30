package com.notanoty.demo.Strike;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.notanoty.demo.Chat.Chat;
import com.notanoty.demo.Chat.ChatDto;
import com.notanoty.demo.Member.Member;
import com.notanoty.demo.Member.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "strikes")
public class Strike {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "strike_id")
    private Long strikeId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @Column(name = "reason")
    private String reason;



}
