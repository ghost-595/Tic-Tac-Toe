package ttt;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Renderer extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Board.board.render((Graphics2D)g);
	}
}