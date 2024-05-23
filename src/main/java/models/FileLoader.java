package models;

public interface FileLoader {
    public Boolean isValid(String dirpath);
    public void load(String dirpath) throws Exception;
    public void save(String dirpath) throws Exception;
}
