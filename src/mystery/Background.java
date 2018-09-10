package mystery;

 class Background
 {
     private Matrix backgroundMap;
     private int totalBombs;
     Background(int totalBombs)
     {
         this.totalBombs=totalBombs;
     }

     void start()
     {
         backgroundMap =new Matrix(Box.ZERO);
         for(int j=0;j<totalBombs;j++)
         placeBomb();
     }

     Box get(Cell cell)
     {
         return backgroundMap.get(cell);
     }
     private void fixBombCount()
     {
        int maxBombs= Ranges.getSize().x* Ranges.getSize().y/2;
        if(totalBombs>maxBombs)
            totalBombs=maxBombs;
     }
     private void placeBomb()
     {
         while (true)
         {
         Cell cell = Ranges.getRandomCell();
         if(backgroundMap.get(cell)==Box.MBOMB)
             continue;
         backgroundMap.set(cell,Box.MBOMB);
         incNumbersAroundBomb(cell);
         break;
         }
     }

     private void incNumbersAroundBomb(Cell cell)
     {
         for (Cell around : Ranges.getCellsAround(cell))
             if(Box.MBOMB!=backgroundMap.get(around))
             backgroundMap.set(around,backgroundMap.get(around).getNextNumberBox());
     }

      int getTotalBombs()
     {
         return totalBombs;
     }
 }
