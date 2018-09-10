package mystery;

class Front
{
    private Matrix frontMap;
    private int countOfClosedBoxes;


    void start()
    {
        frontMap =new Matrix(Box.MCLOSED);
        countOfClosedBoxes= Ranges.getSize().x* Ranges.getSize().y;

    }
    Box get(Cell cell)
    {
        return frontMap.get(cell);
    }
    public void setOpenedToBox(Cell cell)
    {
        frontMap.set(cell,Box.MOPENED);
        countOfClosedBoxes--;
    }

    void toggleFlagedToBox(Cell cell)
    {
       switch (frontMap.get(cell))
       {
           case FLAGED:setClosedToBox(cell);break;
           case MCLOSED:setFlagedToBox(cell);break;

       }
    }

    private void setClosedToBox(Cell cell)
    {
    frontMap.set(cell,Box.MCLOSED);
    }

    void setFlagedToBox(Cell cell)
    {
        frontMap.set(cell,Box.FLAGED);
    }

    int getCountOfClosedBoxes()
    {
        return countOfClosedBoxes;
    }

    public void setbombedToBox(Cell cell)
    {
     frontMap.set(cell,Box.MBOMBED);
    }

    public void setOpenedToClosedBombBox(Cell cell) {
        if(frontMap.get(cell)==Box.MCLOSED)
        frontMap.set(cell,Box.MOPENED);
    }

    public void setNoBombToflagedbox(Cell cell) {
        if(frontMap.get(cell)==Box.FLAGED)
        frontMap.set(cell,Box.MNOBOMB);
    }


    int getCountOfFlagedBoxesAround(Cell cell) {
        int count=0;
        for(Cell around: Ranges.getCellsAround(cell))
            if(frontMap.get(around)== Box.FLAGED)
                count++;
        return count;
    }

}
