package ttt;

public class O implements Marker
{

	O()
	{
		
	}
	
	public void addMarker(int x, int y)
	{
		Board.board.ttt[x][y] = this;
	}
	
	public String toString()
	{
		return "O";
	}

	@Override
	public void renderBase(int x, int y) 
	{
		Board.board.base[x][y] = this;
	}

}
