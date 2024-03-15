package com.project.contestapplication.storage.repository;

import com.project.contestapplication.storage.domain.File;

public interface FileRepository {

    File save(File file);

    void delete(File file);



}
