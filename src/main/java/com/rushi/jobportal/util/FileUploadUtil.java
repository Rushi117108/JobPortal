package com.rushi.jobportal.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.MulticastChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void uploadFile(String dir, String fileName, MultipartFile multipartFile) throws IOException {

        Path path = Paths.get(dir);

        if(!Files.exists(path)){
            Files.createDirectories(path);
        }

        try(InputStream is = multipartFile.getInputStream()) {
            Path path1 = path.resolve(fileName);
            Files.copy(is, path1, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){
            throw new IOException("Could not save image ");
        }
    }



}
