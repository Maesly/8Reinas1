package com.tec.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shape;
	Casilla[][] tablero;
	Button siguiente, solucion;
	Queen queen;
    int verificar = 0;
    int numeroSolucion = 0;
    ArrayList soluciones;

    //Usar para meter valores del TXT
	int[][] reina =
			{{0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0}};

	//
	public float SCREEN_WIDTH , SCREEN_HEIGHT ;
	
	@Override
	public void create () {
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		tablero = new Casilla[8][8];
		siguiente = new Button("play",SCREEN_WIDTH-200,100,200,125);
		solucion = new Button("agree",SCREEN_WIDTH-200,250,200,125);
		queen = new Queen(8);
		initializeTablero(SCREEN_WIDTH/2-400,SCREEN_HEIGHT/2-400,100);
	}

	//Aquí se inicializa el objeto tablero que se hace de casillas
	public void initializeTablero(float x, float y, float tamano_casilla){
		Casilla casilla_temp;
		for (int fila = 0; fila < 8; fila++) {
			for (int columna = 0; columna < 8; columna++) {
				casilla_temp = new Casilla(x+columna*tamano_casilla,y+(7-fila)*tamano_casilla,tamano_casilla,tamano_casilla);
				casilla_temp.setColor((columna+1)%2 - (fila%2));
				tablero[fila][columna] = casilla_temp;
			}
		}
	}

	public void setQueena(int[][] reina, int[] arreglo){

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				reina[arreglo[j]][j] = 1;
			}
		}

	}

	public void setQueenceros(int[][] reina){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				reina[i][j] = 0;
			}
		}
	}
	//Dar matriz del codigo de meis en el parametro posicion
	public void actualizarReinas(int[][] posiciones){
		for (int f = 0; f < 8; f++) {
			for (int c = 0; c < 8; c++) {

				tablero[f][c].setReina(posiciones[f][c]);

			}
		}
	}

	public void menu(ArrayList soluciones, int solucion ) {



		for (int i = 0; i<soluciones.size();i++){
			int[] aux  = (int[]) soluciones.get(solucion);
			System.out.println("Solucion " + (i+1) + ":");

			for (int j = 0; j<aux.length;j++){
				//System.out.print("(" + (j+1) + "," + (aux[j]+1) + ")");
				System.out.print(aux[j]);
				System.out.print(" ");
                if(aux[j] == solucion){
                    setQueena(reina,aux);

                }
			}
			System.out.println("");

		}


	}

	@Override
	public void render () {
		//Limpiar pantalla
		//Color beige 245,245,220
		Gdx.gl.glClearColor(245f/255f,245f/255f,220f/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Verificar boton presionado
        if(siguiente.checkIfClicked()){
            setQueenceros(reina);
		    if(verificar == 0){
                Queen reinas= new Queen(8);
                //reinas.buscarUnaSolucion();
                reinas.buscarSoluciones();
                soluciones = reinas.getSoluciones();
			verificar++;
		    }else if(numeroSolucion < 92){
                menu(soluciones, numeroSolucion);
                actualizarReinas(reina);
                numeroSolucion++;
                System.out.print("Mandando solucion: " + numeroSolucion);

            }else{
                System.out.print("Ya no hay más soluciones");
            }


		}
		if(solucion.checkIfClicked()){
			//Meter codigo que quiero que pase cuando se presione el boton
		}

		//Metodo actualiza reinas
		//actualizarReinas(reinas);
		//Aqui se dibuja el tablero
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for (int f = 0; f < 8; f++) {
			for (int c = 0; c < 8; c++) {
				tablero[f][c].draw(shape);
			}
		}
		shape.end();

		//Aqui se dibujan reinas
		batch.begin();
		for (int f = 0; f < 8; f++) {
			for (int c = 0; c < 8; c++) {
				tablero[f][c].draw(batch);
			}
		}

		siguiente.update(batch);
		solucion.update(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
