package com.project.contestapplication.userplay.infrastructure;

import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.infrastructure.ContestEntity;
import com.project.contestapplication.contest.infrastructure.ContestJpaRepository;
import com.project.contestapplication.storage.infrastructure.FileEntity;
import com.project.contestapplication.storage.infrastructure.FileJpaRepository;
import com.project.contestapplication.user.infrastructre.UserEntity;
import com.project.contestapplication.user.infrastructre.UserJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
@DataJpaTest
class UserPlayJpaRepositoryTest {

    @Autowired
    UserPlayJpaRepository userPlayJpaRepository;

    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    ContestJpaRepository contestJpaRepository;

    @Autowired
    FileJpaRepository fileJpaRepository;


    @Test
    public void findByWriter_테스트(){
        UserEntity userEntity = new UserEntity(0,"헬로","foiwe@gmail.com","password",10,29) ;
        ContestEntity contestEntity = new ContestEntity(0,"대회","대회내용",10,29,userEntity, ContestStatus.PENDING);
        FileEntity fileEntity =new FileEntity(0,"파일명",true,userEntity);
        UserPlayEntity userPlayEntity = new UserPlayEntity(0,"졔 연주입니다",contestEntity,fileEntity,userEntity);
        userJpaRepository.save(userEntity);
        contestJpaRepository.save(contestEntity);
        fileJpaRepository.save(fileEntity);
        userPlayJpaRepository.save(userPlayEntity);


        Assertions.assertThatNoException().isThrownBy(()-> userPlayJpaRepository.findByWriter(userEntity));
        Optional<UserPlayEntity> optionalUserPlayEntity = userPlayJpaRepository.findByWriter(userEntity);
        Assertions.assertThat(optionalUserPlayEntity).isPresent();
        System.out.println(optionalUserPlayEntity.get().id + " "+ userPlayEntity.id);
        Assertions.assertThat(optionalUserPlayEntity.get()).isEqualTo(userPlayEntity);

    }

}