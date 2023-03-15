/* This class contains logic related to adding the images to the user*/

package com.perscholas.app.controller;

import com.perscholas.app.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/images")
public class UserImageController {

    UserImageService userImageService;

    @Autowired
    public UserImageController(UserImageService userImageService) {
        this.userImageService = userImageService;
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws Exception {
        Resource file = userImageService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


}
