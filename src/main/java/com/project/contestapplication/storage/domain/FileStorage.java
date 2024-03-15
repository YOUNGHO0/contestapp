package com.project.contestapplication.storage.domain;

public interface FileStorage {

    boolean upload(FileRequest fileRequest);

    boolean delete(String key);



}
