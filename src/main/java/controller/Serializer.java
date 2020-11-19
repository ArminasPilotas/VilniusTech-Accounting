package controller;

import java.io.*;

public class Serializer {

   /* public static Database loadDatabase() {
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("info.txt"))) {
            return (Database) stream.readObject();
        }
        catch (IOException exception){
            System.out.println("Cannot load file");
            return null;
        }
        catch (ClassNotFoundException exception){
            System.out.println("class not found");
            return null;
        }
    }
    public static void saveDatabase(Database database){
        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("info.txt"))) {
            stream.writeObject(database);
        }
        catch (IOException exception) {
            System.out.printf("Failed writing data to file");
        }
    }

    */
}
