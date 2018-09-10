package mystery;

import java.util.ArrayList;
import java.util.Random;
public class Ranges
{
    private  static Cell size;
    private static ArrayList<Cell> allCells;
    private static Random random=new Random();
    public static void setSize(Cell _size)
    {
        size=_size;
        allCells =new ArrayList<Cell>();
        for(int y=0;y<size.y;y++)
            for(int x=0;x<size.x; x++)
                allCells.add(new Cell(x,y));
    }
    public static Cell getSize()
    {
        return size;
    }
    public static ArrayList<Cell> getAllCells()
    {
        return allCells;
    }
    static boolean inRange(Cell cell)
    {
        return  cell.x >= 0 && cell.x < size.x &&
                cell.y >= 0 && cell.y < size.y;
    }
    static Cell getRandomCell()
    {
        return new Cell(random.nextInt(size.x),
                         random.nextInt(size.y));
    }
    static ArrayList<Cell> getCellsAround(Cell cell)
    {
        Cell around;
        ArrayList<Cell> list =new ArrayList<Cell>();
        for (int x = cell.x-1; x<= cell.x+1; x++)
            for (int y = cell.y-1; y<= cell.y+1; y++)
                if(inRange(around=new Cell(x,y)))
                    if(!around.equals(cell))
                        list.add(around);

        return list;
    }
}

