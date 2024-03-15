package com.project.contestapplication.storage.infrastructure;

import com.project.contestapplication.storage.domain.File;
import com.project.contestapplication.storage.domain.FileRequest;
import com.project.contestapplication.user.infrastructre.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String fileName;
    boolean isUsed = false;
    @ManyToOne(fetch = FetchType.LAZY)
    UserEntity writer;


    public static File toFile(FileEntity fileEntity){
        return new File(
                fileEntity.id,
                fileEntity.fileName,
                fileEntity.isUsed,
                UserEntity.toUser(fileEntity.writer));
    }

    public static FileEntity fromFile(File file){
        return new FileEntity(
                file.getId(),
                file.getFileName(),
                file.isUsed(),
                UserEntity.from(file.getWriter()));
    }

}
