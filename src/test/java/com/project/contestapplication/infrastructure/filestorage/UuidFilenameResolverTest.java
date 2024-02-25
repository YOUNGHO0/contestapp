package com.project.contestapplication.infrastructure.filestorage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

class UuidFilenameResolverTest {

    @Test
    @DisplayName("파일 뒤에 uuid 코드가 제대로 부착되는지 확인")
    public void fileNameTest(){
        //given
        String originalFileName = "myFile.mp3";
        String name = "file";
        String uuid = "uuid123";

        MultipartFile multipartFile = new FakeMultipartfile(originalFileName,name);
        UuidFilenameResolver resolver = new UuidFilenameResolver();

        //when
        String result =resolver.resolve(multipartFile,uuid);

        //then
        Assertions.assertThat(result).isEqualTo("myFile.mp3 uuid123");

    }

}