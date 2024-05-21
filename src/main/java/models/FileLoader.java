package models;

import java.util.Scanner;

interface FileLoader {
    public Boolean isValid(String dirpath);
    public void load(String dirpath, GameManager game) throws Exception;
    public void loadGameState(String dirpath,GameManager game,Scanner reader);
    public void loadPlayer(String dirpath, Player player);
    public void save(String dirpath, GameManager game) throws Exception;
}
