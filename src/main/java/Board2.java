import java.util.Arrays;

public final class Board2 {

    PlacedTile[] tiles;
    int[] indices;
    int reach;

    public static Board2 EMPTY = new Board2(0, new PlacedTile[0], new int[0]);

    public static int sideLength(int reach){
        return 2 * reach + 1;
    }

    public static int indexFor(int x, int y, int reach){
        int maxIndex = Math.pow(sideLength(reach));
        int indexOf = x + reach + (y + reach) * sideLength(reach);
        
        return (indexOf<=maxIndex && indexOf>0) ? indexOf : -1; 
    }

    private Board2(int reach, PlacedTile[] tiles, int[] indices){
        this.reach = reach;
        this.tiles = tiles;
        this.indices = indices;
    }

    public Board2 withPlacedTile(PlacedTile tile, int x, int y){
        int maxComponent = Math.max(Math.abs(x), Math.abs(y));

        if(maxComponent > this.reach){
            int sideLengthSquared = sideLength(maxComponent) * sideLength(maxComponent);

            PlacedTile[] newTiles = new PlacedTile[sideLengthSquared];
            int[] newIndices = new int[indices.length+1];

            int indexOfTile = indexFor(x, y, maxComponent);

            newIndices[newIndices.length - 1] = indexOfTile;
            newTiles[indexOfTile] = tile;

            for(int i = 0; i < indices.length; i++){

                int xOfIndex = indices[i] % sideLength(reach) - reach;
                int yOfIndex = indices[i] / sideLength(reach) - reach;

                int oldIndex = indices[i];
                int newIndex = indexFor(xOfIndex, yOfIndex, maxComponent);

                newIndices[i] = newIndex;
                newTiles[newIndex] = tiles[oldIndex];
            }
            return new Board2(maxComponent, newTiles, newIndices);
        }

        int indexOfTile = indexFor(x, y, this.reach);

        PlacedTile[] newTiles = Arrays.copyOf(tiles, tiles.length);
        int[] newIndices = Arrays.copyOf(indices, indices.length+1);

        newTiles[indexOfTile] = tile;
        newIndices[newIndices.length - 1] = indexOfTile;

        return new Board2(this.reach, newTiles, newIndices);
    }



}
