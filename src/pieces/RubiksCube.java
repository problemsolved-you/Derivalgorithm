package pieces;

import algorithms.Algs;

public class RubiksCube {
	
	private Center core;
	private Center center1, center2, center3, center4, center5, center6;
	private Edge edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11, edge12;
	private Corner corner1, corner2, corner3, corner4, corner5, corner6, corner7, corner8;
	
	private Piece[][] firstLayer, secondLayer, thirdLayer;
	private Piece[][][] state; 
	
	//Layer relationships to 3D array values state[y][z][x]. Used to enhance readability of the face turn methods.
	//y-axis layers
	final private int U = 2;
	final private int E = 1;
	final private int D = 0;
	//z-axis layers
	final private int F = 2;
	final private int S = 1;
	final private int B = 0;
	//x-axis layers
	final private int R = 2;
	final private int M = 1;
	final private int L = 0;
		
	/**
	 * Constructs a solved Rubik's Cube with White top, Green Front.
	 */
	public RubiksCube() {
		//Centers. How to make these values mutually exclusive?
		core = new Center();
		center1 = new Center(Color.Yellow);
		center2 = new Center(Color.Blue);
		center3 = new Center(Color.Orange);
		center4 = new Center(Color.Red);
		center5 = new Center(Color.Green);
		center6 = new Center(Color.White);
		
		//Edges
		edge1 = new Edge(Color.Yellow, Color.Blue, true);
		edge2 = new Edge(Color.Yellow, Color.Orange, true);
		edge3 = new Edge(Color.Yellow, Color.Red, true);
		edge4 = new Edge(Color.Yellow, Color.Green, true);
		edge5 = new Edge(Color.Blue, Color.Orange, true);
		edge6 = new Edge(Color.Blue, Color.Red, true);
		edge7 = new Edge(Color.Green, Color.Orange, true);
		edge8 = new Edge(Color.Green, Color.Red, true);
		edge9 = new Edge(Color.White, Color.Blue, true);
		edge10 = new Edge(Color.White, Color.Orange, true);
		edge11 = new Edge(Color.White, Color.Red, true);
		edge12 = new Edge(Color.White, Color.Green, true);
		
		//Corners
		corner1 = new Corner(Color.Yellow, Color.Blue, Color.Orange, 0);
		corner2 = new Corner(Color.Yellow, Color.Red, Color.Blue, 0);
		corner3 = new Corner(Color.Yellow, Color.Orange, Color.Green, 0);
		corner4 = new Corner(Color.Yellow, Color.Green, Color.Red, 0);
		corner5 = new Corner(Color.White, Color.Orange, Color.Blue, 0);
		corner6 = new Corner(Color.White, Color.Blue, Color.Red, 0);
		corner7 = new Corner(Color.White, Color.Green, Color.Orange, 0);
		corner8 = new Corner(Color.White, Color.Red, Color.Green, 0);
		
		//Layers
		/*	First Layer
		firstLayer = {
			{corner1, edge1, corner2},
			{edge2, center1, edge3},
			{corner3, edge4, corner4}
		};
		*/
		firstLayer = new Piece[3][3];
		firstLayer[0][0] = corner1;
		firstLayer[0][1] = edge1;
		firstLayer[0][2] = corner2;
		firstLayer[1][0] = edge2;
		firstLayer[1][1] = center1;
		firstLayer[1][2] = edge3;
		firstLayer[2][0] = corner3;
		firstLayer[2][1] = edge4;
		firstLayer[2][2] = corner4;
		
		/* Second Layer
		secondLayer = {
			{edge5, center2, edge6},
			{center3, core, center4},
			{edge7, center5, edge8}
		};
		*/
		secondLayer = new Piece[3][3];
		secondLayer[0][0] = edge5;
		secondLayer[0][1] = center2;
		secondLayer[0][2] = edge6;
		secondLayer[1][0] = center3;
		secondLayer[1][1] = core;
		secondLayer[1][2] = center4;
		secondLayer[2][0] = edge7;
		secondLayer[2][1] = center5;
		secondLayer[2][2] = edge8;
		
		/*
		thirdLayer = {
			{corner5, edge9, corner6},
			{edge10, center6, edge11},
			{corner7, edge12, corner8}
		};
		*/
		thirdLayer = new Piece[3][3];
		thirdLayer[0][0] = corner5;
		thirdLayer[0][1] = edge9;
		thirdLayer[0][2] = corner6;
		thirdLayer[1][0] = edge10;
		thirdLayer[1][1] = center6;
		thirdLayer[1][2] = edge11;
		thirdLayer[2][0] = corner7;
		thirdLayer[2][1] = edge12;
		thirdLayer[2][2] = corner8;
		
		//state = {firstLayer, secondLayer, thirdLayer};
		state = new Piece[3][3][3];
		state[0] = firstLayer;
		state[1] = secondLayer;
		state[2] = thirdLayer;
	}
	
