package ru.adept.commander;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Wrapper class for file information
 * @author adept
 * @version 1.0
 */
public class FileInformation {
    private String filename;
    private FileType type;
    private long size;
    private LocalDateTime lastModified;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public FileInformation(Path path) {
        try {
            this.size = Files.size(path);
            this.filename = path.getFileName().toString();
            this.type = Files.isDirectory(path) ? FileType.DIRECTORY : FileType.FILE;

            // For correct sorting
            if (this.type == FileType.DIRECTORY) {
                this.size = -1L;
            }

            this.lastModified = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneOffset.ofHours(0));
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать экземпляр FileInformation по пути:" + path.toString());
        }
    }

    @Override
    public String toString() {
       return "Type: " + this.type + " Filename: " + this.filename + " Size: " + this.size + " Modified: " + this.lastModified;
    }

    /**
     * Enumeration file types
     * @author adept
     * @version 1.0
     */
    public enum FileType {
        FILE("F"), DIRECTORY("D");

        private String name;

        public String getName() {
            return name;
        }

        FileType(String name) {
            this.name = name;
        }
    }
}
