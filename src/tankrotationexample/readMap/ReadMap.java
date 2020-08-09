package tankrotationexample.readMap;

import tankrotationexample.gameObject.*;
import tankrotationexample.handler.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadMap {

    private ArrayList<GameObject> gameObjects;
    private int [][] map;
    private Handler handler;
    public ReadMap(Handler handler){
        this.handler = handler;
    }
    public void  init(){
        gameObjects = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(TRE.class.getClassLoader().getResourceAsStream("maps/map1"));
            BufferedReader mapReader = new BufferedReader(isr);
            String row = mapReader.readLine();
            if (null == row) {
                throw new IOException("no data in file");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);
            map = new int[numCols][numRows];
            int x = 0;
            for (int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for (int curCol = 0; curCol < numCols; curCol++) {
                    switch (mapInfo[curCol]) {
                        case "2":
                            this.gameObjects.add(new BreakableWall(this.handler , curCol * 32, curRow * 32));
                            map[curCol][curRow] = x;
                            x++;
                            break;
                        case "3":
                            this.gameObjects.add(new HealthPowerUp(this.handler, curCol * 32, curRow * 32));
                            map[curCol][curRow] = x;
                            x++;
                            break;
                        case "4":
                            this.gameObjects.add(new SpeedBoostPowerUp(this.handler, curCol * 32, curRow * 32));
                            map[curCol][curRow] = x;
                            x++;
                            break;
                        case "5":
                            this.gameObjects.add(new ControlReversal(this.handler, curCol * 32, curRow * 32));
                            map[curCol][curRow] = x;
                            x++;
                            break;
                        case "9":
                            this.gameObjects.add(new UnBreakableWall(this.handler, curCol * 32, curRow * 32));
                            map[curCol][curRow] = x;
                            x++;
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            System.out.println("Did not read map properly");
            System.exit(3);
        }
    }
    public void addEntities(){
    }//you can add Entities here if you want.

    public ArrayList<GameObject> getGameObjects() {
        return this.gameObjects;
    }
}
