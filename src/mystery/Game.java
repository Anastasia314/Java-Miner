package mystery;

public class Game
{
    private Background background;
    private Front front;

    public GameStatus getState() {
        return state;
    }

    private GameStatus state;

    public Game(int cols,int rows,int bombs){
        Ranges.setSize(new Cell(cols,rows));
        background =new Background(bombs);
        front =new Front();
    }

    public void start()
    {
        background.start();
        front.start();
        state= GameStatus.PLAYED;
    }


    public Box getBox(Cell cell)
    {
        if(front.get(cell)== Box.MOPENED)
            return background.get(cell);
        else
            return front.get(cell);
    }

    public void pressLeftButton(Cell cell)
    {
        if(gameOver())return;
       openBox(cell);
        checkWinner();
    }

    private void checkWinner()
    {
        if(state== GameStatus.PLAYED)
            if(front.getCountOfClosedBoxes()== background.getTotalBombs())
                state= GameStatus.WINNER;
    }

    public void openBox(Cell cell) {
        switch (front.get(cell))
        {
            case MOPENED: setOpenedToClosedBoxesAroundNumber(cell); return;
            case FLAGED: return;
            case MCLOSED:
                switch (background.get(cell))
                {
                    case ZERO: openBoxesArround(cell);return;
                    case MBOMB: {openBombs(cell);
                    state= GameStatus.BOMBED;
                    return;}
                    default:  front.setOpenedToBox(cell); return;
                }
        }
    }

    void setOpenedToClosedBoxesAroundNumber(Cell cell)
    {
        if(background.get(cell)!=Box.MBOMB)
        if(front.getCountOfFlagedBoxesAround(cell)== background.get(cell).getNumber())
        for(Cell around: Ranges.getCellsAround(cell))
            if(front.get(around)== Box.MCLOSED)
                openBox(around);
    }

    private void openBombs(Cell bombed) {
        front.setbombedToBox(bombed);
        for (Cell cell : Ranges.getAllCells())
            if (background.get(cell) == Box.MBOMB)
                front.setOpenedToClosedBombBox(cell);
            else
                front.setNoBombToflagedbox(cell);

    }

    private void openBoxesArround(Cell cell) {
        front.setOpenedToBox(cell);
        for(Cell around: Ranges.getCellsAround(cell))
            openBox(around);
    }

    public void pressRightButton(Cell cell)
    {
        if(gameOver())return;
        front.toggleFlagedToBox(cell);
    }
    private boolean gameOver()
    {
        if(state== GameStatus.PLAYED)
            return false;
        start();
        return true;
    }
}
