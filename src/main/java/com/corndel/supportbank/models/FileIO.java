package com.corndel.supportbank.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileIO {

    private Path filePath;

    // constructor
    public FileIO(String fileName) {
        this.filePath = Paths.get("src", "data",
                "transactions", fileName);
    }

    // read each line
    public List<String> readLines() throws IOException {
        return Files.readAllLines(this.filePath);
    }
}