	/**
	 * Generates a scrambled Rubik's Cube
	 * @param scramble The scramble to use with the cube
	 */
	public RubiksCube(String scramble) {
		this();
		Algs.executeAlg(scramble, this);
	}
	
	/**
	 * Prints a character representation of the cube to the console
	 */
	public void printCube() {
		/* Top Layer
		 * [n/a ][c5,3 ][e9,2 ][c6,2 ][n/a ]
		 * [c5,2 ][c5,1 ][e9,1 ][c6,1 ][c6,3 ]
		 * [e10,2][e10,1][ctr6 ][e11,1][e11,2]
		 * [c7,3 ][c7,1 ][e12,1][c8,1 ][c8,2 ]
		 * [n/a ][c7,2 ][e12,2][c8,3 ][n/a ] 
		 */
		char[][] topLayer = {
			{' '						 , state[2][0][0].getColor()[2], state[2][0][1].getColor()[1], state[2][0][2].getColor()[1], ' '						 },
			{state[2][0][0].getColor()[1], state[2][0][0].getColor()[0], state[2][0][1].getColor()[0], state[2][0][2].getColor()[0], state[2][0][2].getColor()[2]},
			{state[2][1][0].getColor()[1], state[2][1][0].getColor()[0], state[2][1][1].getColor()[0], state[2][1][2].getColor()[0], state[2][1][2].getColor()[1]},
			{state[2][2][0].getColor()[2], state[2][2][0].getColor()[0], state[2][2][1].getColor()[0], state[2][2][2].getColor()[0], state[2][2][2].getColor()[1]},
			{' '						 , state[2][2][0].getColor()[1], state[2][2][1].getColor()[1], state[2][2][2].getColor()[2], ' '						 },
		};	
		
		
		for(int i = 0; i < topLayer.length; i++) {
			for (int j = 0; j < topLayer[i].length; j++) {
				System.out.print(topLayer[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Generates an Array of characters representing the folded out version of the cube
	 *         U U U
	 *         U U U
	 *         U U U
	 *        
	 * L L L   F F F   R R R   B B B
	 * L L L   F F F   R R R   B B B
	 * L L L   F F F   R R R   B B B
	 *        
	 *         D D D
	 *         D D D
	 *         D D D  
	 * @return
	 */
	public char[][] readState() {
		char[][] out = new char[9][12];
		//Fill in empty spaces
		for(int i = 0; i < out.length; i++) {
			for(int j = 0; j < out[i].length; j++) {
				out[i][j] = ' ';
			}
		}
		
		//Fill in the U Layer
		for(int i = 0; i < 3; i++) {
			for(int j = 3; j < 6; j++) {
				out[i][j] = state[U][i][j - 3].getColor()[0];
			}
		}
		
		//Fill in the D Layer
		for(int i = 6; i < 9; i++) {
			for(int j = 3; j < 6; j++) {
				out[i][j] = state[D][8 - i][j - 3].getColor()[0];
			}
		}
		
		//Fill in the F Layer
		for(int i = 3; i < 6; i++) {
			for(int j = 3; j < 6; j++) {
				int color = -1;
				if(i == 4) {color = 0;} //These next three lines select the appropriate color off of each piece.
				else if(5 - i == j - 3) {color = 2;}
				else {color = 1;}
				out[i][j] = state[5 - i][F][j - 3].getColor()[color];
			}
		}
		
		//Fill in the B Layer
		for(int i = 3; i < 6; i++) {
			for(int j = 9; j < 12; j++) {
				int color = -1;
				if(i == 4) {color = 0;}
				else if(i + j == 14) {color = 2;}
				else {color = 1;}
				out[i][j] = state[5 - i][B][11 - j].getColor()[color];
			}
		}
		
		//Fill in the R Layer
		for(int i = 3; i < 6; i++) {
			for(int j = 6; j < 9; j++) {
				int color = -1;
				if(i == 4 && j == 7) {color = 0;}
				else if(i + j == 11) {color = 2;}
				else {color = 1;}
				out[i][j] = state[5 - i][8 - j][R].getColor()[color];
			}
		}
		
		//Fill in the L Layer
		for(int i = 3; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				int color = -1;
				if(i == 4 && j == 1) {color = 0;}
				else if(i + j == 5) {color = 2;}
				else {color = 1;}
				out[i][j] = state[5 - i][j][L].getColor()[color];
			}
		}
		
		return out;
	}
	
	//Face Turns
	public void U() {		
		//Move corners clockwise
		Piece tempCorner = state[U][B][L];
		state[U][B][L] = state[U][F][L];
		state[U][F][L] = state[U][F][R];
		state[U][F][R] = state[U][B][R];
		state[U][B][R] = tempCorner;
		//Move edges clockwise
		Piece tempEdge = state[U][B][M];
		state[U][B][M] = state[U][S][L];
		state[U][S][L] = state[U][F][M];
		state[U][F][M] = state[U][S][R];
		state[U][S][R] = tempEdge;
	}
	public void UPrime() {		
		//Move corners counterclockwise
		Piece tempCorner = state[U][B][L];
		state[U][B][L] = state[U][B][R];
		state[U][B][R] = state[U][F][R];
		state[U][F][R] = state[U][F][L];
		state[U][F][L] = tempCorner;
		//Move edges counterclockwise
		Piece tempEdge = state[U][B][M];
		state[U][B][M] = state[U][S][R];
		state[U][S][R] = state[U][F][M];
		state[U][F][M] = state[U][S][L];
		state[U][S][L] = tempEdge;
	}
	public void U2() {
		U();
		U();
	}
	
	public void D() {
		//Move corners counterclockwise
		Piece tempCorner = state[D][B][L];
		state[D][B][L] = state[D][B][R];
		state[D][B][R] = state[D][F][R];
		state[D][F][R] = state[D][F][L];
		state[D][F][L] = tempCorner;
		//Move edges counterclockwise
		Piece tempEdge = state[D][B][M];
		state[D][B][M] = state[D][S][R];
		state[D][S][R] = state[D][F][M];
		state[D][F][M] = state[D][S][L];
		state[D][S][L] = tempEdge;
	}
	public void DPrime() {
		//Move corners clockwise
		Piece tempCorner = state[D][B][L];
		state[D][B][L] = state[D][F][L];
		state[D][F][L] = state[D][F][R];
		state[D][F][R] = state[D][B][R];
		state[D][B][R] = tempCorner;
		//Move edges clockwise
		Piece tempEdge = state[D][B][M];
		state[D][B][M] = state[D][S][L];
		state[D][S][L] = state[D][F][M];
		state[D][F][M] = state[D][S][R];
		state[D][S][R] = tempEdge;
	}
	public void D2() {
		D();
		D();
	}
	
	public void R() {
		//Move Edges Clockwise (R preserves edge orientation)
		Piece tempEdge = state[U][S][R];
		state[U][S][R] = state[E][F][R];
		state[E][F][R] = state[D][S][R];
		state[D][S][R] = state[E][B][R];
		state[E][B][R] = tempEdge;
		//Move Corners Clockwise (R changes corner orientation)
		Piece tempCorner = state[U][B][R];
		state[U][B][R] = state[U][F][R];
		state[U][B][R].changeOrientation(1);
		state[U][F][R] = state[D][F][R];
		state[U][F][R].changeOrientation(-1);
		state[D][F][R] = state[D][B][R];
		state[D][F][R].changeOrientation(1);
		state[D][B][R] = tempCorner;
		state[D][B][R].changeOrientation(-1);
	}
	public void RPrime() {
		//Move Edges Clockwise (R' preserves edge orientation)
		Piece tempEdge = state[U][S][R];
		state[U][S][R] = state[E][B][R];
		state[E][B][R] = state[D][S][R];
		state[D][S][R] = state[E][F][R];
		state[E][F][R] = tempEdge;
		//Move Corners Clockwise (R' changes corner orientation)
		Piece tempCorner = state[U][B][R];
		state[U][B][R] = state[D][B][R];
		state[U][B][R].changeOrientation(1);
		state[D][B][R] = state[D][F][R];
		state[D][B][R].changeOrientation(-1);
		state[D][F][R] = state[U][F][R];
		state[D][F][R].changeOrientation(1);
		state[U][F][R] = tempCorner;
		state[U][F][R].changeOrientation(-1);
	}
	public void R2() {
		R();
		R();
	}
	
	public void L() {
		//Move Edges Clockwise (L preserves edge orientation)
		Piece tempEdge = state[U][S][L];
		state[U][S][L] = state[E][B][L]; //This portion matches R' exactly, swapping 2s and Ls
		state[E][B][L] = state[D][S][L];
		state[D][S][L] = state[E][F][L];
		state[E][F][L] = tempEdge;
		//Move Corners Clockwise (L changes corner orientation)
		Piece tempCorner = state[U][B][L];
		state[U][B][L] = state[D][B][L]; // This portion matches R' exactly, replacing any 2 in the final bracket with L, and negating the change orientation values.
		state[U][B][L].changeOrientation(-1); //Swap negatives
		state[D][B][L] = state[D][F][L];
		state[D][B][L].changeOrientation(1);
		state[D][F][L] = state[U][F][L];
		state[D][F][L].changeOrientation(-1);
		state[U][F][L] = tempCorner;
		state[U][F][L].changeOrientation(1);
	}
	public void LPrime() {
		//Move Edges Clockwise (L' preserves edge orientation)
		Piece tempEdge = state[U][S][L];
		state[U][S][L] = state[E][F][L];
		state[E][F][L] = state[D][S][L];
		state[D][S][L] = state[E][B][L];
		state[E][B][L] = tempEdge;
		//Move Corners Clockwise (L' changes corner orientation)
		Piece tempCorner = state[U][B][L];
		state[U][B][L] = state[U][F][L];
		state[U][B][L].changeOrientation(-1);
		state[U][F][L] = state[D][F][L];
		state[U][F][L].changeOrientation(1);
		state[D][F][L] = state[D][B][L];
		state[D][F][L].changeOrientation(-1);
		state[D][B][L] = tempCorner;
		state[D][B][L].changeOrientation(1);
	}
	public void L2() {
		L();
		L();
	}
	
	public void F() {
		//Move Edges Clockwise (F changes edge orientation)
		Piece tempEdge = state[U][F][M];
		state[U][F][M] = state[E][F][L];
		state[U][F][M].changeOrientation(1);
		state[E][F][L] = state[D][F][M];
		state[E][F][L].changeOrientation(1);
		state[D][F][M] = state[E][F][R];
		state[D][F][M].changeOrientation(1);
		state[E][F][R] = tempEdge;
		state[E][F][R].changeOrientation(1);
		//Move Corners Clockwise (F changes corner orientation)
		Piece tempCorner = state[U][F][L];
		state[U][F][L] = state[D][F][L];
		state[U][F][L].changeOrientation(-1);
		state[D][F][L] = state[D][F][R];
		state[D][F][L].changeOrientation(1);
		state[D][F][R] = state[U][F][R];
		state[D][F][R].changeOrientation(-1);
		state[U][F][R] = tempCorner;
		state[U][F][R].changeOrientation(1);
	}
	public void FPrime() {
		//Move Edges Clockwise (F' changes edge orientation)
		Piece tempEdge = state[U][F][M];
		state[U][F][M] = state[E][F][R];
		state[U][F][M].changeOrientation(1);
		state[E][F][R] = state[D][F][M];
		state[E][F][R].changeOrientation(1);
		state[D][F][M] = state[E][F][L];
		state[D][F][M].changeOrientation(1);
		state[E][F][L] = tempEdge;
		state[E][F][L].changeOrientation(1);
		//Move Corners Clockwise (F' changes corner orientation)
		Piece tempCorner = state[U][F][L];
		state[U][F][L] = state[U][F][R];
		state[U][F][L].changeOrientation(-1);
		state[U][F][R] = state[D][F][R];
		state[U][F][R].changeOrientation(1);
		state[D][F][R] = state[D][F][L];
		state[D][F][L].changeOrientation(-1);
		state[D][F][L] = tempCorner;
		state[D][F][L].changeOrientation(1);
	}
	public void F2() {
		F();
		F();
	}
	
	public void B() {
		//Move Edges Clockwise (B changes edge orientation)
		Piece tempEdge = state[U][B][M];
		state[U][B][M] = state[E][B][R];
		state[U][B][M].changeOrientation(1);
		state[E][B][R] = state[D][B][M];
		state[E][B][R].changeOrientation(1);
		state[D][B][M] = state[E][B][L];
		state[D][B][M].changeOrientation(1);
		state[E][B][L] = tempEdge;
		state[E][B][L].changeOrientation(1);
		//Move Corners Clockwise (B changes corner orientation)
		Piece tempCorner = state[U][B][L];
		state[U][B][L] = state[U][B][R];
		state[U][B][L].changeOrientation(1);
		state[U][B][R] = state[D][B][R];
		state[U][B][R].changeOrientation(-1);
		state[D][B][R] = state[D][B][L];
		state[D][B][L].changeOrientation(1);
		state[D][B][L] = tempCorner;
		state[D][B][L].changeOrientation(-1);
	}
	public void BPrime() {
		//Move Edges Clockwise (B' changes edge orientation)
		Piece tempEdge = state[U][B][M];
		state[U][B][M] = state[E][B][L];
		state[U][B][M].changeOrientation(1);
		state[E][B][L] = state[D][B][M];
		state[E][B][L].changeOrientation(1);
		state[D][B][M] = state[E][B][R];
		state[D][B][M].changeOrientation(1);
		state[E][B][R] = tempEdge;
		state[E][B][R].changeOrientation(1);
		//Move Corners Clockwise (B' changes corner orientation)
		Piece tempCorner = state[U][B][L];
		state[U][B][L] = state[D][B][L];
		state[U][B][L].changeOrientation(1);
		state[D][B][L] = state[D][B][R];
		state[D][B][L].changeOrientation(-1);
		state[D][B][R] = state[U][B][R];
		state[D][B][R].changeOrientation(1);
		state[U][B][R] = tempCorner;
		state[U][B][R].changeOrientation(-1);
	}
	public void B2() {
		B();
		B();
	}
	
	//Slice Turns
	public void M() {
		//Move edges clockwise
		Piece tempEdge = state[U][B][M];
		state[U][B][M] = state[D][B][M];
		state[U][B][M].changeOrientation(1);
		state[D][B][M] = state[D][F][M];
		state[D][B][M].changeOrientation(1);
		state[D][F][M] = state[U][F][M];
		state[D][F][M].changeOrientation(1);
		state[U][F][M] = tempEdge;
		state[U][F][M].changeOrientation(1);
		//Move Centers clockwise
		Piece tempCenter = state[U][S][M];
		state[U][S][M] = state[E][B][M];
		state[E][B][M] = state[D][S][M];
		state[D][S][M] = state[E][F][M];
		state[E][F][M] = tempCenter;
	}
	public void MPrime() {
		//Move edges counterclockwise
		Piece tempEdge = state[U][B][M];
		state[U][B][M] = state[U][F][M];
		state[U][B][M].changeOrientation(1);
		state[U][F][M] = state[D][F][M];
		state[U][F][M].changeOrientation(1);
		state[D][F][M] = state[D][B][M];
		state[D][F][M].changeOrientation(1);
		state[D][B][M] = tempEdge;
		state[D][B][M].changeOrientation(1);
		//Move Centers counterclockwise
		Piece tempCenter = state[U][S][M];
		state[U][S][M] = state[E][F][M];
		state[E][F][M] = state[D][S][M];
		state[D][S][M] = state[E][B][M];
		state[E][B][M] = tempCenter;
	}
	public void M2() {
		M();
		M();
	}
	
	public void S() {
		//Move edges clockwise
		Piece tempEdge = state[U][S][L];
		state[U][S][L] = state[D][S][L];
		state[U][S][L].changeOrientation(1);
		state[D][S][L] = state[D][S][R];
		state[D][S][L].changeOrientation(1);
		state[D][S][R] = state[U][S][R];
		state[D][S][R].changeOrientation(1);
		state[U][S][R] = tempEdge;
		state[U][S][R].changeOrientation(1);
		//Move Centers clockwise
		Piece tempCenter = state[U][S][M];
		state[U][S][M] = state[E][S][L];
		state[E][S][L] = state[D][S][M];
		state[D][S][M] = state[E][S][R];
		state[E][S][R] = tempCenter;
	}
	public void SPrime() {
		//Move edges counterclockwise
		Piece tempEdge = state[U][S][L];
		state[U][S][L] = state[U][S][R];
		state[U][S][L].changeOrientation(1);
		state[U][S][R] = state[D][S][R];
		state[U][S][R].changeOrientation(1);
		state[D][S][R] = state[D][S][L];
		state[D][S][R].changeOrientation(1);
		state[D][S][L] = tempEdge;
		state[D][S][L].changeOrientation(1);
		//Move Centers counterclockwise
		Piece tempCenter = state[U][S][M];
		state[U][S][M] = state[E][S][R];
		state[E][S][R] = state[D][S][M];
		state[D][S][M] = state[E][S][L];
		state[E][S][L] = tempCenter;
	}
	public void S2() {
		S();
		S();
	}
	
	public void E() {
		//Move edges clockwise
		Piece tempEdge = state[E][B][L];
		state[E][B][L] = state[E][B][R];
		state[E][B][L].changeOrientation(1);
		state[E][B][R] = state[E][F][R];
		state[E][B][R].changeOrientation(1);
		state[E][F][R] = state[E][F][L];
		state[E][F][R].changeOrientation(1);
		state[E][F][L] = tempEdge;
		state[E][F][L].changeOrientation(1);
		//Move Centers clockwise
		Piece tempCenter = state[E][B][M];
		state[E][B][M] = state[E][S][R];
		state[E][S][R] = state[E][F][M];
		state[E][F][M] = state[E][S][L];
		state[E][S][L] = tempCenter;
	}
	public void EPrime() {
		//Move edges counterclockwise
		Piece tempEdge = state[E][B][L];
		state[E][B][L] = state[E][F][L];
		state[E][B][L].changeOrientation(1);
		state[E][F][L] = state[E][F][R];
		state[E][F][L].changeOrientation(1);
		state[E][F][R] = state[E][B][R];
		state[E][F][R].changeOrientation(1);
		state[E][B][R] = tempEdge;
		state[E][B][R].changeOrientation(1);
		//Move Centers counterclockwise
		Piece tempCenter = state[E][B][M];
		state[E][B][M] = state[E][S][L];
		state[E][S][L] = state[E][F][M];
		state[E][F][M] = state[E][S][R];
		state[E][S][R] = tempCenter;
	}
	public void E2() {
		E();
		E();
	}
	
	//Wide slice turns
	public void u() {
		U();
		EPrime();
	}
	public void uPrime() {
		UPrime();
		E();
	}
	public void u2() {
		U2();
		E2();
	}
	
	public void d() {
		D();
		E();
	}
	public void dPrime() {
		DPrime();
		EPrime();
	}
	public void d2() {
		D2();
		E2();
	}
	
	public void r() {
		R();
		MPrime();
	}
	public void rPrime() {
		RPrime();
		M();
	}
	public void r2() {
		R2();
		M2();
	}
	
	public void l() {
		L();
		M();
	}
	public void lPrime() {
		LPrime();
		MPrime();
	}
	public void l2() {
		L2();
		M2();
	}
	
	public void f() {
		F();
		S();
	}
	public void fPrime() {
		FPrime();
		SPrime();
	}
	public void f2() {
		F2();
		S2();
	}
	
	public void b() {
		B();
		SPrime();
	}
	public void bPrime() {
		BPrime();
		S();
	}
	public void b2() {
		B2();
		S2();
	}
	
	//Cube Rotations
	public void x() {
		R();
		MPrime();
		LPrime();
	}
	public void xPrime() {
		RPrime();
		M();
		L();
	}
	public void x2() {
		R2();
		M2();
		L2();
	}
	
	public void y() {
		U();
		EPrime();
		DPrime();
	}
	public void yPrime() {
		UPrime();
		E();
		D();
	}
	public void y2() {
		U2();
		E2();
		D2();
	}
	
	public void z() {
		F();
		S();
		BPrime();
	}
	public void zPrime() {
		FPrime();
		SPrime();
		B();
	}
	public void z2() {
		F2();
		S2();
		B2();
	}
	
}


