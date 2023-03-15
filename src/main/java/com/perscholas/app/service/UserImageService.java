package com.perscholas.app.service;

import com.perscholas.app.controller.UserImageController;
import com.perscholas.app.model.UserImage;
import com.perscholas.app.repository.UserImageRepository;
import com.perscholas.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
@Slf4j
public class UserImageService {
    private final Path root = Paths.get("./uploads");
    UserRepository userRepository;
    RegistrationService registrationService;
    UserImageRepository userImageRepository;
@Autowired
    public UserImageService(UserRepository userRepository, RegistrationService registrationService, UserImageRepository userImageRepository) {
        this.userRepository = userRepository;
        this.registrationService = registrationService;
        this.userImageRepository = userImageRepository;
    }

    public void init() throws Exception{
        try{
            if(Files.exists(root)){
                log.warn("Folder is existing!!");
            } else{
                Files.createDirectories(root);
                log.warn("Folder created!!");
            }
        }catch(IOException e){
            throw new Exception("Could not create a folder");

        }

    }

    public void save(MultipartFile file, String email) throws Exception {
        try {

            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            log.debug(ext);
            String imageName = email.split("@")[0].concat("-").concat(String.valueOf(LocalDate.now().getYear())).concat(ext);
            log.debug(imageName);
            Files.copy(file.getInputStream(), this.root.resolve(imageName));
            Path p = root.resolve(imageName);
            String url = MvcUriComponentsBuilder
                    .fromMethodName(UserImageController.class, "getImage", p.getFileName().toString()).build().toString();
            log.debug(url);

            userImageRepository.save(new UserImage(imageName,url, userRepository.findByEmailAllIgnoreCase(email).get()));
       } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new Exception("A file of that name already exists.");
            } else {
                throw new Exception("Error copying file to HD" + file.getOriginalFilename());
            }

        }
    }

    public Resource load(String filename) throws Exception{
       try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }


    }

}
