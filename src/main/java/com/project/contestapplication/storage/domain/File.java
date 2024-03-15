package com.project.contestapplication.storage.domain;

import com.project.contestapplication.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class File {
    long id;
    String fileName;
    boolean isUsed = false;
    User writer;

    public File changeIsUsedTo(boolean isUsed){
        return new File(id,fileName,isUsed,writer);
    }

}
