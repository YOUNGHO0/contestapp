package com.project.contestapplication.userplay.infrastructure;

import com.project.contestapplication.contest.infrastructure.ContestEntity;
import com.project.contestapplication.storage.infrastructure.FileEntity;
import com.project.contestapplication.user.infrastructre.UserEntity;
import com.project.contestapplication.userplay.domain.UserPlay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserPlayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String playTitle;
    @JoinColumn(name = "contest_id")
    @ManyToOne
    ContestEntity contest;
    @JoinColumn(name = "audio_file")
    @OneToOne(fetch = FetchType.LAZY)
    FileEntity audioFile;
    @JoinColumn(name = "writer")
    @ManyToOne
    UserEntity writer;


    public static UserPlayEntity from(UserPlay userPlay){

        return new UserPlayEntity(
                userPlay.getId(),
                userPlay.getPlayTitle(),
                ContestEntity.from(userPlay.getContest()),
                FileEntity.fromFile(userPlay.getAudioFile()),
                UserEntity.from(userPlay.getWriter()));
    }

    public static UserPlay toUserPlay(UserPlayEntity userPlayEntity){

        return new UserPlay(
                userPlayEntity.id,
                ContestEntity.toContest(userPlayEntity.contest),
                userPlayEntity.playTitle,
                FileEntity.toFile(userPlayEntity.audioFile),
                UserEntity.toUser(userPlayEntity.writer));
    }

}
