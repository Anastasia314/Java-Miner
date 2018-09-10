package mystery;

public class Cell
{
    public int x;
    public int y;
    public Cell(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Cell)
        {
            Cell to= (Cell)o;
            return to.x==x && to.y==y;
        }
        return super.equals(o);
    }

}
