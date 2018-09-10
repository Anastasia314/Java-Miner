package mystery;

class Matrix
{
    private Box [][] matrix;

    Matrix(Box defaultBox)
    {
        matrix=new Box[Ranges.getSize().x][Ranges.getSize().y];
        for(Cell cell : Ranges.getAllCells())
            matrix [cell.x][cell.y]=defaultBox;
    }

    Box get(Cell cell)
    {
        if(Ranges.inRange(cell))
        return matrix[cell.x][cell.y];
        return null;
    }

    void set(Cell cell, Box box){
        if(Ranges.inRange(cell))
        matrix[cell.x][cell.y]=box;
    }
}
