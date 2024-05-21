package models;

interface FileLoader {
    public Boolean isValid(String dirpath);
    public void load(String dirpath, GameManager game) throws Exception;
    public void save(String dirpath, GameManager game) throws Exception;
}
