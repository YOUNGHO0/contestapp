package com.project.contestapplication.storage.repository;

import com.project.contestapplication.storage.domain.File;
import com.project.contestapplication.storage.domain.FileRequest;
import com.project.contestapplication.storage.infrastructure.FileEntity;
import com.project.contestapplication.storage.infrastructure.FileJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepository{

    private final FileJpaRepository fileJpaRepository;

    @Override
    public File save(File file) {
        FileEntity fileEntity = fileJpaRepository.save(FileEntity.fromFile(file));
        return FileEntity.toFile(fileEntity);
    }

    @Override
    public void delete(File file) {
        fileJpaRepository.delete(FileEntity.fromFile(file));
    }


}
