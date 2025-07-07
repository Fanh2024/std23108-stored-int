package com.hei.school.endpoint.rest.controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

@RestController
public class StoredIntController {

    private static final String FILE_PATH = "/tmp/stored-int.txt";

    @GetMapping("/stored-int")
    public String getStoredInt() {
        File file = new File(FILE_PATH);
        String value;

        if (file.exists()) {
            try {
                value = Files.readString(file.toPath());
            } catch (IOException e) {
                return "Erreur lors de la lecture du fichier.";
            }
        } else {
            value = String.valueOf(new Random().nextInt(1_000_000));
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(value);
            } catch (IOException e) {
                return "Erreur lors de l'écriture du fichier.";
            }
        }

        return value;
    }
}
